package me.nathanfallet.appmonday.models;

import me.nathanfallet.appmonday.utils.Utils;

public class Competition {

    private int id;
    private String name;
    private String description;
    private String criterias;
    private String start;
    private String end;
    private boolean coming;
    private boolean playing;

    public Competition(int id, String name, String description, String criterias, String start, String end, boolean coming, boolean playing) {
        setId(id);
        setName(name);
        setDescription(description);
        setCriterias(criterias);
        setStart(start);
        setEnd(end);
        setComing(coming);
        setPlaying(playing);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCriterias() {
        return criterias;
    }

    public void setCriterias(String criterias) {
        this.criterias = criterias;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = Utils.parseDate(start);;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = Utils.parseDate(end);;
    }

    public boolean getComing() {
        return coming;
    }

    public void setComing(boolean coming) {
        this.coming = coming;
    }

    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
