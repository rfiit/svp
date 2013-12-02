package arseny.bochkarev.stacks;

import static arseny.bochkarev.util.Utilities.*;

public class Test {
    public static void main(String[] arg) {

        int n = 10;

        try {
            Stack<Elem> stack = new Stack<Elem>(10);

            for (int i = 0; i < n; i++) {
                stack.push(new Elem(i));
            }

//            while (true) {
//                pln(stack.peek().value);
//            }

//            if (true) throw new Exception();

            while (!stack.empty()) {
                pln(stack.peek().value);
            }


        } catch (Exception e) {
            if (e.getClass() == StackOverflow.class) pln("Stack overflow exception."); else
            if (e.getClass() == StackUnderflow.class) pln("Stack underflow exception."); else
            pln("Unknown exception.");
        }

        try {
            Stack<Elem> stack = new Stack<Elem>(10);

            for (int i = 0; i < n; i++) {
                stack.push(new Elem(i));
            }

//            while (true) {
//                pln(stack.peek().value);
//            }

            while (!stack.empty()) {
                pln(stack.peek().value);
            }


        } catch (Exception e) {
            if (e.getClass() == StackOverflow.class) pln("Stack overflow exception."); else
            if (e.getClass() == StackUnderflow.class) pln("Stack underflow exception."); else
                pln("Unknown exception.");
        }

    }
}

class Elem {
    Elem(int k) {
        value = k;
    }
    public int value = 0;
}