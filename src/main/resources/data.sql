INSERT ignore INTO `action` (`id`, `name`) VALUES ('1', 'ROLE_GET_ALL_VOLUNTEERS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('2', 'ROLE_GET_ALL_VOLUNTEERS_PUBLIC_INFO');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('3', 'ROLE_GET_VOLUNTEERS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('4', 'ROLE_GET_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('5', 'ROLE_CREATE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('6', 'ROLE_UPDATE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('7', 'ROLE_REQUEST_TO_ARCHIVE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('8', 'ROLE_ACCEPT_TO_ARCHIVE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('9', 'ROLE_DECLINE_TO_ARCHIVE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('10', 'ROLE_GET_ALL_ACTIVE_VOLUNTEERS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('11', 'ROLE_GET_ALL_ARCHIVED_VOLUNTEERS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('12', 'ROLE_GET_ALL_ACTIVE_VOLUNTEERS_PUBLIC_INFO');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('13', 'ROLE_GET_ALL_ARCHIVED_VOLUNTEERS_PUBLIC_INFO');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('14', 'ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_VOLUNTEERS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('15', 'ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_VOLUNTEERS_PUBLIC_INFO');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('16', 'ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_VOLUNTEERS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('17', 'ROLE_GET_ALL_REQUESTED_TO_ARCHIVE_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('18', 'ROLE_GET_ALL_ACTIVE_VOLUNTEERS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('19', 'ROLE_GET_ALL_ARCHIVED_VOLUNTEERS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('20', 'ROLE_GET_ALL_ACTIVE_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('21', 'ROLE_GET_ALL_ARCHIVED_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('22', 'ROLE_GET_ALL_EVENTS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('23', 'ROLE_GET_EVENTS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('24', 'ROLE_ADD_EVENT');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('25', 'ROLE_ARCHIVE_EVENT');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('26', 'ROLE_COMPLETE_EVENT');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('27', 'ROLE_UPDATE_EVENT');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('28', 'ROLE_GET_ALL_ACTIVE_EVENTS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('29', 'ROLE_GET_ALL_ARCHIVED_EVENTS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('30', 'ROLE_GET_ALL_COMPLETED_EVENTS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('31', 'ROLE_GET_ALL_ACTIVE_EVENTS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('32', 'ROLE_GET_ALL_ARCHIVED_EVENTS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('33', 'ROLE_GET_ALL_COMPLETED_EVENTS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('34', 'ROLE_ASSIGN_CALLS');



INSERT ignore INTO role (`id`,`name`) VALUE ('1','CEO');
INSERT ignore INTO role (`id`,`name`) VALUE ('2','VICE_CEO');
INSERT ignore INTO role (`id`,`name`) VALUE ('3','TEAM_LEADER');
INSERT ignore INTO role (`id`,`name`) VALUE ('4','TEAM_MEMBER');
INSERT ignore INTO role (`id`,`name`) VALUE ('5','NORMAL_VOLUNTEER');


INSERT ignore INTO privilege (`id`, `name`) VALUES ('1', 'CEO');
INSERT ignore INTO privilege (`id`, `name`) VALUES ('2', 'VICE_CEO');
INSERT ignore INTO privilege (`id`, `name`) VALUES ('3', 'TEAM_LEADER');
INSERT ignore INTO privilege (`id`, `name`) VALUES ('4', 'TEAM_MEMBER');
INSERT ignore INTO privilege (`id`, `name`) VALUES ('5', 'NORMAL_VOLUNTEER');


INSERT ignore INTO volunteer_status (`id`, `name`) VALUES ('1', 'ACTIVE');
INSERT ignore INTO volunteer_status (`id`, `name`) VALUES ('2', 'ARCHIVED');
INSERT ignore INTO volunteer_status (`id`, `name`) VALUES ('3', 'REQUESTED_TO_ARCHIVE');

INSERT ignore INTO event_status (`id`, `name`) VALUES ('1', 'ACTIVE');
INSERT ignore INTO event_status (`id`, `name`) VALUES ('2', 'ARCHIVED');
INSERT ignore INTO event_status (`id`, `name`) VALUES ('3', 'COMPLETED');


