package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.models.Product;
import com.example.mydb.MainActivity;
import com.example.mydb.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    MainActivity activity;
    int itemLayout;
    List<Product> products;

    // Constructor
    public ProductAdapter(MainActivity activity, int itemLayout, List<Product> products) {
        this.activity = activity;
        this.itemLayout = itemLayout;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(itemLayout, null);
            holder.txtProductInfo = view.findViewById(R.id.txtProductInfo);
            holder.imvEdit = view.findViewById(R.id.imvEdit);
            holder.imvDel = view.findViewById(R.id.imvDel);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Binding data
        Product p = products.get(i);
        holder.txtProductInfo.setText(p.getProductName() + " - " + p.getProductPrice());

        holder.imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.openEditDialog(p);
            }
        });

        return view;
    }

    public static class ViewHolder{
        TextView txtProductInfo;
        ImageView imvEdit, imvDel;
    }
}
