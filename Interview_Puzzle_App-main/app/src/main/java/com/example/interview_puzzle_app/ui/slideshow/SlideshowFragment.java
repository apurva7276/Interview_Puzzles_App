package com.example.interview_puzzle_app.ui.slideshow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.interview_puzzle_app.StartContest;
import com.example.interview_puzzle_app.CustomContest;
import com.example.interview_puzzle_app.StartContest;
import com.example.interview_puzzle_app.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    public Button smartContest2, customContest;
    Activity context2;
    String noCompany ="Any";
    String noLevel = "All";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        smartContest2 = binding.btnSmartContest2;
        customContest = binding.btnCustomContest;
        context2 = getActivity();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        smartContest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(context2, StartContest.class);
                i1.putExtra("CompanyName",noCompany);
                i1.putExtra("LevelOfContest",noLevel);
                startActivity(i1);
            }
        });

        customContest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i7 = new Intent(context2, CustomContest.class);
                startActivity(i7);
            }
        });
    }
}