/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.variable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
/**
 * Classe de Test de <code>TemplateInterpreter</code>.
 *
 * @author $Author: catteao $
 * @version $Revision: 1.5 $
 */
public class TemplateInterpreterTest extends TestCase {
    private TemplateInterpreter interpreter;


    public void test_addAsVariable() throws Exception {
        String template = "le grand $v1$ du $aDay$";
        Map map = new HashMap();
        map.put("v1", "bobo");
        map.put("aDay", new Date(0));
        interpreter.addAsVariable(map);
        assertEquals("le grand bobo du Thu Jan 01 01:00:00 CET 1970",
                     interpreter.evaluate(template));
    }


    public void test_evaluate_valueContainsDollar()
          throws Exception {
        interpreter.evaluate("dollar($)");
        interpreter.evaluate("dollar($) ha mais j'ai fait expres de mettre $ dans la phrase");
        String template = "devise $currency$";
        interpreter.add("currency", "dollar($)");
        assertEquals("devise dollar($)", interpreter.evaluate(template));
    }


    public void test_clear() throws Exception {
        String template = "le grand $v1$";
        Map map = new HashMap();
        map.put("v1", "bobo");
        interpreter.addAsVariable(map);
        assertEquals(interpreter.evaluate(template), "le grand bobo");
        interpreter.clear();
        try {
            interpreter.evaluate(template);
            fail("les variables sont effacées");
        }
        catch (UnknownVariableException e) {
        }
    }


    public void test_evaluate_emptyTemplate() throws Exception {
        String template = "no variable";

        assertEquals(interpreter.evaluate(template), template);
    }


    public void test_evaluate_error() throws Exception {
        String template = "unknown $variable$";

        try {
            interpreter.evaluate(template);
            fail("Variable est inconnue");
        }
        catch (UnknownVariableException e) {
        }
    }


    public void test_evaluate() throws Exception {
        String template = "le grand $v1$ v1";
        final String result = "le grand bobo v1";
        final Map variables = new HashMap(1);
        variables.put("v1", "bobo");

        assertEquals(result, interpreter.evaluate(template, variables));
        assertEquals(result, interpreter.evaluate(template));
    }

    public void test_evaluate_nullValue() throws Exception {
        String template = "A $variable$ B";
        final String result = "A null B";
        final Map variables = new HashMap(1);
        variables.put("variable", null);

        assertEquals(result, interpreter.evaluate(template, variables));
        assertEquals(result, interpreter.evaluate(template));
    }

    public void test_evaluate_noSpaceInVariableNames() throws Exception {
        String template = "the big $variable withSpace$ bobo";
        final String result = "the big $variable withSpace$ bobo";
        final Map variables = new HashMap(1);
        variables.put("variable withSpace", "bobo");

        assertEquals(result, interpreter.evaluate(template, variables));
        assertEquals(result, interpreter.evaluate(template));
    }


    public void test_evaluate_with_add() throws Exception {
        String template = "le grand $v1$";
        final Variable variable =
              new Variable() {
                  public String getName() {
                      return "v1";
                  }


                  public String getValue() {
                      return "bobo";
                  }
              };
        interpreter.add(variable);
        assertEquals("le grand bobo", interpreter.evaluate(template));
    }


    public void test_evaluateContiguousVariables()
          throws Exception {
        String template = "le grand $v1$$aDay$";
        Map map = new HashMap();
        map.put("v1", "bobo");
        map.put("aDay", new Date(0));
        interpreter.addAsVariable(map);
        assertEquals("le grand boboThu Jan 01 01:00:00 CET 1970",
                     interpreter.evaluate(template));
    }


    public void test_evaluate_recursivly() throws UnknownVariableException {
        String template = "$concat$";
        Map map = new HashMap();
        map.put("var.a", "A");
        map.put("var.b", "B");
        map.put("concat", "$var.a$.$var.b$");
        interpreter.addAsVariable(map);

        assertEquals("A.B", interpreter.evaluate(template));
    }


    protected void setUp() {
        interpreter = new TemplateInterpreter();
    }


    protected void tearDown() {
    }
}
