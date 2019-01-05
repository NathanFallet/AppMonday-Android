package me.nathanfallet.appmonday.utils;

public class App {

    private String name;
    private String description;
    private String user;
    private String link;
    private String date;
    private String logo;

    public App(String name, String description, String user, String link, String date, String logo) {
        setName(name);
        setDescription(description);
        setUser(user);
        setLink(link);
        setDate(date);
        setLogo(logo);
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
