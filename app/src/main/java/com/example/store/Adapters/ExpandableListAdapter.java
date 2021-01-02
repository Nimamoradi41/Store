package com.example.store.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.store.Main_Fragments.DataFilter;
import com.example.store.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.http.PUT;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, ArrayList<DataFilter>> _listDataChild;
    ArrayList<String> Cate;
    ArrayList<String> Brand;
    public  Data data;
    public  String ID;
    public  void Click(Data data)
    {
        this.data=data;
    }
    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, ArrayList<DataFilter>> listChildData,String Id) {
        this._context = context;
        this.ID=Id;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        Cate=new ArrayList<>();
        Brand=new ArrayList<>();
    }
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return _listDataChild.get(_listDataHeader.get(i))
                .size();
    }

    @Override
    public Object getGroup(int i) {
        return _listDataHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this._listDataChild.get(this._listDataHeader.get(i))
                .get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String s= (String) getGroup(i);
        if (view==null)
        {
            LayoutInflater layoutInflater= (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.list_group,null);
        }

//        ImageView txt_filter_grop=view.findViewById(R.id.txt_filter_grop);
//
//        if (txt_filter_grop.getRotation()==0)
//        {
//            txt_filter_grop.animate().rotation(270f).setDuration(300).start();
//        }
//
//
//        if (txt_filter_grop.getRotation()==270)
//        {
//            txt_filter_grop.animate().rotation(0f).setDuration(300).start();
//        }


        TextView txt=view.findViewById(R.id.lblListHeader);
        txt.setText(s);

        return  view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final DataFilter childText = (DataFilter) getChild(i, i1);


        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.list_item_expended, null);
        }

        CheckBox txtListChild = (CheckBox) view.findViewById(R.id.lblListItem);
        txtListChild.setText(childText.getTitle());

        if (ID.length()!=0)
        {
            if (ID.equals(childText.getId()))
            {
                txtListChild.setChecked(true);
                Cate.add(ID);
            }
        }
        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(viewGroup.getContext(), ""+childText.getTitle(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(viewGroup.getContext(), ""+_listDataHeader.get(i), Toast.LENGTH_SHORT).show();
//                if (childText.getId().equals(ID))
//                {
//                    if (txtListChild.isChecked())
//                    {
//
//                    }else {
//
//                    }
//                }


                if (txtListChild.isChecked())
                {
                    if (i==0)
                    {

                        Cate.add(childText.getId());
                    }else if (i==1){

                        Brand.add(childText.getId());
                    }
                }else {
                    if (i==0)
                    {

                        Cate.remove(childText.getId());
                    }else if (i==1){

                        Brand.remove(childText.getId());
                    }

                }


               data.Daaat(Cate,Brand);

            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public interface Data{
        public void Daaat(ArrayList<String> Cate,ArrayList<String> Brand);
    }
}
