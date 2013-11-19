package arseny.bochkarev.stacks;

public class Stack<T> implements IStack<T>{

    private T[] stackbody;
    private int head, n;

    public Stack(int n) {
        init(n);
    }

    private void init(int n) {
        head = 0;
        this.n = n;
        stackbody = (T[])new Object[n];
    }

    @Override
    public void push(T e) throws StackOverflowError {
        if (head == n) {
            throw new StackOverflowError();
        }
        stackbody[head] = e;
        head++;
    }

    @Override
    public T peek() throws StackOverflowError {
        if (head == 0) {
            throw new StackOverflowError();
        }
        head--;
        T e = stackbody[head];
        stackbody[head] = null;
        return e;
    }

    @Override
    public void makeEmpty() {
        init(n);
    }

    @Override
    public boolean empty() {
        return (head == 0);
    }
}
