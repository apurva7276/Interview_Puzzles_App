package com.example.interview_puzzle_app.ui.PracticeMode;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interview_puzzle_app.R;
import com.example.interview_puzzle_app.databinding.FragmentPracticeModeBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PracticeModeFragment extends Fragment {

    FragmentPracticeModeBinding binding;
    RecyclerView categoryRecyclerView;
    ArrayList<CategoryModel> categories;
    CategoryAdapter adapter;

    //FireStore
    FirebaseFirestore database;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPracticeModeBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        categoryRecyclerView = (RecyclerView) root.findViewById(R.id.categoryList);

        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        categoryRecyclerView.setAdapter(adapter);

        return root;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        database = FirebaseFirestore.getInstance();

        categories = new ArrayList<CategoryModel>();
        adapter = new CategoryAdapter(getContext(), categories);

//        categories.add(new CategoryModel("","Name--","https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Apple-logo.png/640px-Apple-logo.png"));

        database.collection("companyCategories")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        categories.clear();
                        for (DocumentSnapshot snapshot : value) {
                            CategoryModel model = snapshot.toObject(CategoryModel.class);
                            model.setCategoryId(snapshot.getId());
                            categories.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}