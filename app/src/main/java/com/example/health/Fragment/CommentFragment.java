package com.example.health.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.bumptech.glide.Glide;
import com.example.health.Buttom.ButtonSecond;
import com.example.health.Model.CommentModel;
import com.example.health.Model.LentModel;
import com.example.health.R;
import com.example.health.adapter.CommentAdapter;
import com.example.health.databinding.FragmentCommentBinding;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {
    FragmentCommentBinding binding;
    CommentAdapter commentAdapter;
    private NavController controller;
    private List<CommentModel> lamp = new ArrayList<>();
    private LentModel model = new LentModel();


    public CommentFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentAdapter = new CommentAdapter(requireContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCommentBinding.inflate(inflater, container, false);
        initView();
        binding.rvComment.setAdapter(commentAdapter);
        binding.btnPublic.setEnabled(false);
        text();
        initBtn();
        return binding.getRoot();
    }

    private void initBtn() {
        binding.directBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonSecond buttonSecond = new ButtonSecond();
                buttonSecond.show(getActivity().getSupportFragmentManager(),"commentTop");
            }
        });
    }


    private void text() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.editComment.getText().toString().length() == 0) {
                    binding.btnPublic.setEnabled(false);
                } else if (binding.editComment.getText().toString().length() > 0) {
                    binding.btnPublic.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        binding.editComment.addTextChangedListener(textWatcher);
    }

    private void initView() {
        if (getArguments() != null) {
            initComment();
            Glide.with(binding.imageProfile).load(requireArguments().getInt("profile")).circleCrop().into(binding.imageProfile);
            binding.txtNickDecription.setText(requireArguments().getString("nick"));
            binding.descriptionTxt.setText(requireArguments().getString("description"));
            String nick = requireArguments().getString("nick");
            int image = requireArguments().getInt("profile");
            binding.btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controller.popBackStack(R.id.blankFragment,false);
                }
            });
            binding.btnPublic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String comment = binding.editComment.getText().toString().trim();
                    CommentModel molodec = new CommentModel(nick, comment, image);
                    lamp.add(molodec);
                    commentAdapter.setComments(lamp);
                    model.setCommentModelList(lamp);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);
                    Bundle bundle = new Bundle();
                    bundle.putString("nick", nick);
                    bundle.putInt("image", image);
                    bundle.putString("text", comment);
                    bundle.putSerializable("model", model);
                    binding.editComment.setText("");
                    getActivity().getSupportFragmentManager().setFragmentResult("comment", bundle);
                }
            });
        }
    }

    private void initComment() {
        model = (LentModel) requireArguments().getSerializable("model");
        if (model.getCommentModelList() != null) {
            lamp = model.getCommentModelList();
            commentAdapter.setComments(lamp);
        }
        binding.rvComment.setAdapter(commentAdapter);

    }
}