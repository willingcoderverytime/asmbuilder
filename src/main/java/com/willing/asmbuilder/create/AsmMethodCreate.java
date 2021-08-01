package com.willing.asmbuilder.create;

import com.willing.asmbuilder.IClass;
import com.willing.asmbuilder.enums.AccessEnum;
import com.willing.asmbuilder.node.AsmMethodNode;

public class AsmMethodCreate {
    private AsmMethodNode methodNode;

    public AsmMethodCreate(AsmMethodNode methodNode) {
        this.methodNode = methodNode;
    }

    public void initDefaultMethod(String name){
        methodNode.setName(name);
        methodNode.setAccess(AccessEnum.APUBLIC.getAccess());
    }


    public AsmMethodCreate initName(String name){
        methodNode.setName(name);
        return this;
    }

    public AsmMethodCreate initAccess(AccessEnum accessEnum){
        methodNode.setAccess(accessEnum.getAccess());
        return this;
    }

    public AsmMethodCreate initInputs(IClass[] klazzs){
        methodNode.setArgs(klazzs);
        return this;
    }
    public AsmMethodCreate initReturns(IClass klassNode){
        methodNode.setReturnNode(klassNode);
        return this;
    }

    public AsmMethodCreate initThrows(IClass[] exceptions){
        methodNode.setExceptions(exceptions);
        return this;
    }

}
