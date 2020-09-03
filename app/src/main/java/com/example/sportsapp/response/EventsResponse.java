
package com.example.sportsapp.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventsResponse {

    @SerializedName("event")
    @Expose
    private List<Event> event = null;

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }

}
