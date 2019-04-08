package du1;

public class Step implements IStep {
    public Step(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Step(IStep step) {
        if (step == null) {
            x = y = 0;
            return;
        }
        this.x = step.getX();
        this.y = step.getY();
    }

    private int x, y;

    @Override
    public IStep stepUp() {
        return new Step(x, y - 1);
    }

    @Override
    public IStep stepDown() {
        return new Step(x, y + 1);
    }

    @Override
    public IStep stepLeft() {
        return new Step(x - 1, y);
    }

    @Override
    public IStep stepRight() {
        return new Step(x + 1, y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        return this.x == ((Step) obj).getX() && this.y == ((Step) obj).getY();
    }
}
