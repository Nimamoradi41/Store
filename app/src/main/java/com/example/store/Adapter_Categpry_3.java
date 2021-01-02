package com.example.store;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Categpry_3 extends RecyclerView.Adapter<Adapter_Categpry_3.view> {
    List<MyTABLE> items ;
    Activity Context;
    RoomDb db;

    public Adapter_Categpry_3(List<MyTABLE> items, Activity context) {
        this.items = items;
        Context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_item,parent,false);
         return new  view(view);
    }

    @Override
    public void onBindViewHolder(@NonNull view holder, int position) {
      MyTABLE table=items.get(position);
      db=RoomDb.getInstance(Context);
      holder.textView64.setText(table.getName());

      holder.EDIT.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              MyTABLE table1=items.get(holder.getAdapterPosition());
              int id=table1.getId();
              String txt=table1.getName();
              Dialog dialog=new Dialog(Context);
              dialog.setContentView(R.layout.dialog_test);
              dialog.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT,
                      ConstraintLayout.LayoutParams.MATCH_PARENT);

              dialog.show();
              EditText editText=dialog.findViewById(R.id.editTextTextPersonName3);
              Button btn=dialog.findViewById(R.id.button6);

              editText.setText(txt);


              btn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      dialog.dismiss();
                      String new_txt=editText.getText().toString().trim();
                      db.getdata().update(id,new_txt);
                      items.clear();
                      items.addAll(db.getdata().getall());
                      notifyDataSetChanged();
                  }
              });


          }
      });


      holder.Del.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              MyTABLE table1=items.get(holder.getAdapterPosition());
              db.getdata().del(table1);
              int pos=holder.getAdapterPosition();
              items.remove(pos);
              notifyItemRemoved(position);
              notifyItemRangeChanged(pos,items.size());
          }
      });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class  view extends RecyclerView.ViewHolder {
        ImageView Del;
        ImageView EDIT;
        TextView textView64;
        public view(@NonNull View itemView) {
            super(itemView);
            EDIT=itemView.findViewById(R.id.imageView42);
            Del=itemView.findViewById(R.id.imageView41);
            textView64=itemView.findViewById(R.id.textView64);
        }
    }
    public interface Data{
        public void Setdata(MODEL_ITEM item);
    }
}
