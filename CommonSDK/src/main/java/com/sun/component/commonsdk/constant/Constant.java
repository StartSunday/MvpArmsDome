package com.sun.component.commonsdk.constant;

import android.os.Environment;

public class Constant {
    public static final String DOMAIN_NAME = "Domain-Name";
    public static final String HEADER_ACCEPT_CONTENT = "Accept: */*";
    public static final String HEADER_CONTENT_TYPE = "Content-type: application/json";

    // 是否登陆 标志位
    public static final String HAS_LOGIN = "has_login";
    public static final String USER_CARD_ID = "user_card_id";
    public static final String USER_SCHOOLID = "user_schoolId";
    public static final String USER_GRADEID = "user_gradeId";
    public static final String USER_CLASSID = "user_classId";
    public static final String USER_EQUIPMENTSTATUS = "user_equipmentStatus";
    public static final String USER_GRADENAME = "user_gradeName";
    public static final String USER_CLASSNUMBER = "user_classNumber";
    public static final String USER_USERNAME = "user_userName";
    public static final String USER_MOTTO = "user_motto";
    public static final String USER_SCHOOL_BADGE = "user_schoolBadge";
    public static final String USER_CLASS_ALIAS = "user_class_alias";
    public static final String TYPE = "type";
    public static final String ID = "id";
    public static final String POSITION = "position";
    public static final String LIST = "list";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String TIME = "time";
    public static int SCHOOL_HONOR = 1;
    public static String CLASS_MODE="classMode";

    public static final int PAGE_SIZE = 9; //一页显示行数
    public static final String VP_LEFT_TOP_POSITION = "vp_left_top_position";
    public static final String VP_LEFT_BOTTOM_POSITION = "vp_left_bottom_position";
    public static final String VP_RIGHT_BOTTOM_POSITION = "vp_right_bottom_position";
    public static final String TOKEN = "token";


    //寻物招领
    public static final String FLAG = "flag";
    public static final String CONTENT = "content";
    public static final String TITLE = "title";
    public static String AppFolder = Environment.getExternalStorageDirectory()
            + "/apk/";
//    public static final String ID= "id";
    //消息
    public static final String STUDENTID= "studentid";
    public static final String BEAN= "bean";
    public static final String CARDNO = "cardNo";
    public static String UPDATE_VERSION="update_version";
    //人脸
    public static final String FACEISOK="faceisok";
    public static final String FACEINPUT = "faceinput";

}