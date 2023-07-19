package com.nohpe.pimet.utils.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "NEDTO", description = "NE information")
@Data
@Accessors(chain = true)
public class NEDTO {

    @ApiModelProperty(name="nodeName", dataType="String", value="use this field to query in inventory table to get detailed NE information.")
    private String nodeName;

    @ApiModelProperty(name="country", dataType="String")
    private String country;

    @ApiModelProperty(name="province", dataType="String")
    private String province;

    @ApiModelProperty(name="city", dataType="String")
    private String city;

    @ApiModelProperty(name="address", dataType="String")
    private String address;

    @ApiModelProperty(name="district", dataType="String")
    private String district;

    @ApiModelProperty(name="neType", dataType="String")
    private String neType;

    @ApiModelProperty(name="neStatus", dataType="String")
    private String neStatus;

    @ApiModelProperty(name="neLevel", dataType="String")
    private String neLevel;

    @ApiModelProperty(name="source", dataType="String")
    private String source;

    @ApiModelProperty(name="technology", dataType="String")
    private String technology;

    @ApiModelProperty(name="subTech", dataType="String")
    private String subTech;
}
