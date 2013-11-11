package arseny.bochkarev.stacks;

public interface IStack {
    void push(Object e) throws StackOverflowError;
    Object peek() throws StackOverflowError;
    void makeEmpty();
    boolean empty();
}
