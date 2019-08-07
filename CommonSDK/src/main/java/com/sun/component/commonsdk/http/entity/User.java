package com.sun.component.commonsdk.http.entity;

/**
 * Created by ChenYuYun on 2018/8/10.
 * 用户信息
 */
public class User {

    /**
     * schoolId : 1735873773630742528
     * gradeId : 20
     * classId : 1762574245280178178
     * equipmentStatus : 1
     */
    private String id;
    private String schoolId;
    private String gradeId;
    private String classId;
    private int equipmentStatus;
    private String gradeName;
    private String classNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String userName;
    private String motto;//标语
    private String schoolBadge;//校徽
    private String classAlias;//别名

    public String getClassAlias() {
        return classAlias;
    }

    public void setClassAlias(String classAlias) {
        this.classAlias = classAlias;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getSchoolBadge() {
        return schoolBadge;
    }

    public void setSchoolBadge(String schoolBadge) {
        this.schoolBadge = schoolBadge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(int equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }
}
