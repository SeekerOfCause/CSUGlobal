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
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public CloudFileAdapter(ArrayList<CloudFile> cloudFileData, OnItemClickListener listener) {
        this.cloudFileData = cloudFileData;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_cloud, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CloudFile currentFile = cloudFileData.get(position);
        holder.nameTextView.setText(currentFile.getName());
        holder.typeTextView.setText(currentFile.getType());
        holder.sizeTextView.setText(currentFile.getSize());
    }

    @Override
    public int getItemCount() { return cloudFileData.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView typeTextView;
        TextView sizeTextView;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextViewCloud);
            typeTextView = itemView.findViewById(R.id.typeTextViewCloud);
            sizeTextView = itemView.findViewById(R.id.sizeTextViewCloud);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

