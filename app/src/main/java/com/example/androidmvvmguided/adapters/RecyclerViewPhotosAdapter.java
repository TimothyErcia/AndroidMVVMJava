package com.example.androidmvvmguided.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.androidmvvmguided.R;
import com.example.androidmvvmguided.model.Photos;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPhotosAdapter extends RecyclerView.Adapter<RecyclerViewPhotosAdapter.mViewHolder> {


    private Context context;
    private List<Photos> photosArrayList;

    public RecyclerViewPhotosAdapter(Context context) {
        this.context = context;
    }

    public void setPhotoArrayList(List<Photos> data) {
        this.photosArrayList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photos_list_item, parent, false);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        holder.titleText.setText(photosArrayList.get(position).getTitle());
        holder.bodyText.setText(photosArrayList.get(position).getUrl());
        holder.urlText.setText(photosArrayList.get(position).getThumbnailUrl());
        GlideUrl url = new GlideUrl(photosArrayList.get(position).getThumbnailUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());

        Glide.with(context)
                .asBitmap()
                .load(url)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(photosArrayList != null) {
            return photosArrayList.size();
        }
        return 0;
    }

    protected class mViewHolder extends RecyclerView.ViewHolder {
        private TextView titleText, bodyText, urlText;
        private ImageView imageView;
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            bodyText = itemView.findViewById(R.id.bodyText);
            urlText = itemView.findViewById(R.id.urlText);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
