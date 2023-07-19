package com.nohpe.pimet.utils.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nohpe.pimet.utils.enums.AlarmState;
import com.nohpe.pimet.utils.enums.AlarmType;
import com.nohpe.pimet.utils.enums.PerceivedSeverity;
import com.nohpe.pimet.utils.enums.ProblemStatus;
import lombok.*;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@TableName("realtime_alarm")
public class Alarm {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long alarmId;

    private String managedObject;

    private PerceivedSeverity perceivedSeverity;

    private LocalDateTime  eventTime;

    private LocalDateTime createTime;

    private AlarmType alarmType;

    private String probableCause;

    private String specificProblem;

    private AlarmState state;

    private ProblemStatus problemStatus;

    private String notificationId;

    private String alarmAgentId;

    private String additionalText;

    private String source;

    private String nodeName;

    private String country;

    private String province;

    private String city;

    private String district;

    private String address;

    private String neType;

    private String neStatus;

    private String neLevel;

    private String parents;

    private String children;

    private String technology;

    private String subTech;

    private String handledBy;

    private String techDomain;

    private String acknowledgedUser;

    private LocalDateTime acknowledgedTime;

    private String terminationUser;

    private LocalDateTime terminationTime;

    private LocalDateTime lastUpdateTime;


}

