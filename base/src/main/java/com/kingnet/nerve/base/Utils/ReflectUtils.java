package com.kingnet.nerve.base.Utils;

import android.os.Build;
import android.view.Choreographer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/27 14:23
 * Description:反射工具
 */
public class ReflectUtils {
    private static final String TAG = "Nerve.ReflectUtils";

    public static <T> T get(Class<?> clazz,String fieldName) throws NoSuchFieldException {
        return new ReflectField<T>(clazz,fieldName).get();
    }

    public static Method reflectMethod(Object instance, boolean isHard,String methodName, Class<?>... argTypes) {
        if(isHard){
            try {
                Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod",String.class,Class[].class);
                Method method = (Method) getDeclaredMethod.invoke(instance.getClass(),methodName,argTypes);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            try {
                Method method = instance.getClass().getDeclaredMethod(methodName,argTypes);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Method reflectMethod(Object instance,String name,Class<?>... argTypes){
        return reflectMethod(instance, Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q,name,argTypes);
    }

    public static <T> T reflectObject(Object instance, String name, T defaultValue) {
        return reflectObject(instance,name,defaultValue,Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q);
    }

    private static <T> T reflectObject(Object instance, String name, T defaultValue, boolean isHard) {
        if(null == instance)
            return defaultValue;
        if(isHard){
            try {
                Method getDeclaredField = Class.class.getDeclaredMethod("getDeclaredField",String.class);
                Field field = (Field) getDeclaredField.invoke(instance.getClass(),name);
                field.setAccessible(true);
                return (T) field.get(instance);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }else{
            try {
                Field field = instance.getClass().getDeclaredField(name);
                field.setAccessible(true);
                return (T) field.get(instance);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
}
