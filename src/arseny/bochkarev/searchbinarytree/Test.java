package arseny.bochkarev.searchbinarytree;

import static arseny.bochkarev.util.Utilities.*;

public class Test {
    public static void main(String[] arg) {
        SearchBinaryTree<Element> st = new SearchBinaryTree<Element>();
//        for (int i = 0; i < 10; i++) {
//            st.insertLeaf(new Element(i));
//        }
//        st.insertRoot(new Element(10));
//        for (int i = 11; i < 20; i++) {
//            st.insertLeaf(new Element(i));
//        }
//        System.out.println(st);
        int[] arr = {10,5,15,3,7,1,4,6,8,13,17,11,14,16,18};
        for (int i = 0; i < arr.length; i++) {
            st.insertLeaf(new Element(arr[i]));
        }
        pln(st);

        st.remove(new Element(7));

        pln(st);

        st.insertRoot(new Element(7));

        pln(st);

        st.insertLeaf(new Element(0));

        pln(st);

    }
}

class Element implements Comparable<Element> {

    private int val = 0;

    Element(int i) {
        this.val = i;
    }

    @Override
    public int compareTo(Element o) {
        return this.val - o.val;
    }

    public String toString() {
        return "("+val+")";
    }
}