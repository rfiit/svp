package arseny.bochkarev.simpletree;

public class Counter<T> implements Visitor<T> {
    private int count = 0;
    public int getCount() {
        return count;
    }
    @Override
    public void visit(T o) {
        count += (Integer)o;
    }
}
