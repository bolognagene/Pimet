package com.nohpe.nsp.adapter.controller.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@ApiModel(value = "CreateAlarmReqDTO", description = "Alarm from NSP service")
@Data
@Accessors(chain = true)
public class CreateNSPAlarmReqDTO {

    @ApiModelProperty(name="agentEntity", dataType="String", value="NSP Alarm Agent Entity, will be mapped to Alarm MO")
    private String agentEntity;

    @ApiModelProperty(name="alarmId", dataType="String", value="NSP Alarm Agent ID, will be mapped to Alarm 'Alarm Agent ID'")
    private String alarmId;

    @ApiModelProperty(name="alarmType", dataType="String")
    private String  alarmType;

    @ApiModelProperty(name="event_time", dataType="Date")
    private LocalDateTime event_time;

    @ApiModelProperty(name="probableCause", dataType="String")
    private String probableCause;

    @ApiModelProperty(name="perceivedSeverity", dataType="String")
    private String perceivedSeverity;

    @ApiModelProperty(name="specificProblem", dataType="String")
    private String specificProblem;

    @ApiModelProperty(name="nodeName", dataType="String")
    private String nodeName;

    @ApiModelProperty(name="additionalText", dataType="String")
    private String additionalText;
}
