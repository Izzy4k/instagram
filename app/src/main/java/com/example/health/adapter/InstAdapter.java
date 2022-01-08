package com.example.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.health.DoubleListener;
import com.example.health.Interface.Comment;
import com.example.health.Interface.CommentAdd;
import com.example.health.Interface.DoubleClickCall;
import com.example.health.Model.LentModel;
import com.example.health.R;
import com.example.health.databinding.ItemInstBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class InstAdapter extends RecyclerView.Adapter<InstAdapter.InstViewHolder>{
    ItemInstBinding binding;
    private LayoutInflater inflater;
    private List<LentModel> list = new ArrayList<>();
    private Boolean btnLike = false;
    private Comment comment;
    private CommentAdd commentAdd;
    private Boolean btnCollect = false;


    public InstAdapter(Context context, Comment comment, CommentAdd commentAdd) {
        this.inflater = LayoutInflater.from(context);
        this.comment = comment;
        this.commentAdd = commentAdd;
    }
    public void setLent(List<LentModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<LentModel> getList() {
        return list;
    }


    @NonNull
    @Override
    public InstViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemInstBinding.inflate(inflater, parent, false);
        return new InstViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InstViewHolder holder, int position) {
        holder.binding.dotLine.setPadding(10, 10, 10, 10);
        Glide.with(holder.binding.profileUser).load(list.get(holder.getAdapterPosition()).getProfileImage()).circleCrop().into(holder.binding.profileUser);
        Glide.with(holder.binding.mainPhoto).load(list.get(holder.getAdapterPosition()).getMainImage()).into(holder.binding.mainPhoto);
        holder.binding.btnCollection.setImageResource(list.get(holder.getAdapterPosition()).getCollectImage());
        holder.binding.btnDirectPost.setImageResource(list.get(holder.getAdapterPosition()).getDirectCommentImage());
        holder.binding.btnLike.setImageResource(list.get(holder.getAdapterPosition()).getLikeImage());
        holder.binding.commentImage.setImageResource(list.get(holder.getAdapterPosition()).getCommentImage());
        holder.binding.txtNickName.setText(list.get(holder.getAdapterPosition()).getTxtNick());
        holder.binding.txtTown.setText(list.get(holder.getAdapterPosition()).getTxtTown());
        holder.binding.txtDescription.setText(list.get(holder.getAdapterPosition()).getTxtDescription());
        holder.binding.txtLiked.setText(list.get(holder.getAdapterPosition()).getTxtLiked() + " " + list.get(holder.getAdapterPosition()).getTxtNick());
        if (list.get(position).getCommentModelList() != null) {
            holder.binding.containerComment.setVisibility(View.VISIBLE);
            Glide.with(holder.binding.imageProfileCommentLent).load(list.get(position).getCommentModelList().get(list.get(position).getCommentModelList().size() - 1).getImage()).circleCrop().into(holder.binding.imageProfileCommentLent);
            holder.binding.txtNickCommentLent.setText(list.get(position).getCommentModelList().get(list.get(position).getCommentModelList().size() - 1).getNick());
            holder.binding.txtCommentAddedLent.setText(list.get(position).getCommentModelList().get(list.get(position).getCommentModelList().size() - 1).getComment());
        } else {
            holder.binding.containerComment.setVisibility(View.GONE);
        }


        holder.binding.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnLike) {
                    holder.binding.btnLike.setImageResource(R.drawable.ic_like);
                    holder.binding.txtScore.setText("and one");
                    btnLike = true;
                } else {
                    holder.binding.txtScore.setText("and");
                    holder.binding.btnLike.setImageResource(R.drawable.ic_like_1);
                    btnLike = false;
                }
            }
        });
        holder.binding.commentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment.click(holder.getAdapterPosition(), list.get(holder.getAdapterPosition()));
            }
        });
        holder.binding.dotLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentAdd.add();
            }
        });
        holder.binding.btnCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btnCollect) {
                    holder.binding.btnCollection.setImageResource(R.drawable.ic_collect);
                    btnCollect = true;
                } else {
                    holder.binding.btnCollection.setImageResource(R.drawable.ic_collect_dark);
                    btnCollect = false;
                }
            }
        });
            holder.binding.btnDirectPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commentAdd.dop();
                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class InstViewHolder extends RecyclerView.ViewHolder {
        ItemInstBinding binding;

        public InstViewHolder(@NonNull ItemInstBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
