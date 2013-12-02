package arseny.bochkarev.stacks;

public class Stack2<T> implements IStack<T>{

    Element<T> tail;

    public Stack2() throws StackUnderflow {
        init();
    }

    private void init() {
        tail = null;
    }

    @Override
    public void push(T e) throws StackOverflow {
        Element ne = new Element(tail, e);
        tail = ne;
    }

    @Override
    public T peek() throws StackUnderflow {
        if (tail == null) {
            throw new StackUnderflow();
        }
        T e = tail.getValue();
        tail = tail.getPrevious();
        return e;
    }

    @Override
    public void makeEmpty() {
        init();
    }

    @Override
    public boolean empty() {
        return (tail == null);
    }
}

class Element<T> {
    private Element<T> prev;
    private T val;
    Element(Element<T> p, T v) {prev = p; val = v;}
    Element<T> getPrevious() {return prev;}
    T getValue() {return val;}
}
