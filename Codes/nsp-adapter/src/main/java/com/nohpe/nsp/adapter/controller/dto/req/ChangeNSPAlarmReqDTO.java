package com.nohpe.nsp.adapter.controller.dto.req;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@ApiModel("ChangeAlarmReqDTO")
@Data
@Accessors(chain = true)
public class ChangeNSPAlarmReqDTO {

    @ApiModelProperty(name="alarmId", dataType="String")
    private String alarmId;

    @ApiModelProperty(name="alarm state old value", dataType="String")
    private String oldValue;

    @ApiModelProperty(name="alarm state new value", dataType="String")
    private String newValue;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(name="alarm update timestamp", dataType="Date")
    private LocalDateTime update_time;
}
