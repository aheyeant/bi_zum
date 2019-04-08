package du1;

public interface IStep {
    public IStep stepUp();
    public IStep stepDown();
    public IStep stepLeft();
    public IStep stepRight();
    public int getX();
    public int getY();


}
