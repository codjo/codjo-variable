/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.variable.basic;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import junit.framework.TestCase;
/**
 * DOCUMENT ME!
 *
 * @version $Revision: 1.2 $
 */
public class BasicVariableReplacerTest extends TestCase {
    BasicVariableReplacer basicVarableReplacer;

    public void testReplaceKeysPerValues() throws Exception {
        String template =
            "ceci est un exemple de remplacement. string stringBuffer cd cdj";
        Map map = new HashMap();
        map.put("de", "DE");
        map.put("est un", "is a");
        map.put("string", "Integer");
        map.put("stringBuffer", "Long");
        map.put("cd", "FFFF");
        String result = BasicVariableReplacer.replaceKeysPerValues(template, map);
        assertEquals("ceci is a exemple DE remplacement. Integer Long FFFF FFFFj", result);
    }


    public void testReplaceValuePerKeys() throws Exception {
        String template =
            "ceci est un exemple de remplacement. string stringBuffer cd cdj";
        Map map = new HashMap();
        map.put("DE", "de");
        map.put("is a", "est un");
        map.put("Integer", "string");
        map.put("Long", "stringBuffer");
        map.put("FFFF", "cd");
        String result = BasicVariableReplacer.replaceValuesPerKeys(template, map);
        assertEquals("ceci is a exemple DE remplacement. Integer Long FFFF FFFFj", result);
    }


    /**
     * le plus ong en prmier
     *
     * @throws Exception
     */
    public void test_ComparedLengthString() throws Exception {
        Set set = new TreeSet();
        set.add(new ComparedLengthString("eefff", "r"));
        set.add(new ComparedLengthString("ee5", "r"));
        assertEquals("[eefff, ee5]", set.toString());
    }
}
