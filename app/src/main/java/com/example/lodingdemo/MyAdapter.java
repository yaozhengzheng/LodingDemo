package com.example.lodingdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created on 2017/6/29.
 * author ${yao}.
 */

public class MyAdapter extends BaseAdapter {
    private List<Contact> list;
    private Context context;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<Contact> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    class ViewHolder {
        ImageView iv, iv2;
        TextView t1, t2, t3;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (holder == null) {
            view = inflater.inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) view.findViewById(R.id.img);
            holder.iv2 = (ImageView) view.findViewById(R.id.tel);
            holder.t1 = (TextView) view.findViewById(R.id.tvname);
            holder.t2 = (TextView) view.findViewById(R.id.number);
            holder.t3 = (TextView) view.findViewById(R.id.email);
            if (list.get(i).getBitmap() != null) {
                holder.iv.setImageBitmap(list.get(i).getBitmap());
            }
            holder.t1.setText(list.get(i).getName());
            final String tel = list.get(i).getNumber();
            holder.t2.setText(tel);
            if (list.get(i).getEmail() != null) {
                holder.t3.setVisibility(View.VISIBLE);
                holder.t3.setText(list.get(i).getEmail());
            }
            holder.iv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    Uri data = Uri.parse("tel:" + tel);
                    i.setData(data);
                    context.startActivity(i);
                }
            });
        }
        return view;
    }
}
