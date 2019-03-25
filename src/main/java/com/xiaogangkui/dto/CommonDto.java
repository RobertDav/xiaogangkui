package com.xiaogangkui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Created by luchunyu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonDto implements Serializable{
    private int id;
    private int customerId;
    private String account;
    private String password;
    private int roleId;
    private int departmentId;
    private String departName;//部门名称
    private String name;//员工姓名
    private String englishName;
    private String position;//职位
    private String mobile;
    private String email;
    private String address;
    private String certificateContent;
    private String certificateUrl;
    private List<String> certificateUrlList;
    private int source;
    private int status;
    private int type;
    private int creator;
    private Date lastLoginTime;
    private int managerId;//部门主管id
    private String managerName;//主管姓名
    private String companyName;
    private String departmentName;
    private String departmentMemo;
}
