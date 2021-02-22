INSERT ignore INTO `action` (`id`, `name`) VALUES ('1', 'ROLE_GET_ALL_EVENTS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('2', 'ROLE_GET_EVENTS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('3', 'ROLE_GET_ALL_VOLUNTEERS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('4', 'ROLE_GET_ALL_VOLUNTEERS_PUBLIC_INFO');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('5', 'ROLE_GET_VOLUNTEERS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('6', 'ROLE_CREATE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('7', 'ROLE_UPDATE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('8', 'ROLE_DELETE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('9', 'ROLE_ADD_EVENT');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('10', 'ROLE_ASSIGN_CALLS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('11', 'ROLE_DELETE_EVENT');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('12', 'ROLE_UPDATE_EVENT');

INSERT ignore INTO role (`id`,`name`) VALUE ('1','CEO');
INSERT ignore INTO role (`id`,`name`) VALUE ('2','TEAM_LEADER');
INSERT ignore INTO role (`id`,`name`) VALUE ('3','TEAM_MEMBER');
INSERT ignore INTO role (`id`,`name`) VALUE ('4','NORMAL_VOLUNTEER');

INSERT ignore INTO privilege (`id`, `name`) VALUES ('1', 'CEO');
INSERT ignore INTO privilege (`id`, `name`) VALUES ('2', 'TEAM_LEADER');
INSERT ignore INTO privilege (`id`, `name`) VALUES ('3', 'TEAM_MEMBER');
INSERT ignore INTO privilege (`id`, `name`) VALUES ('4', 'NORMAL_VOLUNTEER');

INSERT ignore INTO volunteer_status (`id`, `name`) VALUES ('1', 'ACTIVE');
INSERT ignore INTO volunteer_status (`id`, `name`) VALUES ('2', 'DELETED');
