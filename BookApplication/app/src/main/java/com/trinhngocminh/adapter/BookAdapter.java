package com.trinhngocminh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trinhngocminh.model.Book;
import com.trinhngocminh.test.MainActivity;
import com.trinhngocminh.test.R;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    MainActivity activity;
    int layout;
    List<Book> books;

    public BookAdapter(MainActivity activity, int layout, List<Book> books) {
        this.activity = activity;
        this.layout = layout;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            //Mapping view
            holder.tvBookInfo = convertView.findViewById(R.id.tvInfo);
            holder.imvEdit = convertView.findViewById(R.id.imvEdit);
            holder.imvTrash = convertView.findViewById(R.id.imvTrash);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //Binding data
        Book book = books.get(position);
        holder.tvBookInfo.setText(book.getNameBook() + " - " + book.getPublisher() + " - " + book.getTimePublished() + " - " + book.getPriceBook());

        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.openEditDialog(book);
            }
        });

        holder.imvTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openDeleteDialog(book);
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        TextView tvBookInfo;
        ImageView imvEdit, imvTrash;

    }
}
