package com.example.health.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.health.Model.StoriesProfileModel;
import com.example.health.databinding.ItemStoriesProfileBinding;

import java.util.List;

public class StoriesProfileAdapter extends RecyclerView.Adapter<StoriesProfileAdapter.StoriesProfileViewHolder> {
    private List<StoriesProfileModel> list;
    LayoutInflater inflater;
    private ItemStoriesProfileBinding binding;

    public StoriesProfileAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void addList(List<StoriesProfileModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoriesProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemStoriesProfileBinding.inflate(inflater, parent, false);
        return new StoriesProfileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesProfileViewHolder holder, int position) {
        if (list.get(position).isAdd()) {
            holder.binding.contStoriesAddProfile.setVisibility(View.VISIBLE);
            holder.binding.contStoriesProfile.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < list.get(position).getImages().size(); i++) {
                Glide.with(holder.binding.imageStoriesProfile).load(list.get(position).getImages().get(i)).circleCrop().into(holder.binding.imageStoriesProfile);
            }
            holder.binding.contStoriesAddProfile.setVisibility(View.GONE);
            holder.binding.txtStoriesProfileNick.setText(list.get(position).getNick());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StoriesProfileViewHolder extends RecyclerView.ViewHolder {
        ItemStoriesProfileBinding binding;

        public StoriesProfileViewHolder(@NonNull ItemStoriesProfileBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
