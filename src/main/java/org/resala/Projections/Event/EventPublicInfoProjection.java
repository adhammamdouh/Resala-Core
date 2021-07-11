package org.resala.Projections.Event;

import java.util.Date;

public interface EventPublicInfoProjection {
    int getId();
    String getName();
    Date getFromDate();
}
