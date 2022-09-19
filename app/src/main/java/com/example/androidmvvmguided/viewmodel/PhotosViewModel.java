package com.example.androidmvvmguided.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidmvvmguided.model.Photos;
import com.example.androidmvvmguided.repositories.PhotosAPI;
import com.example.androidmvvmguided.repositories.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PhotosViewModel extends ViewModel {

    private PhotosAPI photosAPI;
    private MutableLiveData<List<Photos>> mPhotoList;
    public LiveData<List<Photos>> getPhotos() {
        return mPhotoList;
    }

    public PhotosViewModel() {
        this.mPhotoList = new MutableLiveData<>();
    }

    public void getDataFromAPI() {
        photosAPI = RetrofitInstance.getRetrofit().create(PhotosAPI.class);
        Call<List<Photos>> call = photosAPI.getPhotos();
        call.enqueue(new Callback<List<Photos>>() {
            @Override
            public void onResponse(Call<List<Photos>> call, Response<List<Photos>> response) {
                if(response.isSuccessful()) {
                    mPhotoList.postValue(response.body());
                } else {
                    Log.i("FAILED ON RESPONSE", "" + response.isSuccessful());
                }
            }

            @Override
            public void onFailure(Call<List<Photos>> call, Throwable t) {
                Log.i("FAILED ON FAILURE", "" + t.getMessage());
            }
        });
    }
}
