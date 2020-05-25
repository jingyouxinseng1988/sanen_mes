package com.plc.platform.util;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SpringUtil {
    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    // 通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
    public static <S, T> List<T> copyListProperty(List<S> list, Class<T> cls) {

        List<T> data = new ArrayList<>();
        if(list==null){
            return data;
        }
        for (S s : list) {
            try {
                T t = cls.newInstance();
                BeanUtils.copyProperties(s, t);
                data.add(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;

    }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(UUID.randomUUID().toString().replace("-", "").length());


        }
    }
}
