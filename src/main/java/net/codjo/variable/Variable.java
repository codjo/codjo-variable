/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.variable;
/**
 * Interface pour une variable
 *
 * @author $Author: gonnot $
 * @version $Revision: 1.1.1.1 $
 */
public interface Variable {
    /**
     * Nom de la variable.
     *
     * @return La valeur de name
     */
    public String getName();


    /**
     * Valeur de la variable.
     *
     * @return La valeur de value
     */
    public String getValue();
}
