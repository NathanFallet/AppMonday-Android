package me.nathanfallet.appmonday.models;

import me.nathanfallet.appmonday.utils.Utils;

public class Project {

    private int id;
    private String name;
    private String description;
    private String user;
    private String link;
    private String date;
    private String logo;

    public Project(int id, String name, String description, String user, String link, String date, String logo) {
        setId(id);
        setName(name);
        setDescription(description);
        setUser(user);
        setLink(link);
        setDate(date);
        setLogo(logo);
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = Utils.parseDate(date);
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo.isEmpty() ? "nologo" : logo;
    }

}
