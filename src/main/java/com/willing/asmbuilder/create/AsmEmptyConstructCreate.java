package com.willing.asmbuilder.create;

import com.willing.asmbuilder.enums.AccessEnum;
import com.willing.asmbuilder.node.EmptyInitNode;
import com.willing.asmbuilder.node.KlassNode;

public class AsmEmptyConstructCreate {
    private EmptyInitNode clazzNode;


    public AsmEmptyConstructCreate(EmptyInitNode clazzNode) {
        this.clazzNode = clazzNode;
    }

    public void initDefault(){
        clazzNode.setAccess(AccessEnum.APUBLIC.getAccess());
    }

    public void initAccess(AccessEnum acc){
        clazzNode.setAccess(acc.getAccess());
    }
}
