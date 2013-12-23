package arseny.bochkarev.simpletree;

import static java.lang.Math.*;

public class Tree<T> {

    private enum w2w {L2R, R2L};

    private Node<T> root;

    public void setRoot(T o) {
        if (this.root == null)
            this.root = new Node<T>(o);
        else
            this.root.value = o;
    }

    public int getDeep() {
        return getDeep(root);
    }

    public int countNodeOnLevel(int lvl) {
        return countNodeOnLevel(lvl - 1, root);
    }

    public void leftToRight(Visitor<T> v) {
        (new Conductor<T>()).recursiveLeftToRight(v);
    }

    public void rightToLeft(Visitor<T> v) {
        (new Conductor<T>()).recursiveRightToLeft(v);
    }

    private int getDeep(Node n) {
        if (n == null) {
            return 0;
        }
        else {
            return max(1 + getDeep(n.left), 1 + getDeep(n.right));
        }
    }

    private int countNodeOnLevel(int lvl, Node n) {
        if (n == null || lvl < 0) return 0;
            else
        if(lvl == 0 && n != null) return 1;
            else {
            return countNodeOnLevel(lvl - 1, n.right) + countNodeOnLevel(lvl-1, n.left);
        }
    }

    public Conductor getConductor() {
        return (this.root == null) ? null : new Conductor<T>();
    }

    public class Conductor<T> {
        private Node<T> now;

        private Conductor(Node<T> node) {
            now = node;
        }
        public Conductor() {
            now = (Node<T>) Tree.this.root;
        }

        public Conductor<T> clone() {
            return new Conductor<T>(now);
        }

        public boolean addLeft(T o) {
            if (now.left != null) return false;
            this.now.left = new Node(o);
            return true;
        }

        public boolean addRight(T o) {
            if (now.right != null) return false;
            this.now.right = new Node(o);
            return true;
        }

        public boolean goRight() {
            if (now.right == null) return false;
            now = now.right;
            return true;
        }

        public boolean goLeft() {
            if (now.left == null) return false;
            now = now.left;
            return true;
        }

        public void set(T o) {
            now.value = o;
        }

        public T get() {
            return now.value;
        }

        public boolean deleteRight() {
            if (now.right == null) return false;
            now.right = null;
            return true;
        }

        public boolean deleteLeft() {
            if (now.left == null) return false;
            now.left = null;
            return true;
        }

        public void recursiveLeftToRight(Visitor<T> v) {
            recursiveW2W(v, now, w2w.L2R);
        }

        public void recursiveRightToLeft(Visitor<T> v) {
            recursiveW2W(v, now, w2w.R2L);
        }

        private void recursiveW2W(Visitor<T> v, Node<T> n, w2w w) {
            if (n.left != null && w == w2w.L2R) recursiveW2W(v, n.left, w); else
            if (n.right != null && w == w2w.R2L) recursiveW2W(v, n.right, w);
            v.visit(n.value);
            if (n.left != null && w == w2w.R2L) recursiveW2W(v, n.left, w); else
            if (n.right != null && w == w2w.L2R) recursiveW2W(v, n.right, w);
        }
    }

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        public Node(T o) {
            this.value = o;
        }
    }
}
