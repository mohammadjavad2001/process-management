package com.company;

public class ProcessClass {
    int PId, PArrivalTime, PPriority, PBurstTime;

    public ProcessClass(int PId, int PArrivalTime, int PPriority, int PBurstTime) {
        this.PId = PId;
        this.PArrivalTime = PArrivalTime;
        this.PPriority = PPriority;
        this.PBurstTime = PBurstTime;
    }

    public int getPArrivalTime() {
        return PArrivalTime;
    }

    public int getPBurstTime() {
        return PBurstTime;
    }

    public int getPId() {
        return PId;
    }

    public int getPPriority() {
        return PPriority;
    }

    public void setPArrivalTime(int PArrivalTime) {
        this.PArrivalTime = PArrivalTime;
    }

    public void setPBurstTime(int PBurstTime) {
        this.PBurstTime = PBurstTime;
    }

    public void setPId(int PId) {
        this.PId = PId;
    }

    public void setPPriority(int PPriority) {
        this.PPriority = PPriority;
    }
}
