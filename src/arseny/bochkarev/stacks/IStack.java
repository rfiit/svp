package arseny.bochkarev.stacks;

public interface IStack<T> {
    void push(T e);// throws StackOverflow;
    T peek();// throws StackUnderflow;
    T show();
    void makeEmpty();
    boolean empty();
}
