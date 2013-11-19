package arseny.bochkarev.stacks;

public interface IStack<T> {
    void push(T e) throws StackOverflowError;
    T peek() throws StackOverflowError;
    void makeEmpty();
    boolean empty();
}
