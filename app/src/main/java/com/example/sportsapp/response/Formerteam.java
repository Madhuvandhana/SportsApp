
package com.example.sportsapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Formerteam {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("idPlayer")
    @Expose
    private String idPlayer;
    @SerializedName("idFormerTeam")
    @Expose
    private String idFormerTeam;
    @SerializedName("strSport")
    @Expose
    private String strSport;
    @SerializedName("strPlayer")
    @Expose
    private String strPlayer;
    @SerializedName("strFormerTeam")
    @Expose
    private String strFormerTeam;
    @SerializedName("strMoveType")
    @Expose
    private String strMoveType;
    @SerializedName("strTeamBadge")
    @Expose
    private String strTeamBadge;
    @SerializedName("strJoined")
    @Expose
    private String strJoined;
    @SerializedName("strDeparted")
    @Expose
    private String strDeparted;

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

    public String getIdFormerTeam() {
        return idFormerTeam;
    }

    public void setIdFormerTeam(String idFormerTeam) {
        this.idFormerTeam = idFormerTeam;
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

    public String getStrFormerTeam() {
        return strFormerTeam;
    }

    public void setStrFormerTeam(String strFormerTeam) {
        this.strFormerTeam = strFormerTeam;
    }

    public String getStrMoveType() {
        return strMoveType;
    }

    public void setStrMoveType(String strMoveType) {
        this.strMoveType = strMoveType;
    }

    public String getStrTeamBadge() {
        return strTeamBadge;
    }

    public void setStrTeamBadge(String strTeamBadge) {
        this.strTeamBadge = strTeamBadge;
    }

    public String getStrJoined() {
        return strJoined;
    }

    public void setStrJoined(String strJoined) {
        this.strJoined = strJoined;
    }

    public String getStrDeparted() {
        return strDeparted;
    }

    public void setStrDeparted(String strDeparted) {
        this.strDeparted = strDeparted;
    }

}
