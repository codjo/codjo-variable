/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.variable.basic;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
/**
 * Cette classe remplace dans un String des mots par d'autres. elle contient deux
 * méthodes statiques replaceKeysPerValues et replaceValuesPerKeys elles prennet en
 * parametre le string à modifier, la map et retournent le string apres remplacement. NB
 * : le cas ou une variable est incluse dans une autre est bien géré.
 *
 * @author Lajmi
 */
public final class BasicVariableReplacer {
    private BasicVariableReplacer() {
        ;
    }

    /**
     * replace les keys par leurs valeurs.
     *
     * @param toBeModified le string à replacer
     * @param variables map des variables
     *
     * @return le string après modification
     */
    public static String replaceKeysPerValues(String toBeModified, Map variables) {
        // construction du TreeSet des ComparedLengthString comparé avec la clé
        TreeSet compKeys = new TreeSet();
        for (Iterator iterator = variables.keySet().iterator(); iterator.hasNext();) {
            String key = (String)iterator.next();
            String value = (String)variables.get(key);
            compKeys.add(new ComparedLengthString(key, value));
        }

        return replace(toBeModified, compKeys);
    }


    /**
     * replace les valeurs par leurs keys.
     *
     * @param toBeModified le string à replacer
     * @param variables map des variables
     *
     * @return le string après modification
     */
    public static String replaceValuesPerKeys(String toBeModified, Map variables) {
        // construction du TreeSet des ComparedLengthString comparé avec la clé
        TreeSet compKeys = new TreeSet();
        for (Iterator iterator = variables.keySet().iterator(); iterator.hasNext();) {
            String key = (String)iterator.next();
            String value = (String)variables.get(key);
            compKeys.add(new ComparedLengthString(value, key));
        }

        return replace(toBeModified, compKeys);
    }


    /**
     * méthode commune pour le remplacement.
     *
     * @param toBeModified
     * @param comp
     *
     * @return
     */
    private static String replace(String toBeModified, Set comp) {
        StringBuffer sbf = new StringBuffer(toBeModified);

        for (Iterator iterator = comp.iterator(); iterator.hasNext();) {
            ComparedLengthString comparedLengthString =
                (ComparedLengthString)iterator.next();
            String key = comparedLengthString.getKey();
            String value = comparedLengthString.getValue();

            int index = sbf.toString().indexOf(key);
            while (index != -1) {
                sbf.replace(index, index + key.length(), value);
                index = sbf.toString().indexOf(key);
            }
        }
        return sbf.toString();
    }
}
