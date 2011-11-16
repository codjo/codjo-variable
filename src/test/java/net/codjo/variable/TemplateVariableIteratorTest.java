/*
 * Team : AGF AM / OSI / SI / BO
 *
 * Copyright (c) 2001 AGF Asset Management.
 */
package net.codjo.variable;
import java.util.NoSuchElementException;
import junit.framework.TestCase;
/**
 * Classe de Test de <code>PatternVariableIterator</code>.
 *
 * @author $Author: boissie $
 * @version $Revision: 1.3 $
 */
public class TemplateVariableIteratorTest extends TestCase {
    public void test_hasNext_NoVariable() throws Exception {
        StringBuffer str = new StringBuffer("Hello");
        TemplateVariableIterator iter = new TemplateVariableIterator(str);
        assertTrue(!iter.hasNext());
    }


    public void test_next() throws Exception {
        StringBuffer str = new StringBuffer("Hello $name$ le petit $type$.");
        TemplateVariableIterator iter = new TemplateVariableIterator(str);

        assertTrue("hasNext(name)", iter.hasNext());
        assertEquals("next(name)", iter.next(), "name");

        assertTrue("hasNext(type)", iter.hasNext());
        assertEquals("next(type)", iter.next(), "type");

        assertTrue("hasNext(end)", !iter.hasNext());
    }


    public void test_next_EmptyName() throws Exception {
        StringBuffer str = new StringBuffer("Hello $$ crapule.");
        TemplateVariableIterator iter = new TemplateVariableIterator(str);

        assertTrue("hasNext()", iter.hasNext());
        assertEquals("next()", iter.next(), "");

        assertTrue("hasNext(end)", !iter.hasNext());
    }


    public void test_next_Error() throws Exception {
        StringBuffer str = new StringBuffer("Hello");
        TemplateVariableIterator iter = new TemplateVariableIterator(str);

        try {
            iter.next();
            fail("pas de next");
        }
        catch (NoSuchElementException ex) {}
    }


    public void test_setValue() throws Exception {
        StringBuffer buf = new StringBuffer("Hello $name$ le petit $type$.");
        TemplateVariableIterator iter = new TemplateVariableIterator(buf);

        iter.next();
        iter.setValue("bobo");

        iter.next();
        iter.setValue("homme");

        assertEquals(buf.toString(), "Hello bobo le petit homme.");
    }


    public void test_setValue_valueContainsDollar()
            throws Exception {
        StringBuffer buf = new StringBuffer("Devise $name$ en $country$.");
        TemplateVariableIterator iter = new TemplateVariableIterator(buf);

        iter.next();
        iter.setValue("dollar($)");

        iter.next();
        iter.setValue("Angleterre");

        assertEquals(buf.toString(), "Devise dollar($) en Angleterre.");
    }


    public void test_next2timesAndSetValue() throws Exception {
        StringBuffer buf = new StringBuffer("Devise $name$$country$.");
        TemplateVariableIterator iter = new TemplateVariableIterator(buf);

        iter.next();
        iter.next();
        iter.setValue("Angleterre");

        assertEquals(buf.toString(), "Devise $name$Angleterre.");
    }


    public void test_setValue_EmptyValue() throws Exception {
        StringBuffer buf =
            new StringBuffer("$ref.class$ ref = new $ref.class$(this, id);");
        TemplateVariableIterator iter = new TemplateVariableIterator(buf);

        iter.next();
        iter.setValue("");

        iter.next();
        iter.setValue("Lolo");

        assertEquals(buf.toString(), " ref = new Lolo(this, id);");
    }
}
