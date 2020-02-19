package org.solarsystem.web.service;

public class Columns {
    private String name;
    private String type;
    private String outputID;
    private String units;

    public Columns() {
    }

    public Columns(String name, String type, String outputID, String units) {
        this.name = name;
        this.type = type;
        this.outputID = outputID;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOutputID() {
        return outputID;
    }

    public void setOutputID(String outputID) {
        this.outputID = outputID;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
