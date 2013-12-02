package arseny.bochkarev.roottree;

public class RootTree<T> {
    Node<T> root = null;



}

class Node<T> {
    public T item;
    public Node<T> left;
    public Node<T> right;
}
