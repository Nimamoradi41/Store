package com.example.store;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Categpry extends RecyclerView.Adapter<Adapter_Categpry.view> {
    ArrayList<MODEL_ITEM> items ;
    Data data;
    public void Onclick(Data data)
    {
        this.data=data;
    }
    public Adapter_Categpry(ArrayList<MODEL_ITEM> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cate,parent,false);
         return new  view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {
      MODEL_ITEM item=items.get(position);
      holder.img.setImageResource(item.getImg());
      holder.txt.setText(item.getName());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              data.Setdata(item);
          }
      });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class  view extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;
        public view(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView13);
            txt=itemView.findViewById(R.id.textView42);
        }
    }
    public interface Data{
        public void Setdata(MODEL_ITEM item);
    }
}
