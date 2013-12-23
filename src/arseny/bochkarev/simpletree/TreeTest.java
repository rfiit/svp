package arseny.bochkarev.simpletree;

import junit.framework.*;
import junit.framework.Assert;
import org.junit.*;
import org.junit.Test;

public class TreeTest extends Assert {

    Tree<Integer> tree = new Tree<Integer>();

    @Before
    public void setUp() throws Exception {
        tree.setRoot(1);
        Tree.Conductor cnd = tree.getConductor();

        cnd.addLeft(2);
        cnd.addRight(2);
        Tree.Conductor b = cnd.clone();
            cnd.goLeft();
            cnd.addLeft(3);
            cnd.addRight(3);
            cnd.goRight();
                cnd.addRight(4);
        cnd = b;
            cnd.goRight();
            cnd.addLeft(3);
            cnd.goLeft();
                cnd.addLeft(4);
    }

    @Test
    public void testGetDeep() throws Exception {
        assertEquals(tree.getDeep(), 4);
    }

    @Test
    public void testCountNodeOnLevel() throws Exception {
        assertEquals(tree.countNodeOnLevel(0), 0);
        assertEquals(tree.countNodeOnLevel(-10), 0);
        assertEquals(tree.countNodeOnLevel(1), 1);
        assertEquals(tree.countNodeOnLevel(2), 2);
        assertEquals(tree.countNodeOnLevel(3), 3);
        assertEquals(tree.countNodeOnLevel(4), 2);
    }

    @Test
    public void testLeftToRight() throws Exception {
        Counter<Integer> c = new Counter<Integer>();
        tree.leftToRight(c);
        assertEquals(c.getCount(), 22);
    }

    @Test
    public void testRightToLeft() throws Exception {
        Counter<Integer> c = new Counter<Integer>();
        tree.rightToLeft(c);
        assertEquals(c.getCount(), 22);
    }
}
