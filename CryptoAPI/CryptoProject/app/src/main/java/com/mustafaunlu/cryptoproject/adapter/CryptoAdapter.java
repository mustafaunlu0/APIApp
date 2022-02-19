package com.mustafaunlu.cryptoproject.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafaunlu.cryptoproject.databinding.RecyclerRowBinding;
import com.mustafaunlu.cryptoproject.model.CryptoModel;

import java.util.ArrayList;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder> {

    ArrayList<CryptoModel> cryptoModelArrayList;
    private String[] colors={"#ff7f01","#c7c96c","#800080","#19507b","#05ba4f","#4466ff","#ff0001"};

    public CryptoAdapter(ArrayList<CryptoModel> cryptoModelArrayList) {
        this.cryptoModelArrayList = cryptoModelArrayList;
    }

    @NonNull
    @Override
    public CryptoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CryptoViewHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoViewHolder holder, int position) {
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position%7]));
        holder.recyclerRowBinding.currencyTextView.setText(cryptoModelArrayList.get(position).currency);
        holder.recyclerRowBinding.priceTextView.setText(cryptoModelArrayList.get(position).price);

    }

    @Override
    public int getItemCount() {
        return cryptoModelArrayList.size();
    }

    public class CryptoViewHolder extends RecyclerView.ViewHolder{
        RecyclerRowBinding recyclerRowBinding;
        public CryptoViewHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding=recyclerRowBinding;

        }
    }
}
