package org.solarsystem.web.service;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonResponseResultID {

    private String status = null;
    private String message= null;
    private String calculationId = null;
    private JsonNode result = null;

    public String getStatus() {
        return this.status;
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

    public JsonNode getResult() {
        return result;
    }

    public void setResult(JsonNode result) {
        this.result = result;
    }
}