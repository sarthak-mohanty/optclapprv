package com.stlindia.optclapprv.adapter;

import com.stlindia.optclapprv.Main3Activity;
import com.stlindia.optclapprv.MainActivity;
import com.stlindia.optclapprv.model.Consumer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stlindia.optclapprv.R;
import com.stlindia.optclapprv.model.Consumer;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {

    private List<Consumer> consumerlist;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, meter, phone,date,block ,village ,district,status;
        public Button view;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.name);
            meter = (TextView) view.findViewById(R.id.address);
            phone = (TextView) view.findViewById(R.id.phone);
            date = (TextView) view.findViewById(R.id.date);
            block = (TextView) view.findViewById(R.id.block);
            village = (TextView) view.findViewById(R.id.village);
            district = (TextView) view.findViewById(R.id.district);
            status=(TextView) view.findViewById(R.id.stat);


        }
    }


    public myAdapter(Context context,List<Consumer> consummerlist) {
        this.consumerlist = consummerlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Consumer consumer = consumerlist.get(position);
        holder.Name.setText(consumer.consumer);
        holder.meter.setText(consumer.meter);
        holder.phone.setText(consumer.phone);
        holder.date.setText(consumer.date);
        holder.block.setText(consumer.block);
        holder.village.setText(consumer.village);
        holder.district.setText(consumer.district);
        holder.status.setText(consumer.status);
        }

    @Override
    public int getItemCount() {
        return consumerlist.size();
    }
}

