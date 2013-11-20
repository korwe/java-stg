package com.korwe.javastg.util;

import com.korwe.javastg.type.AccessModifier;
import com.korwe.javastg.type.Attribute;
import com.korwe.javastg.type.Method;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class MethodUtil {
    public static Method getterMethod(Attribute attribute){
        Method method = new Method();
        method.setAccessModifier(AccessModifier.Public);

        String name = "get"+attribute.getName().substring(0,1).toUpperCase()+attribute.getName().substring(1);
        method.setName(name);

        method.setReturnType(attribute.getType());
        method.setReturnValue("this."+attribute.getName());
        return method;
    }

    public static Method setterMethod(Attribute attribute){
        Method method = new Method();
        method.setAccessModifier(AccessModifier.Public);

        String name = "set"+attribute.getName().substring(0,1).toUpperCase()+attribute.getName().substring(1);
        method.setName(name);

        Attribute parameter = new Attribute();
        parameter.setName(attribute.getName());
        parameter.setType(attribute.getType());
        method.addParamater(parameter);

        method.setBody("this." + attribute.getName() + " = " + attribute.getName());

        return method;

    }
}
