package com.example.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.health.Interface.CameraTop;
import com.example.health.Model.DirectModel;
import com.example.health.R;
import com.example.health.databinding.ItemChatDirectBinding;

import java.util.ArrayList;
import java.util.List;

public class DirectAdapter extends RecyclerView.Adapter<DirectAdapter.DirectViewHolder> {
    LayoutInflater inflater;
    ItemChatDirectBinding binding;
    List<DirectModel> list = new ArrayList<>();
    private CameraTop cameraTop;

    public DirectAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void addDirect(List<DirectModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addInter(CameraTop cameraTop) {
        this.cameraTop = cameraTop;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DirectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemChatDirectBinding.inflate(inflater, parent, false);
        return new DirectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectViewHolder holder, int position) {
        if (list.get(position).isStories()) {
            holder.binding.contImage.setBackgroundResource(R.drawable.shape_ellipse);
            if (list.get(position).isClick()) {
                holder.binding.contImage.setBackgroundResource(R.drawable.ic_ellipse_1);
            } else {

            }
            holder.binding.contImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(holder.getAdapterPosition()).setClick(true);
                    holder.binding.contImage.setBackgroundResource(R.drawable.ic_ellipse_1);
                }
            });
        } else {

        }
        Glide.with(holder.binding.imageProfileChat).load(list.get(position).getImage()).circleCrop().into(holder.binding.imageProfileChat);
        holder.binding.txtNickDirect.setText(list.get(position).getNick());
        holder.binding.txtActionDirect.setText(list.get(position).getAction());
        holder.binding.photoDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraTop.click();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DirectViewHolder extends RecyclerView.ViewHolder {
        ItemChatDirectBinding binding;

        public DirectViewHolder(@NonNull ItemChatDirectBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
