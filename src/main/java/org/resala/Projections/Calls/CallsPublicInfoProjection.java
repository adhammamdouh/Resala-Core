package org.resala.Projections.Calls;

import org.resala.Models.Call.CallType;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Event;
import org.resala.Projections.VolunteerPublicInfoProjection;

public interface CallsPublicInfoProjection {
    int getId();
    Event getEvent();
    VolunteerPublicInfoProjection getReceiver();
    CallType getCallType();
    NetworkType getNetworkType();
}
