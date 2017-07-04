package com.example.lodingdemo;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017/6/29.
 * author ${yao}.
 */

public class GetPeople {
    public static GetPeople getPeople;

    public static GetPeople getInstance() {
        if (getPeople == null) {
            getPeople = new GetPeople();
        }
        return getPeople;
    }

    private List<Contact> list;
    private Context context;

    // ----------------得到本地联系人信息-------------------------------------
    public List<Contact> getpeople(Context context) {
        list = new ArrayList<>();
        String str[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY,
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID};
        //得到ContentResolver ，得到电话本中开始项的光标
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                str, null, null, ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                long contanct_id = cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                long photo_id = cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID));
                String sortkey = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY));
                //查找该联系人的email信息
                String email = null;
                Cursor emails = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contanct_id,
                        null, null);
                int emailIndex = 0;
                if (emails.getCount() > 0) {
                    emailIndex = emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
                }
                while (emails.moveToNext()) {
                    email = emails.getString(emailIndex);
                }
                // 如果photoid 大于0 表示联系人有头像 ，如果没有给此人设置头像则给他一个默认的
                Bitmap photo = null;
                if (photo_id > 0) {
                    Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contanct_id);
                    InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri);
                    photo = BitmapFactory.decodeStream(inputStream);
                }
                list.add(new Contact(name, number, email, photo, sortkey));
                emails.close();
            }
        }
        cursor.close();
        return list;
    }

}