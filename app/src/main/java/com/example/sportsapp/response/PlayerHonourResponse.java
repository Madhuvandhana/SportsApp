
package com.example.sportsapp.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerHonourResponse {

    @SerializedName("honors")
    @Expose
    private List<Honor> honors = null;

    public List<Honor> getHonors() {
        return honors;
    }

    public void setHonors(List<Honor> honors) {
        this.honors = honors;
    }

}
