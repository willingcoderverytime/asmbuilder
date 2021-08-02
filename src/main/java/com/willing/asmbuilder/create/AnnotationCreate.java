package com.willing.asmbuilder.create;

import com.willing.asmbuilder.IClass;
import com.willing.asmbuilder.node.AnnotationInfo;

import java.util.HashMap;

public class AnnotationCreate {
   private AnnotationInfo annotationInfo;

    public AnnotationCreate(AnnotationInfo annotationInfo) {
        this.annotationInfo = annotationInfo;
    }

    public AnnotationCreate initAnnotationClass(IClass iClass){
       annotationInfo.setAnnotationClass(iClass);
       return this;
   }

   public void addValuesKeyValue(String value,Object obj){
       HashMap<String, Object> valuesMap = annotationInfo.getValuesMap();
       if (valuesMap==null) {
           valuesMap=new HashMap<>();
           annotationInfo.setValuesMap(valuesMap);
       }
       valuesMap.put(value,obj);
    }

}
