package com.nohpe.pimet.utils.entity;

import com.nohpe.pimet.utils.enums.EventType;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder(toBuilder = true)
public class Event  implements Serializable  {

    private static final long serialVersionUID = 1L;

    private EventType eventType;

    private String source;

    Alarm alarm;

    ChangeAlarmStatus changeAlarmStatus;

}

