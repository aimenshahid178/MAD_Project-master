package com.example.mad_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Myholder> {
    List<DataModel> dataModelArrayList;

    public RVAdapter(List<DataModel> dataModelArrayList) {
        this.dataModelArrayList = dataModelArrayList;
    }

    class Myholder extends RecyclerView.ViewHolder{
        TextView name,designation;

        public Myholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.show_name);
            designation = (TextView) itemView.findViewById(R.id.show_des);
        }
    }


    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,null);
        return new Myholder(view);

    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        DataModel dataModel=dataModelArrayList.get(position);
        holder.name.setText(dataModel.getName());
        holder.designation.setText(dataModel.getDesignation());

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
}

