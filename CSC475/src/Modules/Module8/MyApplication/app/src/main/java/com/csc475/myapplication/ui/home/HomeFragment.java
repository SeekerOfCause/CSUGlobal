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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Create test data
        ArrayList<TestFile> fileData = new ArrayList<>();
        RandomFiles random = new RandomFiles();
        for (int i = 0; i < random.size(); i++) {
            fileData.add(random.get(i));
        }

        // Set up RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

                String message1 = "Name: " + selectedFile.getName() + "\n" +
                        "Type: " + selectedFile.getType() + "\n" +
                        "Size: " + selectedFile.getSize() + "\n" +
                        "Status: Backed Up\n\n";
                String message2 = "This is where Google Cloud Storage API would check to see if the file is created and stored in the cloud already, if not it would give an option to back up the file.";

                SpannableString spannable = new SpannableString(message1 + message2);
                spannable.setSpan(new ForegroundColorSpan(Color.RED), message1.length(), (message1 + message2).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                dialogMessage.setText(spannable);

                builder.setView(dialogView)
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                Toast.makeText(getContext(), "Google Storage Implementation Required", Toast.LENGTH_SHORT).show();
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
