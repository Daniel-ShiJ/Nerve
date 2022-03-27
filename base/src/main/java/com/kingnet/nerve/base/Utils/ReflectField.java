package com.kingnet.nerve.base.Utils;

import java.lang.reflect.Field;

/**
 * Author:Daniel.ShiJ
 * Date:2022/3/27 14:27
 * Description:
 */
public class ReflectField<T> {
    private Class<?> mClass;
    private String fieldName;
    private boolean mInit;
    private Field mField;

    public ReflectField(Class<?> clazz, String fieldName) {
        this.mClass = clazz;
        this.fieldName = fieldName;
    }

    /**
     * 准备
     */
    private synchronized void prepare() {
        if (mInit)
            return;
        Class<?> tempClass = mClass;
        while (tempClass != null) {
            try {
                Field tempField = tempClass.getDeclaredField(fieldName);
                tempField.setAccessible(true);
                mField = tempField;
                break;
            } catch (NoSuchFieldException e) {
            }
            tempClass = tempClass.getSuperclass();
        }
        mInit = true;
    }

    public T get() throws NoSuchFieldException {
        return get(false);
    }

    private T get(boolean ignoreFieldNoExist) throws NoSuchFieldException {
        prepare();
        if (null == mField) {
            if (!ignoreFieldNoExist)
                throw new NoSuchFieldException();
            return null;
        }
        T fieldValue = null;
        try {
            fieldValue = (T) mField.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (ClassCastException e){
            throw new IllegalArgumentException("unable to cast object");
        }
        return fieldValue;
    }
}
