package org.solarsystem.web.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Planet {
    /*Describe planets */
    private int id;
    private String name;
    private Date startDate;
    private double orbitalPeriod;
    private double diameter;
    private double gravity;
    private boolean isSatellites;
    private String shortDescription;
    private String fullDescription;
    private String languageId;
    private List<String> image;

    public Planet(){}

    public Planet(int id, String name, Date startDate, double orbitalPeriod, double diameter,
                  double gravity, boolean isSatellites, String shortDescription,
                  String fullDescription, String languageId, List<String> image) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.orbitalPeriod = orbitalPeriod;
        this.diameter = diameter;
        this.gravity = gravity;
        this.isSatellites = isSatellites;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.languageId = languageId;
        this.image = image;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public boolean isSatellites() {
        return isSatellites;
    }

    public void setSatellites(boolean satellites) {
        isSatellites = satellites;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return id == planet.id &&
                Double.compare(planet.orbitalPeriod, orbitalPeriod) == 0 &&
                Double.compare(planet.diameter, diameter) == 0 &&
                Double.compare(planet.gravity, gravity) == 0 &&
                isSatellites == planet.isSatellites &&
                Objects.equals(name, planet.name) &&
                Objects.equals(startDate, planet.startDate) &&
                Objects.equals(shortDescription, planet.shortDescription) &&
                Objects.equals(fullDescription, planet.fullDescription) &&
                Objects.equals(languageId, planet.languageId) &&
                Objects.equals(image, planet.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, orbitalPeriod, diameter, gravity, isSatellites, shortDescription, fullDescription, languageId, image);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", orbitalPeriod=" + orbitalPeriod +
                ", diameter=" + diameter +
                ", gravity=" + gravity +
                ", isSatellites=" + isSatellites +
                ", shortDescription='" + shortDescription + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", languageId='" + languageId + '\'' +
                ", image=" + image +
                '}';
    }
}
