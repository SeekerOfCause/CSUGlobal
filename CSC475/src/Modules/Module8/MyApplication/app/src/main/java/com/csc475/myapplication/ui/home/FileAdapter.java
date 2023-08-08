package com.csc475.myapplication.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.csc475.myapplication.R;

import java.util.ArrayList;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {

    private ArrayList<TestFile> fileData;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public FileAdapter(ArrayList<TestFile> fileData, OnItemClickListener listener) {
        this.fileData = fileData;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TestFile currentFile = fileData.get(position);
        holder.nameTextView.setText(currentFile.getName());
        holder.typeTextView.setText(currentFile.getType());
        holder.sizeTextView.setText(currentFile.getSize());
        holder.backedUpView.setText((currentFile.getBackedUp() ? "Backed Up" : "Not Backed up"));
    }

    @Override
    public int getItemCount() {
        return fileData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView typeTextView;
        TextView sizeTextView;

        TextView backedUpView;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            sizeTextView = itemView.findViewById(R.id.sizeTextView);
            backedUpView = itemView.findViewById(R.id.backupTextView);

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
