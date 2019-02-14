package me.nathanfallet.appmonday.utils;

public class SettingsSection {

    private String name;
    private SettingsElement[] elements;

    public SettingsSection(String name, SettingsElement[] elements) {
        setName(name);
        setElements(elements);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SettingsElement[] getElements() {
        return elements;
    }

    public void setElements(SettingsElement[] elements) {
        this.elements = elements;
    }
}
