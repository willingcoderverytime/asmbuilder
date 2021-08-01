package com.willing.asmbuilder.enums;

import jdk.internal.org.objectweb.asm.Opcodes;

public enum AccessEnum {
    APUBLIC(Opcodes.ACC_PUBLIC),
    APROTECTED(Opcodes.ACC_PROTECTED),
    APRIVATE(Opcodes.ACC_PRIVATE);

    private Integer access;

    AccessEnum(Integer access) {
        this.access = access;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }
}
