package com.korwe.javastg.util;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class TemplateUtil {
    private static STGroupFile javaGroupFile = new STGroupFile("ST/java.stg");

    public static ST template(String templateName){
        return javaGroupFile.getInstanceOf(templateName);
    }
}
