package com.example.interview_puzzle_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.example.interview_puzzle_app.databinding.FragmentHelpBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class Help extends Fragment {

    FragmentHelpBinding binding;
    ExpandableListView expandableListView;
    ArrayList<String> listGroup = new ArrayList<>();
    HashMap<String, ArrayList<String>> listChild = new HashMap<>();
    MainAdapter adapter;
    Activity context1;
    String[] mainItemString = {"Fix a problem","Manage Your Account","Policy, safety and copyright"};
    String[] subItem1 = {"Troubleshoot problem solving contest","Trouble account issues","Troubleshoot hint YouTube video errors, buffering and freezing"};
    String[] subItem2 = {"Use your Google account for Interview Puzzle App","Why sign in to Interview Puzzle App"};
    String[] subItem3 = {"Policies","Privacy and safety centre", "Copyright and rights management"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHelpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context1 = getActivity();
        expandableListView = binding.explistview;
        for(int g=0;g<=2;g++)
        {
            //Add values in group list
            listGroup.add(mainItemString[g]);
            ArrayList<String> arrayList = new ArrayList<>();

            if(g==0) {
                for (int c = 0; c < 3; c++) {
                    //add values in arraylist
                    arrayList.add(subItem1[c]);
                }
                listChild.put(listGroup.get(g), arrayList);
            }
            else if(g==1) {
                for (int c = 0; c < 2; c++) {
                    //add values in arraylist
                    arrayList.add(subItem2[c]);
                }
                listChild.put(listGroup.get(g), arrayList);
            }
            else if(g==2)
            {
                for (int c = 0; c < 3; c++) {
                    //add values in arraylist
                    arrayList.add(subItem3[c]);
                }
                listChild.put(listGroup.get(g), arrayList);
            }
        }

        adapter = new MainAdapter(context1,listGroup,listChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setClickable(true);

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}