package org.resala.Projections.LeadVolunteer;

import javax.persistence.Column;

public interface LeadVolunteerKPIProjection {
    int getId();
    int getPresentCount();
    int getEnsureCount();
    int getCallsCount();
}
