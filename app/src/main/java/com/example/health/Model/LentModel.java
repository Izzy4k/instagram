package com.example.health.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LentModel implements Serializable {
    private int profileImage , mainImage , likeImage , commentImage,directCommentImage,collectImage;
    private String txtNick , txtTown , txtLiked , txtDescription ;
    private long id ;
    private List<CommentModel> commentModelList ;
    private Boolean likeClick = false ;
    private  Boolean collectionClick = false;

    public LentModel() {
    }

    public LentModel(int profileImage, int mainImage, int likeImage, int commentImage, int directCommentImage, int collectImage, String txtNick, String txtTown, String txtLiked, String txtDescription ) {
        this.profileImage = profileImage;
        this.mainImage = mainImage;
        this.likeImage = likeImage;
        this.commentImage = commentImage;
        this.directCommentImage = directCommentImage;
        this.collectImage = collectImage;
        this.txtNick = txtNick;
        this.txtTown = txtTown;
        this.txtLiked = txtLiked;
        this.txtDescription = txtDescription;


    }
    public LentModel(int profileImage, int mainImage, int likeImage, int commentImage, int directCommentImage, int collectImage, String txtNick, String txtTown, String txtLiked, String txtDescription  , List<CommentModel> list) {
        this.profileImage = profileImage;
        this.mainImage = mainImage;
        this.likeImage = likeImage;
        this.commentImage = commentImage;
        this.directCommentImage = directCommentImage;
        this.collectImage = collectImage;
        this.txtNick = txtNick;
        this.txtTown = txtTown;
        this.txtLiked = txtLiked;
        this.txtDescription = txtDescription;
        this.commentModelList = list ;

    }

    public int getProfileImage() {
        return profileImage;
    }

    public int getMainImage() {
        return mainImage;
    }

    public int getLikeImage() {
        return likeImage;
    }

    public int getCommentImage() {
        return commentImage;
    }

    public int getDirectCommentImage() {
        return directCommentImage;
    }

    public int getCollectImage() {
        return collectImage;
    }

    public String getTxtNick() {
        return txtNick;
    }

    public String getTxtTown() {
        return txtTown;
    }

    public String getTxtLiked() {
        return txtLiked;
    }

    public String getTxtDescription() {
        return txtDescription;
    }

    public long getId() {
        return id;
    }

    public List<CommentModel> getCommentModelList() {
        return commentModelList;
    }

    public void setCommentModelList(List<CommentModel> commentModelList) {
        this.commentModelList = commentModelList;
    }

    public Boolean getCollectionClick() {
        return collectionClick;
    }

    public void setCollectionClick(Boolean collectionClick) {
        this.collectionClick = collectionClick;
    }

    public Boolean getLikeClick() {
        return likeClick;
    }

    public void setLikeClick(Boolean likeClick) {
        this.likeClick = likeClick;
    }
}
