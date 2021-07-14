INSERT ignore INTO `action` (`id`, `name`) VALUES ('1', 'ROLE_GET_ALL_VOLUNTEERS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('2', 'ROLE_GET_ALL_VOLUNTEERS_PUBLIC_INFO');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('3', 'ROLE_GET_VOLUNTEERS_BY_MY_BRANCH_ID');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('4', 'ROLE_GET_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('5', 'ROLE_CREATE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('6', 'ROLE_UPDATE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('7', 'ROLE_REQUEST_TO_ARCHIVE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('8', 'ROLE_ACCEPT_TO_ARCHIVE_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('9', 'ROLE_DECLINE_TO_ARCHIVE_VOLUNTEER');

INSERT ignore INTO `action` (`id`, `name`) VALUES ('10', 'ROLE_GET_ALL_VOLUNTEERS_BY_STATUS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('11', 'ROLE_GET_ALL_VOLUNTEERS_PUBLIC_INFO_BY_STATUS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('12', 'ROLE_GET_ALL_VOLUNTEERS_BY_STATUS_AND_MY_BRANCH');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('13', 'ROLE_GET_ALL_VOLUNTEERS_PUBLIC_INFO_BY_STATUS_AND_MY_BRANCH');


INSERT ignore INTO `action` (`id`, `name`) VALUES ('14', 'ROLE_GET_ALL_EVENTS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('15', 'ROLE_GET_EVENTS_BY_MY_BRANCH');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('16', 'ROLE_ADD_EVENT');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('17', 'ROLE_ARCHIVE_EVENT');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('18', 'ROLE_COMPLETE_EVENT');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('19', 'ROLE_UPDATE_EVENT');

INSERT ignore INTO `action` (`id`, `name`) VALUES ('20', 'ROLE_GET_All_EVENTS_BY_STATE');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('21', 'ROLE_GET_All_SHAREABLE_EVENTS_BY_STATE');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('22', 'ROLE_GET_All_EVENTS_BY_STATE_AND_MY_BRANCH');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('23', 'ROLE_GET_All_SHAREABLE_EVENTS_BY_STATE_AND_MY_BRANCH');

INSERT ignore INTO `action` (`id`, `name`) VALUES ('24', 'ROLE_GET_ALL_LEAD_VOLUNTEERS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('25', 'ROLE_GET_ALL_LEAD_VOLUNTEERS_PUBLIC_INFO');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('26', 'ROLE_GET_LEAD_VOLUNTEERS_BY_MY_BRANCH');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('27', 'ROLE_GET_LEAD_VOLUNTEERS_PUBLIC_INFO_BY_MY_BRANCH');

INSERT ignore INTO `action` (`id`, `name`) VALUES ('28', 'ROLE_GET_ALL_LEAD_VOLUNTEERS_BY_STATE');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('29', 'ROLE_GET_ALL_LEAD_VOLUNTEERS_PUBLIC_INFO_BY_STATE');

INSERT ignore INTO `action` (`id`, `name`) VALUES ('30', 'ROLE_GET_ALL_LEAD_VOLUNTEERS_BY_STATE_AND_MY_BRANCH');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('31', 'ROLE_GET_ALL_LEAD_VOLUNTEERS_PUBLIC_INFO_BY_STATE_AND_MY_BRANCH');

INSERT ignore INTO `action` (`id`, `name`) VALUES ('32', 'ROLE_ASSIGN_CALLS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('33', 'ROLE_GET_ASSIGNED_CALLS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('34', 'ROLE_SUBMIT_ASSIGNED_CALLS');

INSERT ignore INTO `action` (`id`, `name`) VALUES ('35', 'ROLE_MAKE_EVENT_ATTENDANCE_TO_VOLUNTEER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('36', 'ROLE_CREATE_LEAD_VOLUNTEER');

INSERT ignore INTO `action` (`id`, `name`) VALUES ('37', 'ROLE_GET_ALL_COMMITTEE_TEAM');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('38', 'ROLE_GET_NETWORK_TYPE_ASSIGNED_TO_VOLUNTEERS');



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


INSERT ignore INTO network_type (`id`, `name`) VALUES ('1', 'ETISALAT');
INSERT ignore INTO network_type (`id`, `name`) VALUES ('2', 'VODAFONE');
INSERT ignore INTO network_type (`id`, `name`) VALUES ('3', 'ORANGE');
INSERT ignore INTO network_type (`id`, `name`) VALUES ('4', 'WE');


INSERT ignore INTO call_type (`id`, `name`) VALUES ('1', 'دعوه');
INSERT ignore INTO call_type (`id`, `name`) VALUES ('2', 'تعليق');
INSERT ignore INTO call_type (`id`, `name`) VALUES ('3', 'مكالمة الاكيد و مجاش');

INSERT ignore INTO call_result (`id`, `name`) VALUES ('1', 'اكد');
INSERT ignore INTO call_result (`id`, `name`) VALUES ('2', 'لم يؤكد');
INSERT ignore INTO call_result (`id`, `name`) VALUES ('3', 'لم يرد');
INSERT ignore INTO call_result (`id`, `name`) VALUES ('4', 'لم يتم الاتصال بعد');
INSERT ignore INTO call_result (`id`, `name`) VALUES ('5', 'اول مره');

INSERT ignore INTO capital (`id`, `name`) VALUES ('1', 'القاهرة');
INSERT ignore INTO capital (`id`, `name`) VALUES ('2', 'الاسكندرية');
INSERT ignore INTO capital (`id`, `name`) VALUES ('3', 'الجيزة');
INSERT ignore INTO capital (`id`, `name`) VALUES ('4', 'شبرا الخيمة');
INSERT ignore INTO capital (`id`, `name`) VALUES ('5', 'بور سعيد');
INSERT ignore INTO capital (`id`, `name`) VALUES ('6', 'السويس');
INSERT ignore INTO capital (`id`, `name`) VALUES ('7', 'المحلة الكبرى');
INSERT ignore INTO capital (`id`, `name`) VALUES ('8', 'الاقصر');
INSERT ignore INTO capital (`id`, `name`) VALUES ('9', 'المنصورة');
INSERT ignore INTO capital (`id`, `name`) VALUES ('10', 'طنطا');
INSERT ignore INTO capital (`id`, `name`) VALUES ('11', 'اسيوط');
INSERT ignore INTO capital (`id`, `name`) VALUES ('12', 'الاسماعيلية');
INSERT ignore INTO capital (`id`, `name`) VALUES ('13', 'الفيوم');
INSERT ignore INTO capital (`id`, `name`) VALUES ('14', 'الزقازيق');
INSERT ignore INTO capital (`id`, `name`) VALUES ('15', 'دمياط');
INSERT ignore INTO capital (`id`, `name`) VALUES ('16', 'اسوان');
INSERT ignore INTO capital (`id`, `name`) VALUES ('17', 'المنيا');
INSERT ignore INTO capital (`id`, `name`) VALUES ('18', 'دمنهور');
INSERT ignore INTO capital (`id`, `name`) VALUES ('19', 'بنى سويف');
INSERT ignore INTO capital (`id`, `name`) VALUES ('20', 'الغردقة');
INSERT ignore INTO capital (`id`, `name`) VALUES ('21', 'قنا');
INSERT ignore INTO capital (`id`, `name`) VALUES ('22', 'سوهاج');
INSERT ignore INTO capital (`id`, `name`) VALUES ('23', 'شبين الكوم');
INSERT ignore INTO capital (`id`, `name`) VALUES ('24', 'بنها');
INSERT ignore INTO capital (`id`, `name`) VALUES ('25', 'العريش');


INSERT ignore INTO branch (`id`, `name`) VALUES ('1', 'حلوان');
INSERT ignore INTO branch (`id`, `name`) VALUES ('2', 'المعادى');
INSERT ignore INTO branch (`id`, `name`) VALUES ('3', 'المهندسين');
INSERT ignore INTO branch (`id`, `name`) VALUES ('4', 'فيصل');
INSERT ignore INTO branch (`id`, `name`) VALUES ('5', 'مصر الجديدة');
INSERT ignore INTO branch (`id`, `name`) VALUES ('6', 'مدينة نصر');
INSERT ignore INTO branch (`id`, `name`) VALUES ('7', 'اكتوبر');
INSERT ignore INTO branch (`id`, `name`) VALUES ('8', 'الاسكندرية');


INSERT ignore INTO committee (`id`, `name`) VALUES ('1', 'اسقف');
INSERT ignore INTO committee (`id`, `name`) VALUES ('2', 'مياه و بناء');
INSERT ignore INTO committee (`id`, `name`) VALUES ('3', 'عينى');
INSERT ignore INTO committee (`id`, `name`) VALUES ('4', 'مجددون');
INSERT ignore INTO committee (`id`, `name`) VALUES ('5', 'مسنين');
INSERT ignore INTO committee (`id`, `name`) VALUES ('6', 'اتش ار');
INSERT ignore INTO committee (`id`, `name`) VALUES ('7', 'تدريب');
INSERT ignore INTO committee (`id`, `name`) VALUES ('8', 'اتش ار متظوعين');
INSERT ignore INTO committee (`id`, `name`) VALUES ('9', 'استكشاف');
INSERT ignore INTO committee (`id`, `name`) VALUES ('10', 'براعم');
INSERT ignore INTO committee (`id`, `name`) VALUES ('11', 'محو اميه');
INSERT ignore INTO committee (`id`, `name`) VALUES ('12', 'اطعام');
INSERT ignore INTO committee (`id`, `name`) VALUES ('13', 'توعيه');
INSERT ignore INTO committee (`id`, `name`) VALUES ('14', 'دعايا');
INSERT ignore INTO committee (`id`, `name`) VALUES ('15', 'ميديا');
INSERT ignore INTO committee (`id`, `name`) VALUES ('16', 'مدير فرع');
INSERT ignore INTO committee (`id`, `name`) VALUES ('17', 'جوده و تطوير');
INSERT ignore INTO committee (`id`, `name`) VALUES ('18', 'نائب مدير تنفيذي');
INSERT ignore INTO committee (`id`, `name`) VALUES ('19', 'مدير تنفيذي');
INSERT ignore INTO committee (`id`, `name`) VALUES ('20', 'ولاد العم');
INSERT ignore INTO committee (`id`, `name`) VALUES ('21', 'مدير اداره');
INSERT ignore INTO committee (`id`, `name`) VALUES ('22', 'موظف');
INSERT ignore INTO committee (`id`, `name`) VALUES ('23', 'لجنه فنيه');

INSERT ignore INTO attendance_status (`id`, `name`) VALUES ('1', 'حضر');
INSERT ignore INTO attendance_status (`id`, `name`) VALUES ('2', 'لم يحضر');