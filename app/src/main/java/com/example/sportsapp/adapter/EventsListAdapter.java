package com.example.sportsapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.sportsapp.R;
import com.example.sportsapp.response.Event;

import java.util.ArrayList;
import java.util.List;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {

    private Context context;
    List<Event> eventList = new ArrayList<>();
    private IEventDetailsInterface iEventDetailsInterface;

    public EventsListAdapter(Context context, IEventDetailsInterface iEventDetailsInterface) {
        this.context = context;
        this.iEventDetailsInterface = iEventDetailsInterface;
    }

    @NonNull
    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.events_item, viewGroup, false);
        return new EventsListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsListAdapter.ViewHolder viewHolder, int i) {
        final Event event = eventList.get(i);
        if(event != null){
        viewHolder.tvEvent.setText(" -" + event.getStrEvent());
        viewHolder.tvDescription.setText(event.getStrFilename() != null ? event.getStrFilename() : "");
        if(event.getStrThumb() != null) {
            Glide.with(context)
                    .load(event.getStrThumb())
                    .into(viewHolder.profilePIC);
            }
            viewHolder.lContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iEventDetailsInterface.onEventItemClick(event.getIdEvent());
                }
            });
        }
    }

    public void addEventsList(List<Event> eventList) {
        Log.d("TAG", "EventsAdapter");
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView profilePIC;
        private final TextView tvEvent;
        private final TextView tvDescription;
        private final RelativeLayout lContainer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePIC = itemView.findViewById(R.id.dp);
            tvEvent = itemView.findViewById(R.id.event);
            tvDescription = itemView.findViewById(R.id.description);
            lContainer = itemView.findViewById(R.id.container);
        }
    }
    public interface IEventDetailsInterface {
        void onEventItemClick(String id);
    }
}
