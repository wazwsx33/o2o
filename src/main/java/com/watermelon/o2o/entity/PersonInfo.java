package com.watermelon.o2o.entity;

import java.util.Date;

/**
 * @Description: 用户  实体类
 * @Author; Watermelon
 * @Date: 2018/11/6 19:05
 */
public class PersonInfo {

    //ID
    private Long userId;
    //姓名
    private String name;
    //头像地址
    private String profileImg;
    //邮箱
    private String email;
    //性别
    private String gender;
    //用户状态
    private String enableStatus;
    //身份标识，1、顾客，2、店家，3、管理员
    private Integer userType;
    //创建时间
    private Date crateTime;
    //上次修改时间
    private Date lastEditTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}
