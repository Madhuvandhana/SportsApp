package com.example.sportsapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventDetailResponse {
    @SerializedName("events")
    @Expose
    private List<Event> event = null;

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }
}
