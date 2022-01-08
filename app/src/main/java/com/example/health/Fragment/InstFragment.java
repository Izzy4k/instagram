package com.example.health.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.health.Buttom.ButtonActivity;
import com.example.health.Buttom.ButtonSecond;
import com.example.health.Interface.CameraClick;
import com.example.health.Interface.Comment;
import com.example.health.Interface.CommentAdd;
import com.example.health.Interface.StoriesClick;
import com.example.health.Interface.ViewPagerClick;
import com.example.health.Model.CommentModel;
import com.example.health.Model.LentModel;
import com.example.health.Model.StoriesModel;
import com.example.health.R;
import com.example.health.adapter.InstAdapter;
import com.example.health.adapter.StoriesAdapter;
import com.example.health.databinding.FragmentInstBinding;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InstFragment extends Fragment implements Comment, CommentAdd, CameraClick, StoriesClick {
    NavController controller;
   public static FragmentInstBinding binding;
    private StoriesAdapter adapterStories;
    private ArrayList<StoriesModel> list;
    private ArrayList<LentModel> model;
    private InstAdapter adapterLent;
    private String currentPath;
    private ViewPagerClick click;
    public InstFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (adapterStories == null) {
            adapterStories = new StoriesAdapter(requireContext(), this, this);
        }
        if (adapterLent == null) {
            adapterLent = new InstAdapter(requireContext(), this, this);
        }
        binding.rvStory.setAdapter(adapterStories);
        binding.rvInts.setAdapter(adapterLent);

        lentAdd();
        imagesAdd();

        listenComment();
        initClick();
    }



    private void initClick() {
        binding.instDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.next();
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInstBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    private void scrollRv() {
        binding.rvInts.setNestedScrollingEnabled(false);
    }

    private void lentAdd() {
        if (model == null) {
            model = new ArrayList<>();
            List<CommentModel> loop = new ArrayList<>();
            loop.add(new CommentModel("Top", "красава", R.drawable.tima));
            LentModel raatbek = new LentModel(R.drawable.raatbek, R.drawable.raatbek, R.drawable.ic_like_1, R.drawable.ic_comment___outline, R.drawable.ic_direct, R.drawable.ic_collect, "RaDick", "Saratov", "Liked by", "Тащусь от лоли", loop);
            LentModel lentModel = new LentModel(R.drawable.noka, R.drawable.tima, R.drawable.ic_like_1, R.drawable.ic_comment___outline, R.drawable.ic_direct, R.drawable.ic_collect, "Noka", "Osh", "Liked by", "Жизнь ворам");
            LentModel lentModel2 = new LentModel(R.drawable.azi, R.drawable.azi, R.drawable.ic_like_1, R.drawable.ic_comment___outline, R.drawable.ic_direct, R.drawable.ic_collect, "Azi", "Osh", "Liked by", "Ауф");
            LentModel lentModel3 = new LentModel(R.drawable.tima, R.drawable.noka, R.drawable.ic_like_1, R.drawable.ic_comment___outline, R.drawable.ic_direct, R.drawable.ic_collect, "Tima", "Osh", "Liked by", "Боб");
            LentModel noka = new LentModel(R.drawable.noka, R.drawable.noka, R.drawable.ic_like_1, R.drawable.ic_comment___outline, R.drawable.ic_direct, R.drawable.ic_collect, "Noka", "Osh", "Liked by", "Жизнь ворам");
            model.add(noka);
            model.add(raatbek);
            model.add(lentModel);
            model.add(lentModel2);
            model.add(lentModel3);
            adapterLent.setLent(model);
        }
    }

    private void imagesAdd() {
        if (list == null) {
            list = new ArrayList<>();
            ArrayList<Integer> images = new ArrayList<>();
            images.add(R.drawable.noka);
            ArrayList<Integer> images2 = new ArrayList<>();
            images2.add(R.drawable.azi);
            ArrayList<Integer> images3 = new ArrayList<>();
            images3.add(R.drawable.tima);
            list.add(new StoriesModel(true));
            list.add(new StoriesModel(images, false, false,"Aboba"));
            list.add(new StoriesModel(images3, false, false,"Noka"));
            list.add(new StoriesModel(images, false, false,"Dura"));
            list.add(new StoriesModel(images2, false, false,"dog"));
            list.add(new StoriesModel(images3, false, false,"Best"));
            list.add(new StoriesModel(images2, false, false,"Top"));
            adapterStories.AddStories(list);
        }
    }

    @Override
    public void click(int pos, LentModel model) {
        Bundle bundle = new Bundle();
        bundle.putString("nick", model.getTxtNick());
        bundle.putInt("profile", model.getProfileImage());
        bundle.putString("description", model.getTxtDescription());
        bundle.putSerializable("model", model);
        controller.navigate(R.id.commentFragment, bundle);
    }

    private void listenComment() {
        getActivity().getSupportFragmentManager().setFragmentResultListener("comment", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                LentModel mod = (LentModel) result.getSerializable("model");
                model.set(model.indexOf(mod), mod);
                adapterLent.setLent(model);
            }
        });
    }

    @Override
    public void add() {
        ButtonActivity buttonActivity = new ButtonActivity();
        buttonActivity.show(getActivity().getSupportFragmentManager(), "bottom");

    }

    @Override
    public void dop() {
        ButtonSecond buttonSecond = new ButtonSecond();
        buttonSecond.show(getActivity().getSupportFragmentManager(),"second");
    }

    @Override
    public void cameraClick(int pos) {
        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
        } else {
            cameraPhoto();
        }
    }

    private void cameraPhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Toast.makeText(requireContext(), "Не удалось сохранить фотку!", Toast.LENGTH_LONG).show();
            }
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(requireContext(), "com.example.health", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            cameraPhoto();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            galleryAddPic();
            setPic();
        }
    }

    @Override
    public void storiesClick(StoriesModel model, List<StoriesModel> list , int pos) {
        Bundle bundle = new Bundle();
//        StoriesModel storiesModel = list.get(pos);
        bundle.putSerializable("model", model);
//        if(storiesModel != null)
        controller.navigate(R.id.storiesFragment, bundle);

    }

    private File createImageFile() throws IOException {
        String Stamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFile = "JPEG_" + Stamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFile, ".jpg", storageDir);
        currentPath = image.getAbsolutePath();
        return image;


    }

    private void galleryAddPic() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(currentPath);
        Uri currentUri = Uri.fromFile(file);
        intent.setData(currentUri);
        getActivity().sendBroadcast(intent);
    }

    private void setPic() {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPath, bmOptions);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(currentPath, bmOptions);
        ArrayList<Bitmap> top = new ArrayList<>();
        top.add(bitmap);
        if(bitmap!= null){
            StoriesModel storiesModel = new StoriesModel(false, false, top);
            adapterStories.AddPhotoStories(storiesModel);
        } else {

        }
    }
    public  void addInter(ViewPagerClick click){
        this.click = click ;
    }
    public static  void rvClick(){
        binding.lente.scrollTo(0,0);
    }
}