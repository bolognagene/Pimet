CREATE DATABASE pimet;

USE pimet;

CREATE TABLE `realtime_alarm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'alarm ID, primary key',
  `alarm_id` varchar(32) DEFAULT NULL COMMENT 'Alarm Identifier',
  `managed_object` varchar(64)  NOT NULL COMMENT 'managed object',
  `perceived_severity` varchar(16) NOT NULL COMMENT 'perceived severity',
  `event_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'event time',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create timestamp in database',
  `alarm_type` varchar(32) NOT NULL COMMENT 'alarm type',
  `probable_cause` varchar(32) NOT NULL COMMENT 'probable cause',
  `specific_problem` varchar(32) NOT NULL COMMENT 'specific problem',
  `notification_id` varchar(32) DEFAULT NULL COMMENT 'notification id',
  `alarm_agent_id` varchar(32) DEFAULT NULL COMMENT 'alarm agent id',
  `additional_text` varchar(255) DEFAULT NULL COMMENT 'additional text',
  `node_name` varchar(32) NOT NULL COMMENT 'node name',
  `source` varchar(32) NOT NULL COMMENT 'which AM this alarm is from',
  `country` varchar(32) DEFAULT NULL COMMENT 'country',
  `province` varchar(32) DEFAULT NULL COMMENT 'province',
  `city` varchar(32) DEFAULT NULL COMMENT 'city',
  `region` varchar(32) DEFAULT NULL COMMENT 'region',
  `address` varchar(64) DEFAULT NULL COMMENT 'address',
  `ne_type` varchar(32) DEFAULT NULL COMMENT 'neType',
  `ne_status` varchar(32) DEFAULT NULL COMMENT 'neStatus',
  `ne_level` varchar(32) DEFAULT NULL COMMENT 'neLevel',
  `parents` varchar(32) DEFAULT NULL COMMENT 'parents',
  `children` varchar(32) DEFAULT NULL COMMENT 'children',
  `technology` varchar(32) DEFAULT NULL COMMENT 'technology, example: RAN',
  `sub_tech` varchar(32) DEFAULT NULL COMMENT 'sub technology, example: 5G',
  `handled_by` varchar(32) DEFAULT NULL COMMENT 'handled by which tt',
  `state` varchar(32) DEFAULT 'OUTSTANDING' NOT NULL COMMENT 'Alarm State: OUTSTANDING, ACKNOWLEDGED, TERMINATED',
  `problem_status` varchar(32) DEFAULT 'NOT_HANDLED' NOT NULL COMMENT 'Alarm Problem Status: NOT_HANDLED, HANDLED, CLOSED',
  `tech_domain` varchar(32) DEFAULT NULL COMMENT 'Technology domain, like RAN, Core',
  `last_update_time` datetime DEFAULT NULL COMMENT 'last update timestamp',
  `acknowledged_user` varchar(32) DEFAULT NULL COMMENT 'Acknowledged User ID',
  `acknowledged_time` datetime DEFAULT NULL COMMENT 'Acknowledged timestamp',
  `termination_user` varchar(32) DEFAULT NULL COMMENT 'Termination user',
  `termination_time` datetime DEFAULT NULL COMMENT 'Termination timestamp',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='realtime alarm table';

CREATE TABLE `inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID, primary key',
  `node_name` varchar(32) NOT NULL COMMENT 'node name',
  `country` varchar(32) DEFAULT NULL COMMENT 'country',
  `province` varchar(32) DEFAULT NULL COMMENT 'province',
  `city` varchar(32) DEFAULT NULL COMMENT 'city',
  `address` varchar(64) DEFAULT NULL COMMENT 'address',
  `district` varchar(64) DEFAULT NULL COMMENT 'district',
  `ne_type` varchar(32) DEFAULT NULL COMMENT 'neType',
  `ne_status` varchar(32) DEFAULT NULL COMMENT 'neStatus',
  `ne_level` varchar(32) DEFAULT NULL COMMENT 'neLevel',
  `source` varchar(32) DEFAULT NULL COMMENT 'source',
  `technology` varchar(32) DEFAULT NULL COMMENT 'technology, example: RAN',
  `sub_tech` varchar(32) DEFAULT NULL COMMENT 'sub technology, example: 5G',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='inventory table';

CREATE TABLE `trouble_ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'TT ID, primary key',
  `alarm_id` varchar(64) NOT NULL COMMENT 'source + id',
  `tt_status` varchar(32) NOT NULL COMMENT 'tt status',
  `tt_severity` varchar(32) NOT NULL COMMENT 'tt severity',
  `tt_desc` varchar(255) DEFAULT NULL COMMENT 'tt description',
  `assignee_name` varchar(32) NOT NULL COMMENT 'assignee name who will take care this issue',
  `assignee_phone` varchar(32) NOT NULL COMMENT 'assignee phone',
  `assignee_mail` varchar(32) NOT NULL COMMENT 'assignee mail',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create timestamp in database',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update timestamp',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='trouble ticket table';

CREATE TABLE `maintenance_employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID, primary key',
  `country` varchar(64) NOT NULL COMMENT 'country',
  `province` varchar(32) NOT NULL COMMENT 'province',
  `city` varchar(32) NOT NULL COMMENT 'city',
  `region` varchar(32) NOT NULL COMMENT 'region',
  `technology` varchar(32) NOT NULL COMMENT 'technology, example: RAN',
  `sub_tech` varchar(32) DEFAULT NULL COMMENT 'sub technology, example: 5G',
  `employee_name` varchar(32) NOT NULL COMMENT 'employee name who will take care this issue',
  `employee_phone` varchar(32) NOT NULL COMMENT 'employee phone',
  `employee_mail` varchar(32) NOT NULL COMMENT 'employee mail',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='maintenance employee table';