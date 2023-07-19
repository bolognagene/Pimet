package com.nohpe.pimet.utils.entity.dto;

import com.nohpe.pimet.utils.enums.TTSeverity;
import com.nohpe.pimet.utils.enums.TTStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value = "TTDTO", description = "TT information")
@Data
@Accessors(chain = true)
public class TTDTO {

    @ApiModelProperty(name="alarmId", dataType="String")
    private String alarmId;

    @ApiModelProperty(name="ttStatus", dataType="TTStatus")
    private TTStatus ttStatus;

    @ApiModelProperty(name="ttSeverity", dataType="TTSeverity")
    private TTSeverity ttSeverity;

    @ApiModelProperty(name="ttDesc", dataType="String")
    private String ttDesc;

    @ApiModelProperty(name="assigneeName", dataType="String")
    private String assigneeName;

    @ApiModelProperty(name="assigneePhone", dataType="String")
    private String assigneePhone;

    @ApiModelProperty(name="assigneeMail", dataType="String")
    private String assigneeMail;
}
