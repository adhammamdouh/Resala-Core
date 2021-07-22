ALTER DATABASE `resalacore` CHARACTER SET utf8 COLLATE utf8_general_ci;

SET @sql = (SELECT IF(
    (SELECT COUNT(INDEX_NAME)
        FROM INFORMATION_SCHEMA.statistics WHERE
        INDEX_NAME='uq_name_org_id'
    ) <> 0,
    "SELECT 0",
    "alter table privilege add constraint uq_name_org_id unique (name,organization_id);"
));

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
set @sql=NULL;



INSERT ignore INTO `organization` (`id`, `name`,`domain_name`) VALUES ('1', 'Resala','@resala.org');

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

INSERT ignore INTO `action` (`id`, `name`) VALUES ('37', 'ROLE_GET_MY_BRANCH_COMMITTEE_TEAM');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('38', 'ROLE_GET_NETWORK_TYPE_ASSIGNED_TO_VOLUNTEERS');

INSERT ignore INTO `action` (`id`, `name`) VALUES ('39', 'ROLE_CREATE_CLOUD');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('40', 'ROLE_GENERATE_VOLUNTEERS_KPIS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('41', 'ROLE_GENERATE_LEAD_VOLUNTEERS_KPIS');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('42', 'ROLE_GENERATE_EVENTS_KPIS');

INSERT ignore INTO `action` (`id`, `name`) VALUES ('43', 'ROLE_CREATE_USER');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('44', 'ROLE_ASSIGN_USER_PRIVILEGES');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('45', 'ROLE_CREATE_PRIVILEGES');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('46', 'ROLE_ASSIGN_ACTIONS_TO_PRIVILEGES');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('47', 'ROLE_GET_ALL_BRANCHES');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('48', 'ROLE_GET_ALL_COMMITTEES');
INSERT ignore INTO `action` (`id`, `name`) VALUES ('49', 'ROLE_GET_VOLUNTEER_BY_PHONE_NUMBER');

INSERT ignore INTO role (`id`,`name`) VALUE ('1','CEO');
INSERT ignore INTO role (`id`,`name`) VALUE ('2','VICE_CEO');
INSERT ignore INTO role (`id`,`name`) VALUE ('3','TEAM_LEADER');
INSERT ignore INTO role (`id`,`name`) VALUE ('4','TEAM_MEMBER');
INSERT ignore INTO role (`id`,`name`) VALUE ('5','NORMAL_VOLUNTEER');
INSERT ignore INTO role (`id`, `name`) VALUES ('6', 'CLOUD');
INSERT ignore INTO role (`id`, `name`) VALUES ('7', 'ADMIN');


INSERT ignore INTO privilege (`id`, `name`,`organization_id`) VALUES ('1', 'CEO','1');
INSERT ignore INTO privilege (`id`, `name`,`organization_id`) VALUES ('2', 'VICE_CEO','1');
INSERT ignore INTO privilege (`id`, `name`,`organization_id`) VALUES ('3', 'TEAM_LEADER','1');
INSERT ignore INTO privilege (`id`, `name`,`organization_id`) VALUES ('4', 'TEAM_MEMBER','1');
INSERT ignore INTO privilege (`id`, `name`,`organization_id`) VALUES ('5', 'NORMAL_VOLUNTEER','1');
INSERT ignore INTO privilege (`id`, `name`,`organization_id`) VALUES ('6', 'CLOUD','1');
INSERT ignore INTO privilege (`id`, `name`,`organization_id`) VALUES ('7', 'ADMIN','1');


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
INSERT ignore INTO call_result (`id`, `name`) VALUES ('2', 'احتمال');
INSERT ignore INTO call_result (`id`, `name`) VALUES ('3', 'رفض');
INSERT ignore INTO call_result (`id`, `name`) VALUES ('4', 'لم يرد');
INSERT ignore INTO call_result (`id`, `name`) VALUES ('5', 'الرقم غلط');
INSERT ignore INTO call_result (`id`, `name`) VALUES ('6', 'مغلق');
INSERT ignore INTO call_result (`id`, `name`) VALUES ('7', 'غير متاح');
INSERT ignore INTO call_result (`id`, `name`) VALUES ('8', 'اول مره');

INSERT ignore INTO capital (`id`, `name`) VALUES ('1', 'القاهرة');
INSERT ignore INTO capital (`id`, `name`) VALUES ('2', 'الاسكندرية');
INSERT ignore INTO capital (`id`, `name`) VALUES ('3', 'الجيزة');
INSERT ignore INTO capital (`id`, `name`) VALUES ('4', 'اسوان');
INSERT ignore INTO capital (`id`, `name`) VALUES ('5', 'اسيوط');
INSERT ignore INTO capital (`id`, `name`) VALUES ('6', 'البحيرة');
INSERT ignore INTO capital (`id`, `name`) VALUES ('7', 'الإسماعيلية');
INSERT ignore INTO capital (`id`, `name`) VALUES ('8', 'الاقصر');
INSERT ignore INTO capital (`id`, `name`) VALUES ('9', 'البحر الأحمر');
INSERT ignore INTO capital (`id`, `name`) VALUES ('10', 'بني سويف');
INSERT ignore INTO capital (`id`, `name`) VALUES ('11', 'بورسعيد');
INSERT ignore INTO capital (`id`, `name`) VALUES ('12', 'جنوب سيناء');
INSERT ignore INTO capital (`id`, `name`) VALUES ('13', 'الدقهلية');
INSERT ignore INTO capital (`id`, `name`) VALUES ('14', 'دمياط');
INSERT ignore INTO capital (`id`, `name`) VALUES ('15', 'سوهاج');
INSERT ignore INTO capital (`id`, `name`) VALUES ('16', 'السويس');
INSERT ignore INTO capital (`id`, `name`) VALUES ('17', 'الشرقية');
INSERT ignore INTO capital (`id`, `name`) VALUES ('18', 'شمال سيناء');
INSERT ignore INTO capital (`id`, `name`) VALUES ('19', 'الغربية');
INSERT ignore INTO capital (`id`, `name`) VALUES ('20', 'الفيوم');
INSERT ignore INTO capital (`id`, `name`) VALUES ('21', 'القليوبية');
INSERT ignore INTO capital (`id`, `name`) VALUES ('22', 'قنا');
INSERT ignore INTO capital (`id`, `name`) VALUES ('23', 'كفر الشيخ');
INSERT ignore INTO capital (`id`, `name`) VALUES ('24', 'مطروح');
INSERT ignore INTO capital (`id`, `name`) VALUES ('25', 'المنوفية');
INSERT ignore INTO capital (`id`, `name`) VALUES ('26', 'المنيا');
INSERT ignore INTO capital (`id`, `name`) VALUES ('27', 'الوادي الجديد');


INSERT ignore INTO branch (`id`, `name`,`organization_id`) VALUES ('1', 'حلوان','1');
INSERT ignore INTO branch (`id`, `name`,`organization_id`) VALUES ('2', 'المعادى','1');
INSERT ignore INTO branch (`id`, `name`,`organization_id`) VALUES ('3', 'المهندسين','1');
INSERT ignore INTO branch (`id`, `name`,`organization_id`) VALUES ('4', 'فيصل','1');
INSERT ignore INTO branch (`id`, `name`,`organization_id`) VALUES ('5', 'مصر الجديدة','1');
INSERT ignore INTO branch (`id`, `name`,`organization_id`) VALUES ('6', 'مدينة نصر','1');
INSERT ignore INTO branch (`id`, `name`,`organization_id`) VALUES ('7', 'اكتوبر','1');
INSERT ignore INTO branch (`id`, `name`,`organization_id`) VALUES ('8', 'الاسكندرية','1');


INSERT ignore INTO committee (`id`, `name`) VALUES ('1', 'اسقف');
INSERT ignore INTO committee (`id`, `name`) VALUES ('2', 'مياه و بناء');
INSERT ignore INTO committee (`id`, `name`) VALUES ('3', 'عينى');
INSERT ignore INTO committee (`id`, `name`) VALUES ('4', 'مجددون');
INSERT ignore INTO committee (`id`, `name`) VALUES ('5', 'مسنين');
INSERT ignore INTO committee (`id`, `name`) VALUES ('6', 'اتش ار');
INSERT ignore INTO committee (`id`, `name`) VALUES ('7', 'تدريب');
INSERT ignore INTO committee (`id`, `name`) VALUES ('8', 'اتش ار متطوعين');
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
INSERT ignore INTO attendance_status (`id`, `name`) VALUES ('2', 'اعتذر');
INSERT ignore INTO attendance_status (`id`, `name`) VALUES ('3', 'لم يحضر');


INSERT ignore INTO education_level (`id`, `name`) VALUES ('1', 'اعدادى');
INSERT ignore INTO education_level (`id`, `name`) VALUES ('2', 'ثانوى عام');
INSERT ignore INTO education_level (`id`, `name`) VALUES ('3', 'ثانوى مهنى');
INSERT ignore INTO education_level (`id`, `name`) VALUES ('4', 'جامعة');
INSERT ignore INTO education_level (`id`, `name`) VALUES ('5', 'معهد');
INSERT ignore INTO education_level (`id`, `name`) VALUES ('6', 'خريج');

INSERT ignore INTO shirt (`id`, `name`) VALUES ('1', 'عنده');
INSERT ignore INTO shirt (`id`, `name`) VALUES ('2', 'معندوش');
INSERT ignore INTO shirt (`id`, `name`) VALUES ('3', 'عايز');

INSERT ignore INTO user_type (`id`, `name`) VALUES ('1', 'Volunteer');
INSERT ignore INTO user_type (`id`, `name`) VALUES ('2', 'Cloud');


INSERT ignore INTO `user` (`id`, `user_name`,`password`,`user_type_id`) VALUES ('1', 'admin@resala.org','$2a$10$ndi/m2LvXj5NsT/OgY/sQ.1fAb4N4sPAy85tdLqe9AXrqJrmi/nGS','1');

--INSERT ignore INTO `admin`  VALUES ('1', '1','1','1');