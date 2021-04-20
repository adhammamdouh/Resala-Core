package org.resala.Projections.Calls;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.resala.Models.Call.CallType;
import org.resala.Models.Call.NetworkType;
import org.resala.Models.Event.Event;
import org.resala.Projections.VolunteerCallsInfoProjection;
import org.resala.Projections.VolunteerPublicInfoProjection;

import javax.persistence.Column;
import java.util.Date;

public interface CallsPublicInfoProjection {
    int getId();

    String getInvitationComment();

    String getFeedBackComment();

    String getNotAttendComment();

    @JsonFormat(pattern = "yyyy-MM-dd")
    Date getTimeUnEditableBefore();

    VolunteerCallsInfoProjection getReceiver();

    CallType getCallType();

    NetworkType getNetworkType();
}
