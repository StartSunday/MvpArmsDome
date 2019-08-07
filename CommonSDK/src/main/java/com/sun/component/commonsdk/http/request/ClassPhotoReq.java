package com.sun.component.commonsdk.http.request;

import com.jess.arms.http.Pager;

/**
 * Created by ChenYuYun on 2018/9/20.
 * 班级相册
 * <p>
 * imgType=1获取的是荣誉照片,imgType=2获取的是普通照片
 */
public class ClassPhotoReq {
    public static final int HONER = 2; //荣誉照片
    public static final int PHOTO = 1; //普通照片
    public static final int WORK = 3; //作品照片
    public String classId;
    public String schoolId;
    public int imgType;
    public Pager pager;
}
