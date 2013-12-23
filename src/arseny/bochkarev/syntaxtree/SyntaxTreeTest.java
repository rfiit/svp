package arseny.bochkarev.syntaxtree;

import junit.framework.Assert;
import org.junit.Test;

public class SyntaxTreeTest extends Assert{
    @Test
    public void testGetPoland() throws Exception {
        SyntaxTree st = new SyntaxTree();
        st.parseExpression("5+3*2-2-7*16");
        assertEquals(st.getPoland(), "532*+2-716*-");

        st.parseExpression("1+1");
        assertEquals(st.getPoland(), "11+");

        st.parseExpression("78*45+1-45*19/5");
        assertEquals(st.getPoland(), "7845*1+4519*5/-");

        st.parseExpression("15+0*0");
        assertEquals(st.getPoland(), "1500*+");
    }

    @Test
    public void testCreateTree() throws Exception {
        SyntaxTree st = new SyntaxTree();
        st.parseExpression("5+3*2-2-7*16");
        assertEquals(st.getPoland(), "532*+2-716*-");

    }
}
