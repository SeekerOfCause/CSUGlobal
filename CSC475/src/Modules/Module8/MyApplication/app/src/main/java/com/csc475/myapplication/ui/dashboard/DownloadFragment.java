package com.csc475.myapplication.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csc475.myapplication.R;
import com.csc475.myapplication.databinding.FragmentDownloadBinding;

public class DownloadFragment extends Fragment {

    private FragmentDownloadBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DownloadViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DownloadViewModel.class);

        binding = FragmentDownloadBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<CloudFile> cloudFileData = new RandomCloudFiles();

        RecyclerView recyclerView = root.findViewById(R.id.download_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CloudFileAdapter adapter = new CloudFileAdapter(cloudFileData, new CloudFileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                System.out.println("Clicked");
                CloudFile selectedFile = cloudFileData.get(position);

                // Show AlertDialog with file information
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("File Information");

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_message_cloud, null);
                TextView dialogMessage = dialogView.findViewById(R.id.dialogMessageCloud);

                String message1 = "Name: " + selectedFile.getName() + "\n" +
                        "Type: " + selectedFile.getType() + "\n" +
                        "Size: " + selectedFile.getSize() + "\n" +
                        "Status: Backed Up\n\n";
                String message2 = "This is where Google Cloud Storage API would check to see if the file is already uploaded, if not it would upload the file.";

                SpannableString spannable = new SpannableString(message1 + message2);
                spannable.setSpan(new ForegroundColorSpan(Color.RED), message1.length(), (message1 + message2).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                dialogMessage.setText(spannable);

                builder.setView(dialogView)
                        .setPositiveButton("Download", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), "Download file using async fetch to Google Cloud Storage", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                Toast.makeText(getContext(), "Google Cloud Storage Implementation Required", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}