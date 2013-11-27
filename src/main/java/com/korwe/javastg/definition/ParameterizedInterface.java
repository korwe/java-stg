package com.korwe.javastg.definition;

import com.korwe.javastg.definition.Interface;
import com.korwe.javastg.type.InterfaceType;
import com.korwe.javastg.type.ParameterizedType;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public class ParameterizedInterface extends ParameterizedType implements InterfaceType {
    public ParameterizedInterface() {
        super();
    }

    public ParameterizedInterface(Interface interfaceType) {
        super(interfaceType);
    }

    public void setInterface(Interface interfaceType){
        setGenerifiable(interfaceType);
    }

    public Interface getInterface(){
        return (Interface)getGenerifiable();
    }
}
