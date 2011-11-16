/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.variable;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Classe permettant d'iterer sur les variables definies dans le template.
 *
 * @author $Author: boissie $
 * @version $Revision: 1.3 $
 */
public class TemplateVariableIterator implements Iterator {
    private static final String SEPARATOR = "$";
    private static final int SEPARATOR_LENGTH = SEPARATOR.length();
    private int leftIndex = -1;
    private int rightIndex = -1;
    private StringBuffer template;

    public TemplateVariableIterator(StringBuffer template) {
        this.template = template;
    }

    /**
     * Positionne la valeur de la variable courante.
     *
     * @param value La valeur de la variable
     *
     * @throws NoSuchElementException -
     */
    public void setValue(String value) {
        if (leftIndex == rightIndex) {
            throw new NoSuchElementException();
        }
        template.replace(leftIndex, rightIndex + SEPARATOR_LENGTH, value);
        rightIndex = leftIndex + value.length() - SEPARATOR_LENGTH;
    }


    public boolean hasNext() {
        return nextSeparatorIndex(rightIndex + SEPARATOR_LENGTH) != -1;
    }


    /**
     * Idem que <code>nextVariable</code>.
     *
     * @return le nom de la variable
     */
    public Object next() {
        return nextVariable();
    }


    /**
     * Retourne le prochain nom de variable.
     *
     * @return Nom de la variable
     *
     * @throws NoSuchElementException -
     */
    public String nextVariable() {
        leftIndex =
            nextSeparatorIndex(rightIndex + ((rightIndex == -1) ? 1 : SEPARATOR_LENGTH));
        rightIndex = nextSeparatorIndex(leftIndex + SEPARATOR_LENGTH);
        if (leftIndex == -1 || rightIndex == -1) {
            throw new NoSuchElementException();
        }
        return template.toString().substring(leftIndex + SEPARATOR_LENGTH, rightIndex);
    }


    /**
     * Efface la variable en cours
     */
    public void remove() {
        setValue("");
    }


    private int nextSeparatorIndex(int idx) {
        return template.toString().indexOf(SEPARATOR, idx);
    }
}
