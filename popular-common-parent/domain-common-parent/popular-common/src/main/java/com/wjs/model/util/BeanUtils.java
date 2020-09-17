package com.wjs.model.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import java.util.Collections;
import java.util.List;

public class BeanUtils extends org.springframework.beans.BeanUtils{


    /**
     * 封装{@link org.apache.commons.collections.CollectionUtils#collect}方法和{@link org.springframework.beans.BeanUtils#copyProperties}方法，常用与批量将Bean转换为DTO
     *
     *<pre>
     *     List<UserBean> userBeans = userDao.queryUsers();
     *     List<UserDTO> userDTOs = BeanUtils.batchTransform(UserDTO.class, userBeans);
     *</pre>
     *
     * @param clazz
     * @param srcList
     * @param <T>
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static  <T> List<T> batchTransform(final Class<T> clazz, List<?> srcList){
        if(CollectionUtils.isEmpty(srcList)) {
            return Collections.emptyList();
        }
        return (List<T>) CollectionUtils.collect(srcList, new Transformer() {
            @Override
            public T transform(Object input) {
                T instance = null;
                try {
                    instance = clazz.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                BeanUtils.copyProperties(input, instance);
                return instance;
            }
        });
    }

}
