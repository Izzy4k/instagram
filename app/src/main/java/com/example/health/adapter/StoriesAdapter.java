package com.example.health.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.health.Interface.CameraClick;
import com.example.health.Interface.StoriesClick;
import com.example.health.Model.StoriesModel;
import com.example.health.R;
import com.example.health.databinding.ItemStoriesBinding;


import java.util.List;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder> {
    LayoutInflater inflater;
    List<StoriesModel> list;
    ItemStoriesBinding binding;
    CameraClick click;
    StoriesClick storiesClick ;

    public StoriesAdapter(Context context, CameraClick click , StoriesClick storiesClick) {
        this.inflater = LayoutInflater.from(context);
        this.click = click;
        this.storiesClick = storiesClick ;
    }

    public void AddStories(List<StoriesModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void AddPhotoStories(StoriesModel model) {
        this.list.add(model);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public StoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemStoriesBinding.inflate(inflater, parent, false);
        return new StoriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesViewHolder holder, int position) {
        if (list.get(position).isAdd()) {
            holder.binding.storiesAddContainer.setVisibility(View.VISIBLE);
            holder.binding.imageStories.setVisibility(View.GONE);
            holder.binding.imageStoriesAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("------", holder.getAdapterPosition() + "");
                    click.cameraClick(holder.getAdapterPosition());
                }
            });
        } else if (list.get(position).getStories() != null) {
            if (list.get(position).isViewed()) {
                holder.binding.imageStories.setBackgroundResource(R.drawable.ic_ellipse_1);
            } else {
                holder.binding.imageStories.setBackgroundResource(R.drawable.shape_ellipse);
            }
            for (int i = 0; i < list.get(position).getStories().size(); i++) {
                Glide.with(holder.binding.imageStories).load(list.get(position).getStories().get(i)).circleCrop().into(holder.binding.imageStories);
                holder.binding.imageStories.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.get(holder.getAdapterPosition()).setViewed(true);
                        holder.binding.imageStories.setBackgroundResource(R.drawable.ic_ellipse_1);
                        storiesClick.storiesClick(list.get(holder.getAdapterPosition()), list, holder.getAdapterPosition());
                    }
                });
            }
            holder.binding.storiesAddContainer.setVisibility(View.GONE);
        } else {
            for (int i = 0; i < list.get(position).getPhotoStories().size(); i++) {
                if (list.get(position).isViewed()) {
                    holder.binding.imageStories.setBackgroundResource(R.drawable.ic_ellipse_1);
                } else {
                    holder.binding.imageStories.setBackgroundResource(R.drawable.shape_ellipse);
                }
                Glide.with(holder.binding.imageStories).load(list.get(position).getPhotoStories().get(i)).circleCrop().into(holder.binding.imageStories);
                holder.binding.imageStories.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.get(holder.getAdapterPosition()).setViewed(true);
                        holder.binding.imageStories.setBackgroundResource(R.drawable.ic_ellipse_1);
                        storiesClick.storiesClick(list.get(holder.getAdapterPosition()), list, holder.getAdapterPosition());
                    }
                });
            }
            holder.binding.storiesAddContainer.setVisibility(View.GONE);

        }
        holder.binding.txtStoriesAdd.setText("История");
        holder.binding.txtNickStoriesBegin.setText(list.get(position).getNick());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StoriesViewHolder extends RecyclerView.ViewHolder {
        ItemStoriesBinding binding;

        public StoriesViewHolder(@NonNull ItemStoriesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
