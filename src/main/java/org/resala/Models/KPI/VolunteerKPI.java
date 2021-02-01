package org.resala.Models.KPI;

import org.resala.Models.Volunteer.Volunteer;

import javax.persistence.*;

@Entity
@Table(name = "volunteer_KPI")
public class VolunteerKPI {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "calls_count",nullable = false)
    int callsCount;
    @Column(name = "present_count",nullable = false)
    int presentCount;
    @Column(name = "ensure_count",nullable = false)
    int ensureCount;
    @Column(name = "response_count",nullable = false)
    int responseCount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "volunteer_id",nullable = false)
    Volunteer volunteer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCallsCount() {
        return callsCount;
    }

    public void setCallsCount(int callsCount) {
        this.callsCount = callsCount;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    public int getEnsureCount() {
        return ensureCount;
    }

    public void setEnsureCount(int ensureCount) {
        this.ensureCount = ensureCount;
    }

    public int getResponseCount() {
        return responseCount;
    }

    public void setResponseCount(int responseCount) {
        this.responseCount = responseCount;
    }
}
