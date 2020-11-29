package com.wjs.produce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author caohousong
 * @since 2020-11-26
 */
public class ActivityAreaResp     {


    private Long id;

    private String activityId;

    @ApiModelProperty(value = "所属县")
    private String area;

    @ApiModelProperty(value = "所属镇")
    private String town;

    @ApiModelProperty(value = "所属社区")
    private String community;


}
