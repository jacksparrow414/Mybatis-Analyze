package com.example.mybatis.demomybatis.reflect;

import com.example.mybatis.demomybatis.entity.ReflectEntity;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author jacksparrow414
 * @date 2021/3/11 13:50
 */
public class ReflectUseExample {

    @SneakyThrows
    public static void main(String[] args)  {
        Class<?> aClass = Class.forName("com.example.mybatis.demomybatis.entity.ReflectEntity");
        Class<ReflectEntity> reflectEntityClass = ReflectEntity.class;
        ReflectEntity entity = (ReflectEntity) aClass.getConstructor().newInstance();
        // 获取该类的所有属性
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            declaredField.setAccessible(true);
            // 为属性赋值
            declaredField.set(entity, "this is name");
            System.out.println(declaredField.get(entity));
        }
        // 返回类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，可以指定名字和参数类型，来返回某一个
        Method method = aClass.getDeclaredMethod("print", String.class);
        // 对于private方法，设置可以访问的权限
        method.setAccessible(true);
        // 通过method.invoke方法直接调用
        method.invoke(entity, "this is message");
    }
}
