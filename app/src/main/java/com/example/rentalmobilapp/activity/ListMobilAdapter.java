package com.example.rentalmobilapp.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalmobilapp.R;

import java.util.ArrayList;

public class ListMobilAdapter extends RecyclerView.Adapter<ListMobilAdapter.ListViewHolder> {
    private ArrayList<Mobil> listMobil;
    private OnMobilListener mOnMobilListener;

    public ListMobilAdapter(ArrayList<Mobil> list, OnMobilListener onMobilListener) {
        this.listMobil = list;
        this.mOnMobilListener = onMobilListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(view, mOnMobilListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Mobil mobil = listMobil.get(position);
        holder.tvName.setText(mobil.getName());
    }

    @Override
    public int getItemCount() {
        return listMobil.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName;
        OnMobilListener onMobilListener;

        ListViewHolder(View itemview, OnMobilListener onMobilListener) {
            super(itemview);
            tvName = itemview.findViewById(R.id.item_name);
            this.onMobilListener = onMobilListener;

            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMobilListener.onMobilClick(getAdapterPosition());
        }
    }

    public interface OnMobilListener {
        void onMobilClick(int position);
    }
}
