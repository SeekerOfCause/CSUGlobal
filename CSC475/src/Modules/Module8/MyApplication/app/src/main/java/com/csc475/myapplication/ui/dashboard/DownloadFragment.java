package com.csc475.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        // Mock cloud files
        ArrayList<CloudFile> cloudFiles = new RandomCloudFiles();
        // ... add more files as needed

        // Set up RecyclerView
        RecyclerView recyclerView = root.findViewById(R.id.download_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CloudFileAdapter adapter = new CloudFileAdapter(cloudFiles);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}