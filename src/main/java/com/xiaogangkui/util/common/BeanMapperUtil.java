package com.xiaogangkui.util.common;


import com.google.common.collect.Lists;
import org.dozer.Mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Created by luchunyu
 */
public class BeanMapperUtil {


    private Mapper mapper;

    public <T> T transForm(Object o, Class<T> clazz) {
        if (Objects.nonNull(o)) {
            T t = null;
            try {
                t = clazz.newInstance();
                mapper.map(o, t);
                return t;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setMapper(Mapper mapper){
        this.mapper = mapper;
    }

    public <T, K> List<T> batchMapper(List<K> list, Class<T> clazz) {
        if (org.springframework.util.CollectionUtils.isEmpty(list)) return Lists.newArrayList();
        return list.stream().map(entity -> transForm(entity, clazz)).collect(Collectors.toList());
    }
}
