package com.example.androidmvvmguided.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.androidmvvmguided.R;
import com.example.androidmvvmguided.adapters.RecyclerViewPhotosAdapter;
import com.example.androidmvvmguided.model.Photos;
import com.example.androidmvvmguided.viewmodel.PhotosViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhotosViewModel photosViewModel;
    private RecyclerViewPhotosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewPhotosAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        initViewModel();
    }

    private void initViewModel() {
        photosViewModel = new ViewModelProvider(this).get(PhotosViewModel.class);
        photosViewModel.getPhotos().observe(this, new Observer<List<Photos>>() {
            @Override
            public void onChanged(List<Photos> photos) {
                if(photos != null) {
                    adapter.setPhotoArrayList(photos);
                }
            }
        });
        photosViewModel.getDataFromAPI();
    }
}