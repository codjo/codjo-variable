/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.variable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
/**
 * Evaluateur de template. Cette classe évalue toutes les variables contenues dans le template.
 *
 * @author $Author: levequt $
 * @version $Revision: 1.3 $
 */
public class TemplateInterpreter {
    private Map variables = new java.util.HashMap();


    public TemplateInterpreter() {
    }


    /**
     * Ajoute une définition de variable.
     *
     * @param variable La variable
     */
    public void add(Variable variable) {
        variables.put(variable.getName(), variable);
    }


    public void add(String variableName, String value) {
        add(new StringVariable(variableName, value));
    }


    /**
     * Ajoute toute la liste des variables.
     *
     * @param list List a ajouter
     */
    public void addAll(Collection list) {
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            add((Variable)iter.next());
        }
    }


    /**
     * Ajoute ce tableau de property comme définition de variable.
     *
     * @param properties L'objet a ajouter
     */
    public void addAsVariable(Map properties) {
        for (Iterator iter = properties.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry obj = (Map.Entry)iter.next();
            add(new StringVariable(obj.getKey().toString(), obj.getValue().toString()));
        }
    }


    /**
     * Efface les variables en cours.
     */
    public void clear() {
        variables.clear();
    }


    /**
     * Evaluation du template. Les variables sont remplacée par leur valeur.
     *
     * @param template Le template
     *
     * @return Le template instanciee
     */
    public String evaluate(String template) throws UnknownVariableException {
        StringBuffer result = new StringBuffer(template);

            TemplateVariableIterator iter = new TemplateVariableIterator(result);
            while (iter.hasNext()) {
                String varName = iter.nextVariable();
                iter.setValue(getValue(varName));
            }
            String before = template;
            String after = result.toString();

            while (!after.equals(before)) {
                before = after;
                after = evaluate(before);
            }

            return after;
    }


    /**
     * Evalue le 'template' à l'aide des variables passées en paramètre.
     */
    public String evaluate(String template, Map variables)
          throws UnknownVariableException {
        addAsVariable(variables);
        return evaluate(template);
    }


    public String toString() {
        return "Interpreter(vars=" + variables + ")";
    }


    /**
     * Retourne la valeur de la variable <code>varName</code>
     *
     * @param varName Nom de la variable
     *
     * @return La valeur de la variable
     */
    private String getValue(String varName) throws UnknownVariableException {
        Variable variable = (Variable)variables.get(varName);
        if (variable == null) {
            throw new UnknownVariableException(varName);
        }
        return variable.getValue();
    }


    /**
     * Definition simple d'une variable.
     *
     * @author $Author: levequt $
     * @version $Revision: 1.3 $
     */
    private static class StringVariable implements Variable {
        private String name;
        private String value;


        /**
         * Constructeur de StringVariable
         *
         * @param variableName  nom
         * @param variableValue valeur
         */
        StringVariable(String variableName, String variableValue) {
            name = variableName;
            value = variableValue;
        }


        /**
         * Retourne le nom de la varaiable.
         *
         * @return La valeur de name
         */
        public String getName() {
            return name;
        }


        /**
         * Retourne la valeur de la variable.
         *
         * @return La valeur de value
         */
        public String getValue() {
            return value;
        }
    }
}
