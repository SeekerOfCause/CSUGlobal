package com.csc475.myapplication.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csc475.myapplication.R;

import java.util.ArrayList;

public class CloudFileAdapter extends RecyclerView.Adapter<CloudFileAdapter.ViewHolder> {

    private ArrayList<CloudFile> cloudFileData;

    public CloudFileAdapter(ArrayList<CloudFile> cloudFileData) {
        this.cloudFileData = cloudFileData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CloudFile currentFile = cloudFileData.get(position);
        holder.nameTextView.setText(currentFile.getName());
        holder.typeTextView.setText(currentFile.getType());
        holder.sizeTextView.setText(currentFile.getSize());

        holder.itemView.setOnClickListener(v -> {
            // TODO: Implement file download logic here
        });
    }

    @Override
    public int getItemCount() {
        return cloudFileData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView typeTextView;
        TextView sizeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            sizeTextView = itemView.findViewById(R.id.sizeTextView);
        }
    }
}

