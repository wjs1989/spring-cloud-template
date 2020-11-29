package com.wjs.produce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动管理
 * </p>
 *
 * @author caohousong
 * @since 2020-11-26
 */

public class ActivityManageUpdateReq {

    private Long id;

    private String activityName;

    private String type;

    private Date beginTime;
    private Date endTime;

    private String address;

    private Integer personCount=100;

    private Integer personCountTotal=41;

    private Date deadlineTime;

    private String birthdate;

    private String note;

    private String file;
    private String fileUrl;
    private List<ActivityAreaResp> areaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public Integer getPersonCountTotal() {
        return personCountTotal;
    }

    public void setPersonCountTotal(Integer personCountTotal) {
        this.personCountTotal = personCountTotal;
    }

    public Date getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(Date deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<ActivityAreaResp> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<ActivityAreaResp> areaList) {
        this.areaList = areaList;
    }
}
