package com.willing.asmbuilder.example.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.willing.asmbuilder.AsmClassBuilder;
import com.willing.asmbuilder.EnumClass;
import com.willing.asmbuilder.IClass;
import com.willing.asmbuilder.create.AnnotationCreate;
import com.willing.asmbuilder.create.AsmFieldCreate;
import com.willing.asmbuilder.create.AsmMethodCreate;
import com.willing.asmbuilder.enums.AccessEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

@RunWith(JUnit4.class)
class CreateClassWithAnnotion {

    @Test
    void createClass() {
        AsmClassBuilder asmClassBuilder = AsmClassBuilder.classBuilder();

        IClass iClass = new IClass("ControllerTest", "com.willing.test");
        asmClassBuilder.buildClass(iClass)
                .buildJava8Version()
                .buildSupperClass(new IClass(Object.class))
                .buildAccess(AccessEnum.APUBLIC)
                .createEmptyConstruct().initAccess(AccessEnum.APUBLIC);
        // step 1 头部注解。
        AnnotationCreate annotationCreate = asmClassBuilder.createAnnotationCreate();
        annotationCreate.initAnnotationClass(new IClass("RestController","org.springframework.web.bind.annotation"));
        // step 2 普通方法

        AsmMethodCreate method = asmClassBuilder.createMethod();
        method.initAccess(AccessEnum.APUBLIC);
        IClass xmlC110011Input = new IClass("XmlC110011Input", "com.willing.service.param");
        IClass[] classzz= new IClass[1];
        classzz[0]=xmlC110011Input;
        method.initInputs(classzz);
        method.initReturns(new IClass("XmlC110011Output","com.willing.service.param"));
        method.initName("executeText2xml");

        AnnotationCreate annotationCreate1 = method.createAnnotationCreate();
        annotationCreate1.initAnnotationClass(new IClass(RequestMapping.class));

        String[] xx= new String[1];
        xx[0]="/xml/C110011";
        annotationCreate1.addValuesKeyValue("value",xx);

        String[] consumes= new String[2];
        consumes[0]="application/xml";
        consumes[1]="text/xml";
        annotationCreate1.addValuesKeyValue("consumes",consumes);

        String[] produces= new String[1];
        produces[0]="application/xml";
        annotationCreate1.addValuesKeyValue("produces",produces);


        EnumClass enumClass = new EnumClass(RequestMethod.class);
        enumClass.setValue("POST");
        annotationCreate1.addValuesKeyValue("method",enumClass);
        asmClassBuilder.generateClass();


    }


}