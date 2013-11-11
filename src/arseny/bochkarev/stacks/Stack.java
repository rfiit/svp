package arseny.bochkarev.stacks;

public class Stack implements IStack {

    private Object[] stackbody;
    private int head, n;

    public Stack(int n) {
        init(n);
    }

    private void init(int n) {
        head = 0;
        this.n = n;
        stackbody = new Object[n];
    }

    @Override
    public void push(Object e) throws StackOverflowError {
        if (head == n) {
            throw new StackOverflowError();
        }
        stackbody[head] = e;
        head++;
    }

    @Override
    public Object peek() throws StackOverflowError {
        if (head == 0) {
            throw new StackOverflowError();
        }
        head--;
        Object e = stackbody[head];
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
