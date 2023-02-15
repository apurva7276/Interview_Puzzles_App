package com.example.interview_puzzle_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainAdapter extends BaseExpandableListAdapter {

    ArrayList<String> listGroup;
    HashMap<String,ArrayList<String>> listChild;
    Context context;
    public MainAdapter(Context context, ArrayList<String> listGroup, HashMap<String, ArrayList<String>> listChild) {
        this.listGroup = listGroup;
        this.listChild = listChild;
        this.context =  context;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listChild.get(listGroup.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listGroup.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listChild.get(listGroup.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.exp_list_items,viewGroup,false);
        TextView problemsTv = view.findViewById(R.id.tvProblems);
        String sGroup = String.valueOf(getGroup(i));
        problemsTv.setText("  "+sGroup);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_selectable_list_item,viewGroup,false);
        TextView subProblemsTv = view.findViewById(android.R.id.text1);
        String sChild = String.valueOf(getChild(i,i1));
        subProblemsTv.setText(sChild);
        subProblemsTv.setTextColor(Color.WHITE);
        subProblemsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(viewGroup.getContext(),sChild,Toast.LENGTH_SHORT).show();
                Intent sendProblem = new Intent(context,HelpDetails.class);
                sendProblem.putExtra("problem",sChild);
                context.startActivity(sendProblem);
            }
        });
        view.setBackgroundColor(Color.GREEN);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
