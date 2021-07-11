package org.resala.Projections.Calls;

import org.resala.Models.Call.NetworkType;
import org.resala.Projections.Branch.BranchPublicInfo;
import org.resala.Projections.Event.EventPublicInfoProjection;
import org.resala.Projections.Volunteer.VolunteerCallsInfoProjection;

public interface NetworkTypeAssignedToVolunteerProjection {
    BranchPublicInfo getBranch();
    NetworkType getNetworkType();
    EventPublicInfoProjection getEvent();
    VolunteerCallsInfoProjection getVolunteers();
}
