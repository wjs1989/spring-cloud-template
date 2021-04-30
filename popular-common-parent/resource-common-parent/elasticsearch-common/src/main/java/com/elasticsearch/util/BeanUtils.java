package com.elasticsearch.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class BeanUtils extends org.springframework.beans.BeanUtils{


    /**
     * 封装{@link org.apache.commons.collections.CollectionUtils#collect}
     * 方法和{@link org.springframework.beans.BeanUtils#copyProperties}方法，
     * 常用与批量将Bean转换为DTO
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
                copyProperties(input, instance);
                return instance;
            }
        });
    }

    public static <S,T> List<T> copyPropertiesList(List<S> source, Supplier<T> targetFun) {
        if (CollectionUtils.isEmpty(source) || targetFun == null) {
            return null;
        }

        return (List<T>) CollectionUtils.collect(source, (input)-> {
            T instance = null;
            try {
                instance = targetFun.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            copyProperties(input, instance);
            return instance;
        });
    }

    public static <T, S> T copyProperties(S source, Supplier<T> target) {
        T instance = target.get();
        copyProperties(source,instance);
        return instance;
    }

    public static <T, S> T copyProperties(S source, Class<T> target) {
        T instance = null;
        try {
            instance = target.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        copyProperties(source, instance);
        return instance;
    }
}
