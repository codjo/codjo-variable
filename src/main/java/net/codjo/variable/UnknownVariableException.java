/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.variable;
/**
 * Exception lance lorsqu'une variable est inconnue.
 *
 * @author $Author: gonnot $
 * @version $Revision: 1.1.1.1 $
 */
public class UnknownVariableException extends Exception {
    /**
     * Constructeur de UnknownVariable
     */
    public UnknownVariableException() {}


    /**
     * Constructeur de UnknownVariable
     *
     * @param varName Description of Parameter
     */
    public UnknownVariableException(String varName) {
        super("La variable \"" + varName + "\" est inconnue ");
    }
}
