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
//        if (root == null) {
//            insertRoot(i);
//            return;
//        }
//
//        Node<T> cur = root;
//        Node<T> par = root;
//        while((cur != null) && (i.compareTo(cur.item) != 0)) {
//            par = cur;
//            cur = (i.compareTo(cur.item) > 0) ? cur.right : cur.left;
//        }
//        if(cur != null && cur.item.compareTo(i) == 0) {
//            cur.item = i;
//            return;
//        }
//        if (i.compareTo(par.item) < 0) {
//            par.left = new Node(i);
//        } else {
//            par.right = new Node(i);
//        }
        insertNode(new Node(i));
    }    //+

    public void insertRoot(T i) { //-
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
    }  //??

    public void remove(T i) {  //+
        Node<T> nd = searchNode(i);
        Node<T> par = searchNodeParent(i);
//        Worker<T> w = new Worker<T>() {
//            public void work(Node<T> n) {
//                SearchTree.this.insertLeaf(n.item);
//            }
//        };
//        recursiveWork(nd.left, w);
//        recursiveWork(nd.right, w);
        if (nd == null) return;

        if (nd.right == null && nd.left == null) { // have not leaf
            removeNodeForce(nd);// can remove root
            return;
        }

        if (nd.right == null) {
            replaceNode(nd, nd.left);
            return;
        }
        if (nd.left == null) {
            replaceNode(nd, nd.right);
            return;
        }

        // nd.left != null && nd.right != null :
        Node<T> cur = nd.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        if (cur.right != null) {
            replaceNode(cur, cur.right);
            nd.item = cur.item;
        } else {
            T it = cur.item;
            removeNodeForce(cur);
            nd.item = it;
        }

    }  //+

    public T search(T key) {
        Node<T> cur = searchNode(key);
        return (cur == null) ? null : cur.item;
    } //+

//    private void recursiveInsertLeaf(SearchTree st, Node<T> oldroot) {
//        if (oldroot == null) return;
//        st.insertLeaf(oldroot.item);
//        st.recursiveInsertLeaf(st, oldroot.left);
//        st.recursiveInsertLeaf(st, oldroot.right);
//    }

    private String recursiveToString(Node<T> nd) {
        if (nd == null) return "()";
        String s =  nd.item.toString() + " - ";
        s += "[" + recursiveToString(nd.left) + "]:";
        s += "[" + recursiveToString(nd.right) + "]";
        return s;
    }

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
        Node<T> cur = root;
        while((cur != null) && (key.compareTo(cur.item) != 0)) {
            cur = (key.compareTo(cur.item) > 0) ? cur.right : cur.left;
        }
        return cur;
    } //+

    private Node<T> searchNodeParent(T key) {
        Node<T> cur = root;
        Node<T> par = null;
        while((cur != null) && (key.compareTo(cur.item) != 0)) {
            par = cur;
            cur = (key.compareTo(cur.item) > 0) ? cur.right : cur.left;
        }
        return (cur == null) ? null : par;
    } //+

    private void removeNodeForce(Node<T> nd) {
        // предполагается, что элемент точно есть в дереве, иначе неправильная функциональность
        Node<T> par = searchNodeParent(nd.item);
        if (par == null) {
            root = null;
            return;
        }
        if (par.left == nd) par.left = null; else par.right = null;
    } // +

    private void replaceNode(Node<T> old, Node<T> nw) {
        //перемещаемая нода не удаляется из дерева, если она в нем была
        Node<T> par = searchNodeParent(old.item);
        if (par == null) { // try to replace root
            root = nw;
            return;
        }
        if (par.right == old) par.right = nw; else par.left = nw;
    } //+

    private void recursiveWork(Node<T> node, Worker<T> worker) {
        if (node == null) return;
        worker.work(node);
        recursiveWork(node.left, worker);
        recursiveWork(node.right, worker);
    }

    private void insertNode(Node<T> node) {
        if (root == null) {
            this.root = node;
            return;
        }

        T i = node.item;
        Node<T> cur = root;
        Node<T> par = root;
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
    }
}

class Node<T extends Comparable<T>> {
    Node left;
    Node right;
    T item;
    Node(T i) {
        item = i;
    }
    public String toString() {
        return "(" + item.toString() + ")";
    }
}

interface Worker<T extends Comparable<T>> {
    void work(Node<T> node);
    //void work(Node<T> node, String s);
}
