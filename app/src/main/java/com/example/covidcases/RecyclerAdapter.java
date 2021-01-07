package com.example.covidcases;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<CovidDataReportModel> report;

    public RecyclerAdapter(ArrayList<CovidDataReportModel> reports) {
        this.report = reports;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.states.setText(report.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return report.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView states;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            states = itemView.findViewById(R.id.state_name);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
