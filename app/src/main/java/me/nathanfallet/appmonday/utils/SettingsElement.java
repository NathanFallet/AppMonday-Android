package me.nathanfallet.appmonday.utils;

public class SettingsElement {

    protected String id;
    protected String text;

    public SettingsElement(String id, String text) {
        setId(id);
        setText(text);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
