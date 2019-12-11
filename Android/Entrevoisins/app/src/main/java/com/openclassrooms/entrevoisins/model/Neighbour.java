package com.openclassrooms.entrevoisins.model;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Home address of the user */
    private String homeAddress;

    /** Phone number of the user */
    private String phoneNumber;

    /** Web address of the user */
    private String webAddress;

    /** Description of the user */
    private String description;

    /**
     *
     * @param id
     * @param name
     * @param avatarUrl
     * @param homeAddress
     * @param phoneNumber
     * @param webAddress
     * @param description
     */
    public Neighbour(Integer id, String name, String avatarUrl, String homeAddress, String phoneNumber, String webAddress, String description) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.webAddress = webAddress;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() { return avatarUrl; }

    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getHomeAddress() { return homeAddress; }

    public void setHomeAddress(String homeAddress) { this.homeAddress = homeAddress; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getWebAddress() { return webAddress; }

    public void setWebAddress(String webAddress) { this.webAddress = webAddress; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
