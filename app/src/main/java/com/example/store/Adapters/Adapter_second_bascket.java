package com.example.store.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.store.MODEL_ITEM;
import com.example.store.ModelAddress;
import com.example.store.R;

import java.util.ArrayList;

public class Adapter_second_bascket extends RecyclerView.Adapter<Adapter_second_bascket.view> {
    public ArrayList<ModelAddress> items ;
    Data data;
    int Pos=-1;
    public void Onclick(Data data)
    {
        this.data=data;
    }


    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cate,parent,false);
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_second_address,parent,false);
         return new  view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {
      holder.txt_address.setText(items.get(position).getName());


      if (Pos!=-1)
      {
          if (Pos==position)
          {
              holder.txt_address.setChecked(true);
          }else {
              holder.txt_address.setChecked(false);
          }

      }




      holder.txt_address.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Pos=position;
              notifyDataSetChanged();
          }
      });


    }

    @Override
    public int getItemCount() {

        if (items!=null)
        {
            return items.size();
        }
        return  0;

    }

    public class  view extends RecyclerView.ViewHolder {

        CheckBox txt_address;

        public view(@NonNull View itemView) {
            super(itemView);
            txt_address=itemView.findViewById(R.id.txt_address);
        }
    }
    public interface Data{
        public void Setdata(MODEL_ITEM item);
    }
}
