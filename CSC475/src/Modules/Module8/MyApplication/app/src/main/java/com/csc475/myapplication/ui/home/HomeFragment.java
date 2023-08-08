package com.csc475.myapplication.ui.home;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csc475.myapplication.R;
import com.csc475.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private boolean permission = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Create test data
        ArrayList<TestFile> fileData = new RandomFiles();

        // Set up RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (!permission){
            ProgressBar progress = root.findViewById(R.id.mainProgress);
            progress.setIndeterminate(true);
            progress.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("**MOCK PERMISSIONS REQUEST**");

            LayoutInflater initialInflater = getLayoutInflater();

            View dialogView = initialInflater.inflate(R.layout.dialog_message, null);
            TextView dialogMessage = dialogView.findViewById(R.id.dialogMessage);
            String message1 = "PERMISSIONS REQUIRED:\n" +
                    "Please approve permissions for reading and writing to external storage\n";

            String message2 = "This is where the user grants permission for the application to write and read external storage in order to display the file directory";

            SpannableString spannable = new SpannableString(message1 + message2);
            spannable.setSpan(new ForegroundColorSpan(Color.RED), message1.length(), (message1 + message2).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            dialogMessage.setText(spannable);

            builder.setView(dialogView)
                    .setPositiveButton("APPROVE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getContext(), "PERMISSIONS APPROVED", Toast.LENGTH_LONG);
                            progress.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                            permission = true;
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }


        FileAdapter adapter = new FileAdapter(fileData, new FileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                TestFile selectedFile = fileData.get(position);

                // Show AlertDialog with file information
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("File Information");

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_message, null);
                TextView dialogMessage = dialogView.findViewById(R.id.dialogMessage);

                boolean isBackedUp = selectedFile.getBackedUp();

                String message1 = "Name: " + selectedFile.getName() + "\n" +
                        "Type: " + selectedFile.getType() + "\n" +
                        "Size: " + selectedFile.getSize() + "\n" +
                        "Status: Backed Up\n\n";
                String message2 = "This is where Google Cloud Storage API would check to see if the file is created and stored in the bucket already, if not it would give an option to back up the file.";

                SpannableString spannable = new SpannableString(message1 + message2);
                spannable.setSpan(new ForegroundColorSpan(Color.RED), message1.length(), (message1 + message2).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                dialogMessage.setText(spannable);

                builder.setView(dialogView)
                        .setPositiveButton((!isBackedUp ? "Download File" : "File Downloaded"), (!isBackedUp ? new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), "Downloading file from Google Cloud Bucket", Toast.LENGTH_LONG).show();
                            }
                        } : null ))
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                Toast.makeText(getContext(), "Google Cloud Storage Bucket Implementation Required", Toast.LENGTH_SHORT).show();
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
