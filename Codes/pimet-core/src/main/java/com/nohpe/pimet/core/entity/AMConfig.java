package com.nohpe.pimet.core.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AMConfig {

    String autoClearPolicy;
    String similarAlarmPolicy;

    String techDomain;
}
