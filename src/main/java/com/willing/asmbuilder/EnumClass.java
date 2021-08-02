package com.willing.asmbuilder;

import cn.hutool.core.util.StrUtil;

public class EnumClass implements AsmValidate {

    private String name;

    private String packageName;
    // annotation visit use it to do something

    private String value;


    public EnumClass(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    public EnumClass(Class<?> clazz) {
        this.name = clazz.getSimpleName();
        this.packageName = clazz.getPackage().getName();
    }

    @Override
    public void validate() {

    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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


    public String generatorArgs() {
        return "L" + getFullName() + ";";
    }

    public String getFullName() {
        return point2Diagonal(packageName + "." + name);
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
