package com.example.fetchmobileassess;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import androidx.annotation.NonNull;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private List<Item> items;

    public ItemAdapter(List<Item> items){
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount(){
        return items.size();
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView itemDetailsTextView;


        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            itemDetailsTextView = itemView.findViewById(R.id.itemDetailsTextView);
        }
        public void bind(Item item){
            //No need for 'id' to be displayed as well
            String itemDetails = "List ID: " + item.getListId() + ", Name: " + item.getName() + "\n";
            itemDetailsTextView.setText(itemDetails);
        }
    }
}