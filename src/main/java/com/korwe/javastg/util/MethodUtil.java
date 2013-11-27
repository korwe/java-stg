package com.korwe.javastg.util;

import com.korwe.javastg.definition.AccessModifier;
import com.korwe.javastg.definition.ClassAttribute;
import com.korwe.javastg.definition.ConcreteMethod;
import com.korwe.javastg.definition.Parameter;
import org.stringtemplate.v4.ST;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class MethodUtil {
    public static ConcreteMethod getterMethod(ClassAttribute attribute){
        String name = "get"+attribute.getCapitalizedName();
        ConcreteMethod method = new ConcreteMethod(AccessModifier.Public, name);

        method.setReturnType(attribute.getType());
        ST body = TemplateUtil.template("getter_method_body");
        body.add("attributeName", attribute.getName());
        method.setBody(body.render());
        return method;
    }

    public static ConcreteMethod setterMethod(ClassAttribute attribute){
        String name = "set"+attribute.getCapitalizedName();
        ConcreteMethod method = new ConcreteMethod(AccessModifier.Public, name);

        Parameter parameter = new Parameter(attribute.getType(), attribute.getName());
        method.addParamater(parameter);

        ST body = TemplateUtil.template("setter_method_body");
        body.add("attributeName", attribute.getName());
        method.setBody(body.render());

        return method;

    }
}
