package com.nohpe.pimet.fronted.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nohpe.pimet.utils.entity.Alarm;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class ParseQueryCondition {

    static public QueryWrapper<Alarm> addQueryCondition(String condition, QueryWrapper<Alarm> queryWrapper) {

        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<Alarm>();
        }

        if (condition.isEmpty()) {
            return queryWrapper;
        }

        // splits[0] is the attribute name
        // splits[1] is the operator
        // splits[2] is the value
        String[] splits = condition.split(",");

        if(splits.length >= 2) {
            switch (splits[1].toLowerCase()) {
                case "eq":
                    queryWrapper.eq(splits[0], splits[2]);
                    break;
                case "like":
                    queryWrapper.like(splits[0], splits[2]);
                    break;
                case "ge":
                    queryWrapper.ge(splits[0], splits[2]);
                    break;
                case "le":
                    queryWrapper.le(splits[0], splits[2]);
                    break;
                case "gt":
                    queryWrapper.gt(splits[0], splits[2]);
                    break;
                case "lt":
                    queryWrapper.lt(splits[0], splits[2]);
                    break;
                case "likeleft":
                    queryWrapper.likeLeft(splits[0], splits[2]);
                    break;
                case "likeright":
                    queryWrapper.likeRight(splits[0], splits[2]);
                    break;
                case "in":
                    List<String> values = new ArrayList<>();
                    for (int i=2; i<splits.length; i++) {
                        values.add(splits[i]);
                    }
                    queryWrapper.in(splits[0], values);
                    break;
                case "ne":
                    queryWrapper.ne(splits[0], splits[2]);
                    break;
                case "not.isnull":
                    queryWrapper.isNotNull(splits[0]);
                    break;
                case "asc":
                    queryWrapper.orderByAsc(splits[0]);
                    break;
                case "desc":
                    queryWrapper.orderByDesc(splits[0]);
                    break;
                default:
                    break;
            }
        }

        return queryWrapper;
    }
}
