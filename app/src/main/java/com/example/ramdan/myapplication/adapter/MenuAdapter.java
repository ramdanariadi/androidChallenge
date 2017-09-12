package com.example.ramdan.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ramdan.myapplication.R;
import com.example.ramdan.myapplication.data.IMenu;
import com.example.ramdan.myapplication.data.LoadData;

import java.util.List;

/**
 * Created by ramdan on 11/09/17.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolderMenu>{

    List<IMenu> menuList;
    Context context;

    public MenuAdapter(List<IMenu> menuList,Context context){
        this.menuList = menuList;
        this.context = context;
    }

    @Override
    public ViewHolderMenu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list_row,parent,false);
        return new ViewHolderMenu(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderMenu holder, int position) {
        IMenu menu = menuList.get(position);
        holder.name.setText(menu.getName());
        holder.price.setText(menu.getPrice());
        holder.description.setText(menu.getDescription());
        new LoadData.GetImage(holder.menuImage).execute(menu.getPhoto());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class ViewHolderMenu extends RecyclerView.ViewHolder{
        public TextView name,price,description;
        public ImageView menuImage;

        public ViewHolderMenu(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.menu_name);
            price = (TextView)itemView.findViewById(R.id.menu_price);
            description = (TextView)itemView.findViewById(R.id.menu_description);
            menuImage = (ImageView)itemView.findViewById(R.id.resto_image);
        }
    }
}
