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
