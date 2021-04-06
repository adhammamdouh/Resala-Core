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
    String requestedToActiveState ="REQUESTED_TO_ARCHIVE";
    String archivedState = "ARCHIVED";
    String completedState = "COMPLETED";
    //----------------------------------------------------------------------------------------------------------------------
    String getAllEvents = "ROLE_GET_ALL_EVENTS";
    String getEventsByMyBranchId = "ROLE_GET_EVENTS_BY_MY_BRANCH_ID";
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

    String getAllEvents = "ROLE_GET_ALL_EVENTS";
    String getEventsByMyBranchId = "ROLE_GET_EVENTS_BY_MY_BRANCH_ID";
    String addEvent="ROLE_ADD_EVENT";
    String archiveEvent = "ROLE_ARCHIVE_EVENT";
    String completeEvent = "ROLE_COMPLETE_EVENT";
    String updateEvent = "ROLE_UPDATE_EVENT";
    String getAssignedCalls="ROLE_GET_ASSIGNED_CALLS";
    String getAllActiveEvents = "ROLE_GET_ALL_ACTIVE_EVENTS";
    String getAllArchivedEvents = "ROLE_GET_ALL_ARCHIVED_EVENTS";
    String getAllCompletedEvents = "ROLE_GET_ALL_COMPLETED_EVENTS";
    String getAllActiveEventsByMyBranchId = "ROLE_GET_ALL_ACTIVE_EVENTS_BY_MY_BRANCH_ID";
    String getAllArchivedEventsByMyBranchId = "ROLE_GET_ALL_ARCHIVED_EVENTS_BY_MY_BRANCH_ID";
    String getAllCompletedEventsByMyBranchId = "ROLE_GET_ALL_COMPLETED_EVENTS_BY_MY_BRANCH_ID";

    String assignCalls="ROLE_ASSIGN_CALLS";



    //----------------------------------------------------------------------------------------------------------------------
    String addedSuccessfully = "Created Successfully";
    String completedSuccessfully = "CompletedSuccessfully";
    String archivedSuccessfully = "Archived Successfully";
    String updatedSuccessfully = "Updated Successfully";
    String callAssignedSuccessfully="Assigned successfully";
    //----------------------------------------------------------------------------------------------------------------------
    String notFound = "Not Found";
    String etisalat="ETISALAT";
    String vodavone="VODAVONE";
    String orange="ORANGE";
    String we="WE";
}
