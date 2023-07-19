package com.nohpe.pimet.utils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAlarmStatus {

    private String keyField;

    private String oldValue;

    private String newValue;

    private LocalDateTime update_time;
}
