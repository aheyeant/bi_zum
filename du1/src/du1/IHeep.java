package du1;

public interface IHeep <S> {

    public void insert(S arg, int first);
    public void insert(S arg, int first, int second);
    public S extractMin();
    public S getMin();
    public boolean isEmpty();
    public int getSize();
    public void printHeep();
    public void testHeep();

}
