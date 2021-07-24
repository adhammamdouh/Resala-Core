package org.resala.Projections.Calls;

import org.resala.Projections.Volunteer.VolunteerCallsInfoProjection;

public interface CallsPublicInfoProjectionWithCaller extends CallsPublicInfoProjection {

    VolunteerCallsInfoProjection getCaller();
}
