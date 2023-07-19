package com.nohpe.pimet.utils.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@ApiModel(value = "ActionDTO", description = "Action information")
@Data
@Accessors(chain = true)
public class ActionDTO {

    @ApiModelProperty(name="entityId", dataType="Long", value="The entity Id that Action on")
    private Long entityId;

    @ApiModelProperty(name="source", dataType="String", value="The entity source")
    private String source;

    @ApiModelProperty(name="actionUser", dataType="String", value="The user performed this action")
    private String actionUser;

    @ApiModelProperty(name="actionTimeStamp", dataType="String", value="The timestamp performed this action")
    private LocalDateTime actionTimeStamp;
}
