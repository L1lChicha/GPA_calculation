package com.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



    private List <Item> items;

    public MyAdapter(List<Item> items) {
        this.items = items;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.subjectView.setText(this.items.get(position).getSubject());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subjectView;
        RadioGroup radioGroup;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectView = itemView.findViewById(R.id.sub);
            radioGroup = itemView.findViewById(R.id.radioGroup);

        }
    }

    @Override
    public  int getItemCount() {
        return items.size();
    }

    public List<Item> getItems(){
        return items;
    }


}





