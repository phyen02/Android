package com.android.prepareforfinal.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.prepareforfinal.Activity.MainActivity;
import com.android.prepareforfinal.Model.Book;
import com.android.prepareforfinal.R;

import java.util.List;

public class BookAdapter extends BaseAdapter{
    MainActivity activity;
    List<Book> bookList;
    int layout;

    public BookAdapter(MainActivity activity, List<Book> bookList, int layout) {
        this.activity = activity;
        this.bookList = bookList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout,null);

            holder.tvInfo = convertView.findViewById(R.id.tvInfo);
            holder.lvBook = convertView.findViewById(R.id.lvBook);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = bookList.get(position);
        holder.tvInfo.setText("Tên sách: " + book.getNameBook() + "\n"
                            + "Tên nhà xuất bản: " + book.getPublisher() + "\n"
                            + "Giá sách: " + book.getPriceBook());

        holder.tvInfo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                activity.openDialogAdd(book);
                return true;
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        TextView tvInfo;
        ListView lvBook;
    }
}
