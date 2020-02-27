package org.solarsystem.web.service.jsonparser;

import java.util.List;

public class ResultResponse {
    private String status;
    private String message;
    private String calculationId;
    private List<Columns> columns;

    private String[][] rows;

    public ResultResponse() {
    }

    //{
    //  "status": "OK",
    //  "message": "The request was successful.",
    //  "calculationId": "2312e51f-1593-4e72-834b-86e4fb3beca5",
    //  "columns": [
    //    {
    //      "name": "UTC calendar date",
    //      "type": "DATE",
    //      "units": "",
    //      "outputID": "DATE"
    //    },
    //    {
    //      "name": "Longitude (deg)",
    //      "type": "NUMBER",
    //      "units": "deg",
    //      "outputID": "LONGITUDE"
    //    },
    //    {
    //      "name": "Latitude (deg)",
    //      "type": "NUMBER",
    //      "units": "deg",
    //      "outputID": "LATITUDE"
    //    },
    //    {
    //      "name": "Altitude (km)",
    //      "type": "NUMBER",
    //      "units": "km",
    //      "outputID": "ALTITUDE"
    //    },
    //    {
    //      "name": "d Longitude/dt (deg/s)",
    //      "type": "NUMBER",
    //      "units": "deg/s",
    //      "outputID": "D_LONGITUDE_DT"
    //    },
    //    {
    //      "name": "d Latitude/dt (deg/s)",
    //      "type": "NUMBER",
    //      "units": "deg/s",
    //      "outputID": "D_LATITUDE_DT"
    //    },
    //    {
    //      "name": "d Altitude/dt (km/s)",
    //      "type": "NUMBER",
    //      "units": "km/s",
    //      "outputID": "D_ALTITUDE_DT"
    //    },
    //    {
    //      "name": "Speed (km/s)",
    //      "type": "NUMBER",
    //      "units": "km/s",
    //      "outputID": "SPEED"
    //    },
    //    {
    //      "name": "Time at Target",
    //      "type": "DATE",
    //      "units": "",
    //      "outputID": "TIME_AT_TARGET"
    //    },
    //    {
    //      "name": "Light Time (s)",
    //      "type": "NUMBER",
    //      "units": "s",
    //      "outputID": "LIGHT_TIME"
    //    }
    //  ],
    //  "rows": [this is where received data goes

    public ResultResponse(String status, String message, String calculationId, List<Columns> columns, String[][] rows) {
        this.status = status;
        this.message = message;
        this.calculationId = calculationId;
        this.columns = columns;
        this.rows = rows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCalculationId() {
        return calculationId;
    }

    public void setCalculationId(String calculationId) {
        this.calculationId = calculationId;
    }

    public List<Columns> getColumns() {
        return columns;
    }

    public void setColumns(List<Columns> columns) {
        this.columns = columns;
    }

    public String[][] getRows() {
        return rows;
    }

    public void setRows(String[][] rows) {
        this.rows = rows;
    }
}
