/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.variable.basic;
/**
 * classe qui delegue à un string et qui permet de se placer dans une TreeSet le plus
 * long en premier.
 *
 * @author Lajmi
 * @version $Revision: 1.3 $
 */
class ComparedLengthString implements Comparable {
    private String key;
    private String value;

    public ComparedLengthString(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }


    public String getValue() {
        return value;
    }


    public String toString() {
        return key;
    }


    /**
     * le plus long en premier
     *
     * @param object à comparer avec
     *
     * @return
     */
    public int compareTo(Object object) {
        ComparedLengthString compStr = (ComparedLengthString)object;
        if (compStr.getKey().length() < key.length()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
