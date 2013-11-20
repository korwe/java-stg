package com.korwe.javastg.type;

/**
 * @author <a href="mailto:tjad.clark@korwe.com>Tjad Clark</a>
 */
public enum AccessModifier {
    Public,
    Private,
    Protected;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
