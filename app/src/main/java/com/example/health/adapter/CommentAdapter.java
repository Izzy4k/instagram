package com.example.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.health.Model.CommentModel;
import com.example.health.databinding.ItemCommentBinding;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.commentViewHolder> {
    private LayoutInflater inflater ;
    private ItemCommentBinding binding ;
    private List<CommentModel> list  = new ArrayList<>();
    public CommentAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }
    public void setComments(List<CommentModel> list ){
        this.list = list ;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public commentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemCommentBinding.inflate(inflater,parent,false);
        return new commentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull commentViewHolder holder, int position) {
        Glide.with(holder.binding.imageProfileComment).load(list.get(position).getImage()).circleCrop().into(holder.binding.imageProfileComment);
        holder.binding.txtNickComment.setText(list.get(position).getNick());
        holder.binding.txtCommentAdded.setText(list.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class commentViewHolder extends  RecyclerView.ViewHolder {
        ItemCommentBinding binding ;
        public commentViewHolder(@NonNull ItemCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
