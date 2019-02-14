package me.nathanfallet.appmonday.utils;

public class SettingsElementButton extends SettingsElement {

    protected Runnable handler;

    public SettingsElementButton(String id, String text, Runnable handler) {
        super(id, text);
        setHandler(handler);
    }

    public Runnable getHandler() {
        return handler;
    }

    public void setHandler(Runnable handler) {
        this.handler = handler;
    }
}
