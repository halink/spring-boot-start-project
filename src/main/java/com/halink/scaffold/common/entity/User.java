package com.halink.scaffold.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 */
@ApiModel(value = "用户信息表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "id")
    private Integer userId;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "区号")
    private String areaCode;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "头像")
    private String photo;
    @ApiModelProperty(value = "性别 0.未知 1.男 2.女")
    private Byte gender;
    @ApiModelProperty(value = "出生年月日")
    private Date birthday;
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
    @ApiModelProperty(value = "修改时间")
    private Date updatedAt;
    @ApiModelProperty(value = "最后一次登录时间")
    private Date lastLoginAt;
}