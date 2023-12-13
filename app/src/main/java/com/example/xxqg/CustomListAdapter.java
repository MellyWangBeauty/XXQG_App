package com.example.xxqg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    private List<ListItem> itemList;

    public CustomListAdapter(Context context, List<ListItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.titleTextView = convertView.findViewById(R.id.titleTextView);
            viewHolder.durationTextView = convertView.findViewById(R.id.durationTextView);
            viewHolder.sourceTextView = convertView.findViewById(R.id.sourceTextView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final ListItem currentItem = itemList.get(position);

        viewHolder.titleTextView.setText(currentItem.getTitle());
        viewHolder.durationTextView.setText(currentItem.getDuration());
        viewHolder.sourceTextView.setText(currentItem.getSource());

        //添加点击事件
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 启动 DetailActivity
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("url", currentItem.getUrl());
                intent.putExtra("title", currentItem.getTitle());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    // ViewHolder 类，用于提高性能
    private static class ViewHolder {
        TextView titleTextView;
        TextView durationTextView;
        TextView sourceTextView;
    }
}
