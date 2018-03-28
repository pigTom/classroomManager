package org.study.classroom.utils;

public class Constants {
    public static final String ID_EMPTY = "账号不能为空";
    public static final String USERNAME_EMPTY = "用户名不能为空";
    public static final String PASSWORD_EMPTY = "密码不能为空";
    public static final String USER_TITLE_ERROR = "员工职称错误";
    public static final String ID_ERROR = "账号错误";
    public static final String USERNAME_ERROR = "用户名错误";
    public static final String PASSWORD_ERROR = "密码错误";
    public static final String LOGIN_OK = "登录成功";
    public static final Integer PAGE_NUM = 10;
    public static final String DEFAULT_PASSWORD = "123456";
    public static final String ADMIN_BASE_PATH = "WEB-INF/jsp/admin/";
    public static final String USER_BASE_PATH = "WEB-INF/jsp/user/";
    // admin

    public static final String STUDENT_PAGES = "student_pages";
    public static final String TEACHER_PAGES = "teacher_pages";
    public static final String CLASSROOM_PAGES = "classroom_pages";
    public static final String COURSE_PAGES = "course_pages";


    public static final String STUDENT_PATH = ADMIN_BASE_PATH + "students";
    public static final String TEACHER_PATH = ADMIN_BASE_PATH + "teachers";
    public static final String CLASSROOM_PATH = ADMIN_BASE_PATH + "classrooms";
    public static final String COURSE_PATH = ADMIN_BASE_PATH + "course";
    public static final String NOTICE_PATH = ADMIN_BASE_PATH + "notice";
    public static final String NEW_NOTICE_PATH = ADMIN_BASE_PATH + "new_notice";

    // user
    public static final String USER_COURSE_PAGES = "user_course_pages";
    public static final String USER_CLASSROOM_PAGES = "user_classroom_pages";
    public static final String USER_ACTIVITY_PAGES = "user_activity_pages";


    public static final String USER_COURSE_PATH = USER_BASE_PATH + "course";
    public static final String USER_CLASSROOM_PATH = USER_BASE_PATH + "classrooms";
    public static final String USER_ORDER_PATH = USER_BASE_PATH + "order";
    public static final String USER_PASSWORD_PATH = USER_BASE_PATH + "password";
    public static final String USER_INFORMATION_PATH = USER_BASE_PATH + "information";
    public static final String USER_ACTIVITY_PATH = USER_BASE_PATH +"activity";
    public static final String USER_NOTICE_PATH = USER_BASE_PATH + "notice";


    // common
    public static final String NOTICE_PAGE = "notice_pages";
}

