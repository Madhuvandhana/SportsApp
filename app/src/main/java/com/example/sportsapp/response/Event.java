
package com.example.sportsapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("idEvent")
    @Expose
    private String idEvent;
    @SerializedName("idSoccerXML")
    @Expose
    private String idSoccerXML;
    @SerializedName("idAPIfootball")
    @Expose
    private Object idAPIfootball;
    @SerializedName("strEvent")
    @Expose
    private String strEvent;
    @SerializedName("strEventAlternate")
    @Expose
    private String strEventAlternate;
    @SerializedName("strFilename")
    @Expose
    private String strFilename;
    @SerializedName("strSport")
    @Expose
    private String strSport;
    @SerializedName("idLeague")
    @Expose
    private String idLeague;
    @SerializedName("strLeague")
    @Expose
    private String strLeague;
    @SerializedName("strSeason")
    @Expose
    private String strSeason;
    @SerializedName("strDescriptionEN")
    @Expose
    private String strDescriptionEN;
    @SerializedName("strHomeTeam")
    @Expose
    private String strHomeTeam;
    @SerializedName("strAwayTeam")
    @Expose
    private String strAwayTeam;
    @SerializedName("intHomeScore")
    @Expose
    private String intHomeScore;
    @SerializedName("intRound")
    @Expose
    private String intRound;
    @SerializedName("intAwayScore")
    @Expose
    private String intAwayScore;
    @SerializedName("intSpectators")
    @Expose
    private String intSpectators;
    @SerializedName("strOfficial")
    @Expose
    private Object strOfficial;
    @SerializedName("strHomeGoalDetails")
    @Expose
    private String strHomeGoalDetails;
    @SerializedName("strHomeRedCards")
    @Expose
    private String strHomeRedCards;
    @SerializedName("strHomeYellowCards")
    @Expose
    private String strHomeYellowCards;
    @SerializedName("strHomeLineupGoalkeeper")
    @Expose
    private String strHomeLineupGoalkeeper;
    @SerializedName("strHomeLineupDefense")
    @Expose
    private String strHomeLineupDefense;
    @SerializedName("strHomeLineupMidfield")
    @Expose
    private String strHomeLineupMidfield;
    @SerializedName("strHomeLineupForward")
    @Expose
    private String strHomeLineupForward;
    @SerializedName("strHomeLineupSubstitutes")
    @Expose
    private String strHomeLineupSubstitutes;
    @SerializedName("strHomeFormation")
    @Expose
    private String strHomeFormation;
    @SerializedName("strAwayRedCards")
    @Expose
    private String strAwayRedCards;
    @SerializedName("strAwayYellowCards")
    @Expose
    private String strAwayYellowCards;
    @SerializedName("strAwayGoalDetails")
    @Expose
    private String strAwayGoalDetails;
    @SerializedName("strAwayLineupGoalkeeper")
    @Expose
    private String strAwayLineupGoalkeeper;
    @SerializedName("strAwayLineupDefense")
    @Expose
    private String strAwayLineupDefense;
    @SerializedName("strAwayLineupMidfield")
    @Expose
    private String strAwayLineupMidfield;
    @SerializedName("strAwayLineupForward")
    @Expose
    private String strAwayLineupForward;
    @SerializedName("strAwayLineupSubstitutes")
    @Expose
    private String strAwayLineupSubstitutes;
    @SerializedName("strAwayFormation")
    @Expose
    private String strAwayFormation;
    @SerializedName("intHomeShots")
    @Expose
    private String intHomeShots;
    @SerializedName("intAwayShots")
    @Expose
    private String intAwayShots;
    @SerializedName("strTimestamp")
    @Expose
    private Object strTimestamp;
    @SerializedName("dateEvent")
    @Expose
    private String dateEvent;
    @SerializedName("dateEventLocal")
    @Expose
    private Object dateEventLocal;
    @SerializedName("strDate")
    @Expose
    private String strDate;
    @SerializedName("strTime")
    @Expose
    private String strTime;
    @SerializedName("strTimeLocal")
    @Expose
    private Object strTimeLocal;
    @SerializedName("strTVStation")
    @Expose
    private Object strTVStation;
    @SerializedName("idHomeTeam")
    @Expose
    private String idHomeTeam;
    @SerializedName("idAwayTeam")
    @Expose
    private String idAwayTeam;
    @SerializedName("strResult")
    @Expose
    private Object strResult;
    @SerializedName("strVenue")
    @Expose
    private Object strVenue;
    @SerializedName("strCountry")
    @Expose
    private Object strCountry;
    @SerializedName("strCity")
    @Expose
    private Object strCity;
    @SerializedName("strPoster")
    @Expose
    private String strPoster;
    @SerializedName("strFanart")
    @Expose
    private String strFanart;
    @SerializedName("strThumb")
    @Expose
    private String strThumb;
    @SerializedName("strBanner")
    @Expose
    private String strBanner;
    @SerializedName("strMap")
    @Expose
    private String strMap;
    @SerializedName("strTweet1")
    @Expose
    private Object strTweet1;
    @SerializedName("strTweet2")
    @Expose
    private Object strTweet2;
    @SerializedName("strTweet3")
    @Expose
    private Object strTweet3;
    @SerializedName("strVideo")
    @Expose
    private Object strVideo;
    @SerializedName("strStatus")
    @Expose
    private Object strStatus;
    @SerializedName("strPostponed")
    @Expose
    private String strPostponed;
    @SerializedName("strLocked")
    @Expose
    private String strLocked;

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getIdSoccerXML() {
        return idSoccerXML;
    }

    public void setIdSoccerXML(String idSoccerXML) {
        this.idSoccerXML = idSoccerXML;
    }

    public Object getIdAPIfootball() {
        return idAPIfootball;
    }

    public void setIdAPIfootball(Object idAPIfootball) {
        this.idAPIfootball = idAPIfootball;
    }

    public String getStrEvent() {
        return strEvent;
    }

    public void setStrEvent(String strEvent) {
        this.strEvent = strEvent;
    }

    public String getStrEventAlternate() {
        return strEventAlternate;
    }

    public void setStrEventAlternate(String strEventAlternate) {
        this.strEventAlternate = strEventAlternate;
    }

    public String getStrFilename() {
        return strFilename;
    }

    public void setStrFilename(String strFilename) {
        this.strFilename = strFilename;
    }

    public String getStrSport() {
        return strSport;
    }

    public void setStrSport(String strSport) {
        this.strSport = strSport;
    }

    public String getIdLeague() {
        return idLeague;
    }

    public void setIdLeague(String idLeague) {
        this.idLeague = idLeague;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public void setStrLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public String getStrSeason() {
        return strSeason;
    }

    public void setStrSeason(String strSeason) {
        this.strSeason = strSeason;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public void setStrDescriptionEN(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }

    public String getStrHomeTeam() {
        return strHomeTeam;
    }

    public void setStrHomeTeam(String strHomeTeam) {
        this.strHomeTeam = strHomeTeam;
    }

    public String getStrAwayTeam() {
        return strAwayTeam;
    }

    public void setStrAwayTeam(String strAwayTeam) {
        this.strAwayTeam = strAwayTeam;
    }

    public String getIntHomeScore() {
        return intHomeScore;
    }

    public void setIntHomeScore(String intHomeScore) {
        this.intHomeScore = intHomeScore;
    }

    public String getIntRound() {
        return intRound;
    }

    public void setIntRound(String intRound) {
        this.intRound = intRound;
    }

    public String getIntAwayScore() {
        return intAwayScore;
    }

    public void setIntAwayScore(String intAwayScore) {
        this.intAwayScore = intAwayScore;
    }

    public String getIntSpectators() {
        return intSpectators;
    }

    public void setIntSpectators(String intSpectators) {
        this.intSpectators = intSpectators;
    }

    public Object getStrOfficial() {
        return strOfficial;
    }

    public void setStrOfficial(Object strOfficial) {
        this.strOfficial = strOfficial;
    }

    public String getStrHomeGoalDetails() {
        return strHomeGoalDetails;
    }

    public void setStrHomeGoalDetails(String strHomeGoalDetails) {
        this.strHomeGoalDetails = strHomeGoalDetails;
    }

    public String getStrHomeRedCards() {
        return strHomeRedCards;
    }

    public void setStrHomeRedCards(String strHomeRedCards) {
        this.strHomeRedCards = strHomeRedCards;
    }

    public String getStrHomeYellowCards() {
        return strHomeYellowCards;
    }

    public void setStrHomeYellowCards(String strHomeYellowCards) {
        this.strHomeYellowCards = strHomeYellowCards;
    }

    public String getStrHomeLineupGoalkeeper() {
        return strHomeLineupGoalkeeper;
    }

    public void setStrHomeLineupGoalkeeper(String strHomeLineupGoalkeeper) {
        this.strHomeLineupGoalkeeper = strHomeLineupGoalkeeper;
    }

    public String getStrHomeLineupDefense() {
        return strHomeLineupDefense;
    }

    public void setStrHomeLineupDefense(String strHomeLineupDefense) {
        this.strHomeLineupDefense = strHomeLineupDefense;
    }

    public String getStrHomeLineupMidfield() {
        return strHomeLineupMidfield;
    }

    public void setStrHomeLineupMidfield(String strHomeLineupMidfield) {
        this.strHomeLineupMidfield = strHomeLineupMidfield;
    }

    public String getStrHomeLineupForward() {
        return strHomeLineupForward;
    }

    public void setStrHomeLineupForward(String strHomeLineupForward) {
        this.strHomeLineupForward = strHomeLineupForward;
    }

    public String getStrHomeLineupSubstitutes() {
        return strHomeLineupSubstitutes;
    }

    public void setStrHomeLineupSubstitutes(String strHomeLineupSubstitutes) {
        this.strHomeLineupSubstitutes = strHomeLineupSubstitutes;
    }

    public String getStrHomeFormation() {
        return strHomeFormation;
    }

    public void setStrHomeFormation(String strHomeFormation) {
        this.strHomeFormation = strHomeFormation;
    }

    public String getStrAwayRedCards() {
        return strAwayRedCards;
    }

    public void setStrAwayRedCards(String strAwayRedCards) {
        this.strAwayRedCards = strAwayRedCards;
    }

    public String getStrAwayYellowCards() {
        return strAwayYellowCards;
    }

    public void setStrAwayYellowCards(String strAwayYellowCards) {
        this.strAwayYellowCards = strAwayYellowCards;
    }

    public String getStrAwayGoalDetails() {
        return strAwayGoalDetails;
    }

    public void setStrAwayGoalDetails(String strAwayGoalDetails) {
        this.strAwayGoalDetails = strAwayGoalDetails;
    }

    public String getStrAwayLineupGoalkeeper() {
        return strAwayLineupGoalkeeper;
    }

    public void setStrAwayLineupGoalkeeper(String strAwayLineupGoalkeeper) {
        this.strAwayLineupGoalkeeper = strAwayLineupGoalkeeper;
    }

    public String getStrAwayLineupDefense() {
        return strAwayLineupDefense;
    }

    public void setStrAwayLineupDefense(String strAwayLineupDefense) {
        this.strAwayLineupDefense = strAwayLineupDefense;
    }

    public String getStrAwayLineupMidfield() {
        return strAwayLineupMidfield;
    }

    public void setStrAwayLineupMidfield(String strAwayLineupMidfield) {
        this.strAwayLineupMidfield = strAwayLineupMidfield;
    }

    public String getStrAwayLineupForward() {
        return strAwayLineupForward;
    }

    public void setStrAwayLineupForward(String strAwayLineupForward) {
        this.strAwayLineupForward = strAwayLineupForward;
    }

    public String getStrAwayLineupSubstitutes() {
        return strAwayLineupSubstitutes;
    }

    public void setStrAwayLineupSubstitutes(String strAwayLineupSubstitutes) {
        this.strAwayLineupSubstitutes = strAwayLineupSubstitutes;
    }

    public String getStrAwayFormation() {
        return strAwayFormation;
    }

    public void setStrAwayFormation(String strAwayFormation) {
        this.strAwayFormation = strAwayFormation;
    }

    public String getIntHomeShots() {
        return intHomeShots;
    }

    public void setIntHomeShots(String intHomeShots) {
        this.intHomeShots = intHomeShots;
    }

    public String getIntAwayShots() {
        return intAwayShots;
    }

    public void setIntAwayShots(String intAwayShots) {
        this.intAwayShots = intAwayShots;
    }

    public Object getStrTimestamp() {
        return strTimestamp;
    }

    public void setStrTimestamp(Object strTimestamp) {
        this.strTimestamp = strTimestamp;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Object getDateEventLocal() {
        return dateEventLocal;
    }

    public void setDateEventLocal(Object dateEventLocal) {
        this.dateEventLocal = dateEventLocal;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public Object getStrTimeLocal() {
        return strTimeLocal;
    }

    public void setStrTimeLocal(Object strTimeLocal) {
        this.strTimeLocal = strTimeLocal;
    }

    public Object getStrTVStation() {
        return strTVStation;
    }

    public void setStrTVStation(Object strTVStation) {
        this.strTVStation = strTVStation;
    }

    public String getIdHomeTeam() {
        return idHomeTeam;
    }

    public void setIdHomeTeam(String idHomeTeam) {
        this.idHomeTeam = idHomeTeam;
    }

    public String getIdAwayTeam() {
        return idAwayTeam;
    }

    public void setIdAwayTeam(String idAwayTeam) {
        this.idAwayTeam = idAwayTeam;
    }

    public Object getStrResult() {
        return strResult;
    }

    public void setStrResult(Object strResult) {
        this.strResult = strResult;
    }

    public Object getStrVenue() {
        return strVenue;
    }

    public void setStrVenue(Object strVenue) {
        this.strVenue = strVenue;
    }

    public Object getStrCountry() {
        return strCountry;
    }

    public void setStrCountry(Object strCountry) {
        this.strCountry = strCountry;
    }

    public Object getStrCity() {
        return strCity;
    }

    public void setStrCity(Object strCity) {
        this.strCity = strCity;
    }

    public String getStrPoster() {
        return strPoster;
    }

    public void setStrPoster(String strPoster) {
        this.strPoster = strPoster;
    }

    public String getStrFanart() {
        return strFanart;
    }

    public void setStrFanart(String strFanart) {
        this.strFanart = strFanart;
    }

    public String getStrThumb() {
        return strThumb;
    }

    public void setStrThumb(String strThumb) {
        this.strThumb = strThumb;
    }

    public String getStrBanner() {
        return strBanner;
    }

    public void setStrBanner(String strBanner) {
        this.strBanner = strBanner;
    }

    public String getStrMap() {
        return strMap;
    }

    public void setStrMap(String strMap) {
        this.strMap = strMap;
    }

    public Object getStrTweet1() {
        return strTweet1;
    }

    public void setStrTweet1(Object strTweet1) {
        this.strTweet1 = strTweet1;
    }

    public Object getStrTweet2() {
        return strTweet2;
    }

    public void setStrTweet2(Object strTweet2) {
        this.strTweet2 = strTweet2;
    }

    public Object getStrTweet3() {
        return strTweet3;
    }

    public void setStrTweet3(Object strTweet3) {
        this.strTweet3 = strTweet3;
    }

    public Object getStrVideo() {
        return strVideo;
    }

    public void setStrVideo(Object strVideo) {
        this.strVideo = strVideo;
    }

    public Object getStrStatus() {
        return strStatus;
    }

    public void setStrStatus(Object strStatus) {
        this.strStatus = strStatus;
    }

    public String getStrPostponed() {
        return strPostponed;
    }

    public void setStrPostponed(String strPostponed) {
        this.strPostponed = strPostponed;
    }

    public String getStrLocked() {
        return strLocked;
    }

    public void setStrLocked(String strLocked) {
        this.strLocked = strLocked;
    }

}
