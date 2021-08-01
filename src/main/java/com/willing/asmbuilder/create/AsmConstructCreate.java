package com.willing.asmbuilder.create;

import com.willing.asmbuilder.IClass;
import com.willing.asmbuilder.enums.AccessEnum;
import com.willing.asmbuilder.node.InitNode;

public class AsmConstructCreate {
    private InitNode clazzNode;


    public AsmConstructCreate(InitNode clazzNode) {
        this.clazzNode = clazzNode;
    }

    public void initDefault(){
        clazzNode.setAccess(AccessEnum.APUBLIC.getAccess());
    }

    public void initArgs(IClass[] args){
        clazzNode.setArgs(args);
    }

    public void initAccess(AccessEnum acc){
        clazzNode.setAccess(acc.getAccess());
    }

    public void initSignature(String signature){
        clazzNode.setSignature(signature);
    }
}
