package org.resala;

public interface StaticNames {
    String CEO = "CEO";
    String ViceCEO = "VICE_CEO";
    String TeamLeader = "TEAM_LEADER";
    String TeamMember = "TEAM_MEMBER";
    String normalVolunteer = "NORMAL_VOLUNTEER";
    //----------------------------------------------------------------------------------------------------------------------
    String activeState = "ACTIVE";
    String deletedState = "DELETED";
    String requestedToArchiveState ="REQUESTED_TO_ARCHIVE";
    String archivedState = "ARCHIVED";
    String completedState = "COMPLETED";
    //----------------------------------------------------------------------------------------------------------------------

    String getAllVolunteers = "ROLE_GET_ALL_VOLUNTEERS";
    String getAllVolunteersPublicInfo = "ROLE_GET_ALL_VOLUNTEERS_PUBLIC_INFO";
    String getVolunteersByMyBranchId = "ROLE_GET_VOLUNTEERS_BY_MY_BRANCH_ID";
    String getVolunteersPublicInfoByMyBranchId = "ROLE_GET_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID";
    String createVolunteer = "ROLE_CREATE_VOLUNTEER";
    String updateVolunteer = "ROLE_UPDATE_VOLUNTEER";
    String requestToArchiveVolunteer = "ROLE_REQUEST_TO_ARCHIVE_VOLUNTEER";
    String acceptToArchiveVolunteer = "ROLE_ACCEPT_TO_ARCHIVE_VOLUNTEER";
    String declineToArchiveVolunteer = "ROLE_DECLINE_TO_ARCHIVE_VOLUNTEER";
    String getAllActiveVolunteers = "ROLE_GET_ALL_ACTIVE_VOLUNTEERS";
    String getAllArchivedVolunteers = "ROLE_GET_ALL_ARCHIVED_VOLUNTEERS";
    String getAllActiveVolunteersPublicInfo = "ROLE_GET_ALL_ACTIVE_VOLUNTEERS_PUBLIC_INFO";
    String getAllArchivedVolunteersPublicInfo = "ROLE_GET_ALL_ARCHIVED_VOLUNTEERS_PUBLIC_INFO";
    String getAllRequestedToArchiveVolunteers = "ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_VOLUNTEERS";
    String getAllRequestedToArchiveVolunteersPublicInfo ="ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_VOLUNTEERS_PUBLIC_INFO" ;
    String getAllRequestedToArchiveVolunteersByMyBranchId = "ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_VOLUNTEERS_BY_MY_BRANCH_ID";
    String getAllRequestedToArchiveVolunteersPublicInfoByMyBranchId = "ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID";
    String getAllActiveVolunteersByMyBranchId = "ROLE_GET_ALL_ACTIVE_VOLUNTEERS_BY_MY_BRANCH_ID";
    String getAllArchivedVolunteersByMyBranchId = "ROLE_GET_ALL_ARCHIVED_VOLUNTEERS_BY_MY_BRANCH_ID";
    String getAllActiveVolunteersPublicInfoByMyBranchId = "ROLE_GET_ALL_ACTIVE_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID";
    String getAllArchivedVolunteersPublicInfoByMyBranchId = "ROLE_GET_ALL_ARCHIVED_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID";


    String getAllLeadVolunteers = "ROLE_GET_ALL_LEAD_VOLUNTEERS";
    String getAllLeadVolunteersPublicInfo = "ROLE_GET_ALL_LEAD_VOLUNTEERS_PUBLIC_INFO";
    String getLeadVolunteersByMyBranchId = "ROLE_GET_LEAD_VOLUNTEERS_BY_MY_BRANCH_ID";
    String getLeadVolunteersPublicInfoByMyBranchId = "ROLE_GET_LEAD_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID";
    String getAllActiveLeadVolunteers = "ROLE_GET_ALL_ACTIVE_LEAD_VOLUNTEERS";
    String getAllArchivedLeadVolunteers = "ROLE_GET_ALL_ARCHIVED_LEAD_VOLUNTEERS";
    String getAllActiveLeadVolunteersPublicInfo = "ROLE_GET_ALL_ACTIVE_LEAD_VOLUNTEERS_PUBLIC_INFO";
    String getAllArchivedLeadVolunteersPublicInfo = "ROLE_GET_ALL_ARCHIVED_LEAD_VOLUNTEERS_PUBLIC_INFO";
    String getAllRequestedToArchiveLeadVolunteers = "ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_LEAD_VOLUNTEERS";
    String getAllRequestedToArchiveLeadVolunteersPublicInfo ="ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_LEAD_VOLUNTEERS_PUBLIC_INFO" ;
    String getAllRequestedToArchiveLeadVolunteersByMyBranchId = "ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_LEAD_VOLUNTEERS_BY_MY_BRANCH_ID";
    String getAllRequestedToArchiveLeadVolunteersPublicInfoByMyBranchId = "ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_LEAD_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID";
    String getAllActiveLeadVolunteersByMyBranchId = "ROLE_GET_ALL_ACTIVE_LEAD_VOLUNTEERS_BY_MY_BRANCH_ID";
    String getAllArchivedLeadVolunteersByMyBranchId = "ROLE_GET_ALL_ARCHIVED_LEAD_VOLUNTEERS_BY_MY_BRANCH_ID";
    String getAllActiveLeadVolunteersPublicInfoByMyBranchId = "ROLE_GET_ALL_ACTIVE_LEAD_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID";
    String getAllArchivedLeadVolunteersPublicInfoByMyBranchId = "ROLE_GET_ALL_ARCHIVED_LEAD_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID";


    String getAllEvents = "ROLE_GET_ALL_EVENTS";
    String getEventsByMyBranchId = "ROLE_GET_EVENTS_BY_MY_BRANCH_ID";
    String addEvent="ROLE_ADD_EVENT";
    String archiveEvent = "ROLE_ARCHIVE_EVENT";
    String completeEvent = "ROLE_COMPLETE_EVENT";
    String updateEvent = "ROLE_UPDATE_EVENT";
    String getAssignedCalls="ROLE_GET_ASSIGNED_CALLS";
    String submitAssignedCalls="ROLE_SUBMIT_ASSIGNED_CALLS";
    String getAllActiveEvents = "ROLE_GET_ALL_ACTIVE_EVENTS";
    String getAllArchivedEvents = "ROLE_GET_ALL_ARCHIVED_EVENTS";
    String getAllCompletedEvents = "ROLE_GET_ALL_COMPLETED_EVENTS";
    String getAllActiveEventsByMyBranchId = "ROLE_GET_ALL_ACTIVE_EVENTS_BY_MY_BRANCH_ID";
    String getAllArchivedEventsByMyBranchId = "ROLE_GET_ALL_ARCHIVED_EVENTS_BY_MY_BRANCH_ID";
    String getAllCompletedEventsByMyBranchId = "ROLE_GET_ALL_COMPLETED_EVENTS_BY_MY_BRANCH_ID";

    String getAllActiveShareableEvents = "ROLE_GET_ALL_Active_Shareable_EVENTS";
    String getAllActiveShareableEventsByMyBranchId = "ROLE_GET_ALL_Active_Shareable_EVENTS_BY_MY_BRANCH_ID";
    String getAllCompletedShareableEvents = "ROLE_GET_ALL_Completed_Shareable_EVENTS";
    String getAllCompletedShareableEventsByMyBranchId = "ROLE_GET_ALL_Completed_Shareable_EVENTS_BY_MY_BRANCH_ID";

    String assignCalls="ROLE_ASSIGN_CALLS";

    String makeEventAttendance="ROLE_MAKE_EVENT_ATTENDANCE_TO_VOLUNTEER";


    //----------------------------------------------------------------------------------------------------------------------
    String addedSuccessfully = "Created Successfully";
    String completedSuccessfully = "Completed Successfully";
    String archivedSuccessfully = "Archived Successfully";
    String updatedSuccessfully = "Updated Successfully";
    String assignedSuccessfully ="Assigned successfully";
    String submittedSuccessfully="submitted Successfully";
    //----------------------------------------------------------------------------------------------------------------------
    String notFound = "Not Found";
    //----------------------------------------------------------------------------------------------------------------------
    String etisalat="ETISALAT";
    String vodafone ="VODAFONE";
    String orange="ORANGE";
    String we="WE";
    //----------------------------------------------------------------------------------------------------------------------
    String invitation="دعوه";
    String feedBack="تعليق";
    String notAttend="مكالمة الاكيد و مجاش";
    //----------------------------------------------------------------------------------------------------------------------
    String attendedTheEvent="حضر";
    String notAttendedTheEvent="لم يحضر";
    //----------------------------------------------------------------------------------------------------------------------
    String callEnsure="اكد";
    String callNotEnsure="لم يؤكد";

}
