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

    //Create a new view holder
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout and create a new view holder
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    //Replace contents of view holder
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        //Bind data to the view holder
        Item item = items.get(position);
        holder.bind(item);
    }

    // Return the number of items in the dataset
    @Override
    public int getItemCount(){
        return items.size();
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView itemDetailsTextView;


        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            //Initialize views within the view holder
            itemDetailsTextView = itemView.findViewById(R.id.itemDetailsTextView);
        }
        public void bind(Item item){
            //Display item details, no need for 'id' to be displayed as well
            String itemDetails = "List ID: " + item.getListId() + ", Name: " + item.getName() + "\n";
            itemDetailsTextView.setText(itemDetails);
        }
    }
}