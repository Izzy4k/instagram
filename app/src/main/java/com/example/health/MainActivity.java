package com.example.health;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.health.Fragment.BlankFragment;
import com.example.health.Fragment.CameraFragment;
import com.example.health.Fragment.InstFragment;
import com.example.health.Interface.BackTop;
import com.example.health.adapter.ViewPagerAdapter;
import com.example.health.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NavController controller;
    static ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        controller = Navigation.findNavController(this, R.id.fragment_container);
        initView();
    }
    private void initView() {
        binding.navigationButton.setItemIconTintList(null);
        BottomNavigationView.OnNavigationItemSelectedListener button = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        controller.popBackStack(R.id.blankFragment, false);
                        if (controller.getCurrentDestination().getId() == R.id.blankFragment) {
                            InstFragment.rvClick();
                        }

                        break;
                    case R.id.profile:
                        if (controller.getCurrentDestination().getId() != R.id.profileFragment) {
                            controller.navigate(R.id.profileFragment);
                        } else {
                        }
                        break;
                }
                return true;
            }
        };
        binding.navigationButton.setOnNavigationItemSelectedListener(button);
        controller.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()) {
                    case R.id.commentFragment:
                        binding.navigationButton.setVisibility(View.GONE);
                        break;
                    case R.id.storiesFragment:
                        binding.navigationButton.setVisibility(View.GONE);
                        break;
                    default:
                        binding.navigationButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public static void setVisibility(int visibility) {
        binding.navigationButton.setVisibility(visibility);

    }

    @Override
    public void onBackPressed() {
      switch (BlankFragment.pos){
          case 0 :
              BlankFragment.top();
              break;
          case  1:
              super.onBackPressed();
              break;
          case  2:
              BlankFragment.top();
              break;
      }
    }



}