package org.resala;

public interface StaticNames {
    String CEO = "CEO";
    String ViceCEO = "VICE_CEO";
    String TeamLeader = "TEAM_LEADER";
    String TeamMember = "TEAM_MEMBER";
    String normalVolunteer = "NORMAL_VOLUNTEER";
    String cloud = "CLOUD";
    String admin="ADMIN";
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
    String getVolunteersPublicInfoByMyBranch = "ROLE_GET_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH";
    String createVolunteer = "ROLE_CREATE_VOLUNTEER";
    String updateVolunteer = "ROLE_UPDATE_VOLUNTEER";
    String requestToArchiveVolunteer = "ROLE_REQUEST_TO_ARCHIVE_VOLUNTEER";
    String acceptToArchiveVolunteer = "ROLE_ACCEPT_TO_ARCHIVE_VOLUNTEER";
    String declineToArchiveVolunteer = "ROLE_DECLINE_TO_ARCHIVE_VOLUNTEER";

    String getAllVolunteersByState="ROLE_GET_ALL_VOLUNTEERS_BY_STATUS";
    String getAllVolunteersPublicInfoByState="ROLE_GET_ALL_VOLUNTEERS_PUBLIC_INFO_BY_STATUS";

    String getAllVolunteersByStateAndMyBranch="ROLE_GET_ALL_VOLUNTEERS_BY_STATUS_AND_MY_BRANCH";
    String getAllVolunteersPublicInfoByStateAndMyBranch="ROLE_GET_ALL_VOLUNTEERS_PUBLIC_INFO_BY_STATUS_AND_MY_BRANCH";


    String getAllLeadVolunteers = "ROLE_GET_ALL_LEAD_VOLUNTEERS";
    String getAllLeadVolunteersPublicInfo = "ROLE_GET_ALL_LEAD_VOLUNTEERS_PUBLIC_INFO";
    String getLeadVolunteersByMyBranchId = "ROLE_GET_LEAD_VOLUNTEERS_BY_MY_BRANCH_ID";
    String getLeadVolunteersPublicInfoByMyBranch = "ROLE_GET_LEAD_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH";

    String getAllLeadVolunteersByState="ROLE_GET_ALL_LEAD_VOLUNTEERS_BY_STATE";
    String getAllLeadVolunteersPublicInfoByState="ROLE_GET_ALL_LEAD_VOLUNTEERS_PUBLIC_INFO_BY_STATE";
    String getAllLeadVolunteersByStateAndMyBranch = "ROLE_GET_ALL_LEAD_VOLUNTEERS_BY_STATE_AND_MY_BRANCH";
    String getAllLeadVolunteersPublicInfoByStateAndMyBranch = "ROLE_GET_ALL_LEAD_VOLUNTEERS_PUBLIC_INFO_BY_STATE_AND_MY_BRANCH";

    String getAllEvents = "ROLE_GET_ALL_EVENTS";
    String getAllEventsByMyBranch = "ROLE_GET_EVENTS_BY_MY_BRANCH";
    String addEvent="ROLE_ADD_EVENT";
    String archiveEvent = "ROLE_ARCHIVE_EVENT";
    String completeEvent = "ROLE_COMPLETE_EVENT";
    String updateEvent = "ROLE_UPDATE_EVENT";
    String getAssignedCalls="ROLE_GET_ASSIGNED_CALLS";
    String submitAssignedCalls="ROLE_SUBMIT_ASSIGNED_CALLS";

    String getAllEventsByState = "ROLE_GET_All_EVENTS_BY_STATE";
    String getAllShareableEventsByState = "ROLE_GET_All_SHAREABLE_EVENTS_BY_STATE";
    String getAllEventsByStateAndMyBranch = "ROLE_GET_All_EVENTS_BY_STATE_AND_MY_BRANCH";
    String getAllShareableEventsByStateAndMyBranch = "ROLE_GET_All_SHAREABLE_EVENTS_BY_STATE_AND_MY_BRANCH";

    String assignCalls="ROLE_ASSIGN_CALLS";

    String makeEventAttendance="ROLE_MAKE_EVENT_ATTENDANCE_TO_VOLUNTEER";

    String createLeadVolunteer = "ROLE_CREATE_LEAD_VOLUNTEER";
    String getMyBranchCommitteeTeam ="ROLE_GET_MY_BRANCH_COMMITTEE_TEAM";
    String getNetworkTypeAssignedToVolunteer="ROLE_GET_NETWORK_TYPE_ASSIGNED_TO_VOLUNTEERS";

    String createCloud = "ROLE_CREATE_CLOUD";
    String generateVolunteersKPIs ="ROLE_GENERATE_VOLUNTEERS_KPIS";
    String generateLeadVolunteersKPIs ="ROLE_GENERATE_LEAD_VOLUNTEERS_KPIS";
    String generateEventsKPIs="ROLE_GENERATE_EVENTS_KPIS";

    String createUser="ROLE_CREATE_USER";
    String assignUser="ROLE_ASSIGN_USER";
    String createPrivilege="ROLE_CREATE_PRIVILEGES";
    String assignActionsToPrivilege="ROLE_ASSIGN_ACTIONS_TO_PRIVILEGES";

    //----------------------------------------------------------------------------------------------------------------------
    String addedSuccessfully = "Created Successfully";
    String completedSuccessfully = "Completed Successfully";
    String archivedSuccessfully = "Archived Successfully";
    String updatedSuccessfully = "Updated Successfully";
    String assignedSuccessfully ="Assigned successfully";
    String submittedSuccessfully="submitted Successfully";
    //----------------------------------------------------------------------------------------------------------------------
    String notFound = "Not Found";
    String callsHasBeenCreatedBefore="Calls has been created before";
    String thereIsNoCallsToBalance="There is no calls to balance";
    String thereIsNoAssignedCallsToConfirm="There is no assigned calls to confirm";
    //----------------------------------------------------------------------------------------------------------------------
    String volunteerType="Volunteer";
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
    String didNotAnswer="لم يرد";
    String doesNotCalled="لم يتم الاتصال بعد";
    String firstTimeCall="اول مره";
    //----------------------------------------------------------------------------------------------------------------------
    String hasTShirt ="عنده";
    String hasNoTShirt ="معندوش";
    String needTShirt ="عايز";

    enum gender{MALE,FEMALE}
}
