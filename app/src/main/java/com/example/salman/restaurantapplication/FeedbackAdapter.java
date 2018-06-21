package com.example.salman.restaurantapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Salman on 6/21/2018.
 */

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    View view;
    RestaurantDetailsActivity detailsActivity;
    List<Feedback> feedbacks;

    public FeedbackAdapter(RestaurantDetailsActivity restaurantDetailsActivity, List<Feedback> feedbackList) {
        this.detailsActivity = restaurantDetailsActivity;
        this.feedbacks = feedbackList;
    }


    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_single_row, parent, false);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedbackViewHolder holder, int position) {
        Feedback feedback = feedbacks.get(position);
        holder.tvFeedback.setText(feedback.getComment());
        holder.ratingBar.setRating(feedback.getRating());

    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {

        TextView tvFeedback;
        RatingBar ratingBar;

        public FeedbackViewHolder(View itemView) {
            super(itemView);
            tvFeedback = itemView.findViewById(R.id.tvFeedBack);
            ratingBar = itemView.findViewById(R.id.feedbackRatingBar);
        }
    }
}
