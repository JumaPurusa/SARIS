package com.example.saris.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.saris.Actvities.AccommodationActivity;
import com.example.saris.Actvities.Admission;
import com.example.saris.Actvities.CommunicationActivity;
import com.example.saris.Actvities.ELearningActivity;
import com.example.saris.Actvities.EVoting;
import com.example.saris.Actvities.ResultsActivity;
import com.example.saris.Actvities.SecurityActivity;
import com.example.saris.Actvities.TimetableActivity;
import com.example.saris.Models.DashboardItem;
import com.example.saris.R;


public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>{

    private Context mContext;
    private DashboardItem[] dashboardItems;

    public DashboardAdapter(Context context, DashboardItem[] list){
        this.mContext = context;
        this.dashboardItems = list;
    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_item_view, parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, final int position) {

        final DashboardItem dashboardItem = dashboardItems[position];

        Glide.with(mContext)
                .load(dashboardItem.getImage())
                .into(holder.imageView);

        holder.textView.setText(dashboardItem.getText());

        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(position == 0){

                            mContext.startActivity(new Intent(mContext, Admission.class));

                        }else if(position == 1){
                            mContext.startActivity(
                                    new Intent(mContext, AccommodationActivity.class)
                            );
                        }else if(position == 2){

                            mContext.startActivity(new Intent(mContext, TimetableActivity.class));

                        }else if(position == 3){

                            mContext.startActivity(new Intent(mContext, ResultsActivity.class));

                        }else if(position == 4){

                            mContext.startActivity(new Intent(mContext, ELearningActivity.class));

                        }else if(position == 5){

                            mContext.startActivity(new Intent(mContext, EVoting.class));

                        }else if(position == 6){

                            mContext.startActivity(new Intent(mContext, CommunicationActivity.class));

                        }else if(position == 7){

                            mContext.startActivity(new Intent(mContext, SecurityActivity.class));
                        }

                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return dashboardItems.length;
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
