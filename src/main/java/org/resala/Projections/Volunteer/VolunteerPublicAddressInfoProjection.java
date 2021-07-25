package org.resala.Projections.Volunteer;

import org.resala.Models.Address.Capital;

public interface VolunteerPublicAddressInfoProjection {
    Capital getCapital();
    String getRegionName();
}
