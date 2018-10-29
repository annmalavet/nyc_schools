package com.malavet.a20181027AMNYCSchools.Adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.malavet.a20181027AMNYCSchools.Objects.NYCSchoolsObject;
import com.malavet.a20181027AMNYCSchools.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<NYCSchoolsObject> data;
    private RecyclerViewAdapter.ClickListener clickListener;

    @Inject
    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.school_name.setText(data.get(position).getSchoolName());
        holder.borough.setText(data.get(position).getBorough());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<NYCSchoolsObject> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public interface ClickListener {
        void launchIntent(String dbn);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView school_name;
        private TextView borough;
        private ConstraintLayout constraintLayoutContainer;

        ViewHolder(View itemView) {
            super(itemView);

            school_name = itemView.findViewById(R.id.school_name);
            borough = itemView.findViewById(R.id.borough);
            constraintLayoutContainer = itemView.findViewById(R.id.constraintLayout);
            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //launch detail activity passing the DBN of the school to make new query
                    clickListener.launchIntent(data.get(getAdapterPosition()).getDbn());
                }
            });
        }
    }
}
