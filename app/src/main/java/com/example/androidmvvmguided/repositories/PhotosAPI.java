package com.example.androidmvvmguided.repositories;

import com.example.androidmvvmguided.model.Photos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotosAPI {

    @GET("photos")
    Call<List<Photos>> getPhotos();
}
