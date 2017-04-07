package com.imdb.SimulacionCarnet;

/**
 * Created by Martin on 07/04/2017.
 */

public class Process {
    private int id;

    public int getId() {
        return id;
    }

    private String description;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    private String waitTime;

    public String getWaitTime() {
        return waitTime;
    }
    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    private String serviceTime;

    public String getServiceTime() {
        return serviceTime;
    }
    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    private String totalTime;

    public String getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    private String incidentalEventDescription;

    public String getIncidentalEventDescription() {
        return incidentalEventDescription;
    }
    public void setIncidentalEventDescription(String incidentalEventDescription) {
        this.incidentalEventDescription = incidentalEventDescription;
    }

    private boolean processDismissed;

    public boolean isProcessDismissed() {
        return processDismissed;
    }
    public void setProcessDismissed(boolean processDismissed) {
        this.processDismissed = processDismissed;
    }
}
