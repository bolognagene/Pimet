package com.nohpe.pimet.utils.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Node("Node")
@TableName("inventory")
public class NE {

    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Property("node_name")
    private String nodeName;

    private String country;

    private String province;

    private String city;

    private String address;

    private String district;

    @Property("ne_type")
    private String neType;

    @Property("ne_status")
    private String neStatus;

    @Property("ne_level")
    private String neLevel;

    private String source;

    private String technology;

    @Property("sub_tech")
    private String subTech;

}
