package arseny.bochkarev.searchbinarytree;

public class SearchBinaryTree<T extends Comparable<T>> {
    Node<T> root = null;

    public String toString() {
        return "[" + recursiveToString(root, "") + "]";
//        Worker<T> w = new Worker<T>() {
//            public void work(Node<T> n) {
//                //System.out.print(n.item.toString()); //FUCK
//            }
//        };
    }   //+

    public void insertLeaf(T i) {
        if (i == null) return;
        if (root == null)
            insertRoot(i);
        else
            insertNode(new Node(i));
    }

    public void insertRoot(T i) { //-
        if (i == null) return;
        if (root == null) {
            root = new Node(i);
            return;
        }
        Node<T> current = root;
        this.root = new Node<T>(i);
        Node<T> bufcurrent;
        while (current != null) {
            bufcurrent = null;
            if (i.compareTo(current.item) == 0) { // if in tree was node.item == new root.item;
                insertNode(current.left);
                insertNode(current.right);
                return;
            }
            if (i.compareTo(current.item) > 0) {
                bufcurrent = current.right;
                current.right = null;
            }
            if (i.compareTo(current.item) < 0) {
                bufcurrent = current.left;
                current.left = null;
            }
//            disconnectNode(bufcurrent);
            insertNode(current);
            current = bufcurrent;
        }
//        // F
//        root = new Node(i);
//        Worker<T> w = new Worker<T>() {
//            public void work(Node<T> n) {
//                SearchTree.this.insertLeaf(n.item);
//            }
//        };
//        recursiveWork(oldroot, w);

//        recursiveInsertLeaf(this, oldroot);
    }  //+

    public void remove(T i) {  //+
        if (i == null) return;
        Node<T> nd = searchNode(i);
        if (nd == null) return;

        Node<T> par = searchNodeParent(nd);

        if (nd.right == null && nd.left == null) { // have not leaf
            removeNodeForce(nd);// can remove root
            return;
        }
        if (nd.right == null) {
            moveNode(nd, nd.left);
            return;
        }
        if (nd.left == null) {
            moveNode(nd, nd.right);
            return;
        }

        // nd.left != null && nd.right != null :
        Node<T> cur = nd.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        if (cur.right != null) {
            moveNode(cur, cur.right);
            nd.item = cur.item;
        } else {
            removeNodeForce(cur);
            nd.item = cur.item;
        }

    }  //+

    public T search(T key) {
        Node<T> cur = searchNode(key);
        return (cur == null) ? null : cur.item;
    } //+

    public boolean checkTreeStructure() {
        if (root == null) return true;
        return recursiveCheckStructure(null, root);
    }

//    private void recursiveInsertLeaf(SearchTree st, Node<T> oldroot) {
//        if (oldroot == null) return;
//        st.insertLeaf(oldroot.item);
//        st.recursiveInsertLeaf(st, oldroot.left);
//        st.recursiveInsertLeaf(st, oldroot.right);
//    }

    private boolean recursiveCheckStructure(Node parent, Node child) {
        return (child.parent == parent)
            && (child.left == null ? true : (child.item.compareTo(child.left.item) > 0) && recursiveCheckStructure(child, child.left))
            && (child.right == null ? true : (child.item.compareTo(child.right.item) < 0) && recursiveCheckStructure(child, child.right));
    }

//    private String recursiveToString(Node<T> nd) {
//        if (nd == null) return "()";
//        String s =  nd.item.toString() + " - ";
//        s += "[" + recursiveToString(nd.left) + "]:";
//        s += "[" + recursiveToString(nd.right) + "]";
//        return s;
//    }

    private String recursiveToString(Node<T> nd, String indent) {
        if (nd == null) return "()|\n";
        if (nd.left == null && nd.right == null) {
            return nd.item.toString() + "|\n";
        }
        String s =  nd.item.toString() + "<\n";
        s += indent + "-" + recursiveToString(nd.left, indent + "-") + "";
        s += indent + "-" + recursiveToString(nd.right, indent + "-") + "";
        return s;
    }

    private Node<T> searchNode(T key) {
        if (key == null) return null;
        Node<T> cur = root;
        while((cur != null) && (key.compareTo(cur.item) != 0)) {
            cur = (key.compareTo(cur.item) > 0) ? cur.right : cur.left;
        }
        return cur;
    } //+

//    private Node<T> searchNodeParent(T key) {
//        Node<T> cur = root;
////        Node<T> par = null;
//        while((cur != null) && (key.compareTo(cur.item) != 0)) {
////            par = cur;
//            cur = (key.compareTo(cur.item) > 0) ? cur.right : cur.left;
//        }
//        return (cur == null) ? null : cur.parent;
//    } //++

    private Node<T> searchNodeParent(Node<T> node) {
        return node == null ? null : node.parent;
    } //++

    private void removeNodeForce(Node<T> nd) {
        disconnectNode(nd);
    } // +

    private void moveNode(Node<T> old, Node<T> nw) {
        disconnectNode(nw);
        if (old.parent == null) { // try to replace root
            root = nw;
            return;
        }
        if (old.parent.right == old) old.parent.right = nw; else old.parent.left = nw;
        nw.parent = old.parent;
    } //+

//    private void recursiveWork(Node<T> node, Worker<T> worker) {
//        if (node == null) return;
//        worker.work(node);
//        recursiveWork(node.left, worker);
//        recursiveWork(node.right, worker);
//    }

    private void insertNode(Node<T> node) {

        T i = node.item;
        Node<T> cur = root;
        Node<T> par = null;
        while((cur != null) && (i.compareTo(cur.item) != 0)) {
            par = cur;
            cur = (i.compareTo(cur.item) > 0) ? cur.right : cur.left;
        }

        if(cur != null && cur.item.compareTo(i) == 0) {
            cur.item = i;
            return;
        }

        if (i.compareTo(par.item) < 0) {
            par.left = node;
        } else {
            par.right = node;
        }
        node.parent = par;
    }

    private Node<T> disconnectNode(Node<T> node) {
        if (node.parent == null) return node;
        if (node.parent.left == node) node.parent.left = null; else node.parent.right = null;
        node.parent = null;
        return node;
    }

    // parent & child != null and compareTo != 0
    private void connectNodes(Node<T> parent, Node<T> child) {
        if (parent == null || child == null) return;
        if (parent.item.compareTo(child.item) > 0)
            parent.left = child;
        else
            parent.right = child;
        child.parent = parent;
    }
}

class Node<T extends Comparable<T>> {
    Node left;
    Node right;
    Node parent;
    T item;
    Node(T i) {
        item = i;
    }
    public String toString() {
        return "(" + item.toString() + ")";
    }
}

//interface Worker<T extends Comparable<T>> {
//    void work(Node<T> node);
//    //void work(Node<T> node, String s);
//}
