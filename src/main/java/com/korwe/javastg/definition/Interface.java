package com.korwe.javastg.definition;

import com.korwe.javastg.type.InterfaceType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class Interface extends Generifiable implements InterfaceType {
    private List<AbstractMethod> methods;
    private InterfaceType superInterface;

    public Interface(String name) {
        super(null, name);
        init();
    }

    public Interface(String packageName, String name) {
        super(packageName, name);
        init();
    }

    private void init(){
        methods = new ArrayList<>();
    }

    public List<AbstractMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<AbstractMethod> methods) {
        this.methods = methods;
    }

    public InterfaceType getSuperInterface() {
        return superInterface;
    }

    public void setSuperInterface(InterfaceType superInterface) {
        this.superInterface = superInterface;
    }

    public void addMethod(AbstractMethod method){
        methods.add(method);
    }
}
