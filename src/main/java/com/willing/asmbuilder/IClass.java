package com.willing.asmbuilder;

import cn.hutool.core.util.StrUtil;
import com.willing.asmbuilder.AbstractNode;

public class IClass implements AsmValidate {

    private String name;

    private String packageName;

    private boolean isArrays=false;

    public IClass(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    public IClass(Class<?> clazz) {
        this.name = clazz.getSimpleName();
        this.packageName = clazz.getPackage().getName();
    }

    @Override
    public void validate() {

    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public boolean isArrays() {
        return isArrays;
    }

    public void setArrays(boolean arrays) {
        isArrays = arrays;
    }


    public String generatorArgs(){
        if (isArrays) {
            return "[L"+getFullName()+";";
        }else{
            return "L"+getFullName()+";";
        }
    }

    public String getFullName() {
        return point2Diagonal(packageName+"."+name);
    }

    public static String diagonal2Point(String className) {

        if (StrUtil.isEmpty(className)) {
            return "java.lang.Object";
        }
        if (className.contains("\\")) {
            return className.replaceAll("\\\\", "\\.");
        } else if (className.contains("/")) {
            return className.replaceAll("/", "\\.");
        }
        return className;
    }

    public static String point2Diagonal(String className) {
        if (StrUtil.isEmpty(className)) {
            return "java/lang/Object";
        }
        if (className.contains(".")) {
            return className.replaceAll("\\.", "/");
        }
        return className;
    }


}
