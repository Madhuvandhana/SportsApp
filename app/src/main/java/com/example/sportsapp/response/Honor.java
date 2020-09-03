
package com.example.sportsapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Honor {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idPlayer")
    @Expose
    private String idPlayer;
    @SerializedName("idTeam")
    @Expose
    private String idTeam;
    @SerializedName("strSport")
    @Expose
    private String strSport;
    @SerializedName("strPlayer")
    @Expose
    private String strPlayer;
    @SerializedName("strTeam")
    @Expose
    private String strTeam;
    @SerializedName("strHonour")
    @Expose
    private String strHonour;
    @SerializedName("strSeason")
    @Expose
    private String strSeason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getStrSport() {
        return strSport;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }

    public String getStrPlayer() {
        return strPlayer;
    }

    public void setStrPlayer(String strPlayer) {
        this.strPlayer = strPlayer;
    }

    public String getStrTeam() {
        return strTeam;
    }

    public void setStrTeam(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getStrHonour() {
        return strHonour;
    }

    public void setStrHonour(String strHonour) {
        this.strHonour = strHonour;
    }

    public String getStrSeason() {
        return strSeason;
    }

    public void setStrSeason(String strSeason) {
        this.strSeason = strSeason;
    }

}
