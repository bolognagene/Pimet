package com.nohpe.pimet.core.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "alarm-handling-configs")
public class AMConfigs {

    private Map<String, AMConfig> sources = new HashMap<String, AMConfig>();

    public void setSources(Map<String, AMConfig> alarmHandlingConfigMap) {
        this.sources = alarmHandlingConfigMap;
    }

    public Map<String, AMConfig> getSources() {
        return sources;
    }
}
