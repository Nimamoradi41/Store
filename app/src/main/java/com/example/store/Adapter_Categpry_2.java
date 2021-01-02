package com.example.store;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Categpry_2 extends RecyclerView.Adapter<Adapter_Categpry_2.view> {
    ArrayList<String> items ;
    Data data;
    public void Onclick(Data data)
    {
        this.data=data;
    }
    public Adapter_Categpry_2(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_sugges,parent,false);
        return new  view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {

        holder.txt.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class  view extends RecyclerView.ViewHolder {

        TextView txt;
        public view(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.textView43);
        }
    }
    public interface Data{
        public void Setdata(MODEL_ITEM item);
    }
}
