package com.nohpe.pimet.utils.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nohpe.pimet.utils.enums.TTSeverity;
import com.nohpe.pimet.utils.enums.TTStatus;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@TableName("trouble_ticket")
public class TT {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String alarmId;

    private TTStatus ttStatus;

    private TTSeverity ttSeverity;

    private String ttDesc;

    private String assigneeName;

    private String assigneePhone;

    private String assigneeMail;

    private LocalDateTime create_time;

    private LocalDateTime  update_time;

}
