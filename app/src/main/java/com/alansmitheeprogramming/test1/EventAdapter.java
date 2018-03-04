package com.alansmitheeprogramming.test1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v4.content.ContextCompat;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private Event[] mEvents;
    private Context mContext;

    public EventAdapter(Context context, Event[] events) {
        mEvents = events;
        mContext = context;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_layout, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.bindEvent(mEvents[position]);
    }

    @Override
    public int getItemCount() {
        return mEvents.length;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitleLabel;
        public TextView mCoefficientLabel;
        public TextView mTournamentLabel;

        public EventViewHolder(View itemView) {
            super(itemView);

            mTitleLabel = itemView.findViewById(R.id.title);
            mCoefficientLabel = itemView.findViewById(R.id.coefficient);
            mTournamentLabel = itemView.findViewById(R.id.tournament);

            itemView.setOnClickListener(this);
        }

        public void bindEvent(Event event) {
            mTitleLabel.setText(event.getTitle());
            mCoefficientLabel.setText(event.getCoefficient());
            mTournamentLabel.setText(event.getPlace());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ArticleActivity.class);
            intent.putExtra("article", mEvents[this.getPosition()].getArticle());
            mContext.startActivity(intent);
//            Toast.makeText(mContext, "CLICKED ON ELEMENT NUMBUH " + mEvents[this.getPosition()].getArticle(), Toast.LENGTH_LONG).show();
        }
    }
}
