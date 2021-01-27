package com.example.simplestodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener {
        void onItemLongClick(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //Inflate a view using a layout inflater
        View todoView =LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);

        //wrap it inside a View Holder and return it

        return new ViewHolder(todoView);
    }

    //responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        //Grab the item at the position

        String item = items.get(i);

        //Bind the item to the specified view holder

        viewHolder.bind(item);
    }

    //Tells the RV how many items are on the list
    @Override
    public int getItemCount() {
        return items.size();
    }
    //container to provide easy access to views that represent each row of the list

    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Notify the listener which item was long pressed
                    longClickListener.onItemLongClick(getAdapterPosition());


                    return true;
                }
            });
        }

        //update the view inside the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
        }
    }
}
