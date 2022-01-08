package com.example.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.health.Model.ShapeModel;
import com.example.health.databinding.ItemShapeChatBinding;

import java.util.List;

public class ShapeAdapter extends RecyclerView.Adapter<ShapeAdapter.ShapeViewHolder> {
    ItemShapeChatBinding binding ;
    LayoutInflater inflater ;
    List<ShapeModel> list ;
    public ShapeAdapter (Context context){
        this.inflater = LayoutInflater.from(context);
    }
    public void addList(List<ShapeModel> list){
        this.list = list ;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ShapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemShapeChatBinding.inflate(inflater,parent,false);
        return new ShapeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShapeViewHolder holder, int position) {
        Glide.with(holder.binding.imageProfileShape).load(list.get(position).getImage()).circleCrop().into(holder.binding.imageProfileShape);
        holder.binding.txtNickShape.setText(list.get(position).getNick());
        holder.binding.txtActionShape.setText(list.get(position).getNickMilk());
        holder.binding.btnClickShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(inflater.getContext(), "Отправлено",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShapeViewHolder extends RecyclerView.ViewHolder {
        ItemShapeChatBinding binding ;
        public ShapeViewHolder(@NonNull ItemShapeChatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
