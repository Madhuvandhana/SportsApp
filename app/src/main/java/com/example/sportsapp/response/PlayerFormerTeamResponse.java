
package com.example.sportsapp.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerFormerTeamResponse {

    @SerializedName("formerteams")
    @Expose
    private List<Formerteam> formerteams = null;

    public List<Formerteam> getFormerteams() {
        return formerteams;
    }

    public void setFormerteams(List<Formerteam> formerteams) {
        this.formerteams = formerteams;
    }

}
