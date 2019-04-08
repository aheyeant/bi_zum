package du1;

public class Node implements INode {
    public Node(NodeStatus status) {
        this.status = status;
        this.step = null;
        this.level = 0x0f_ff_ff_ff;
    }

    public Node(INode node) {
        this.status = node.getStatus();
        if (node.getStep() == null) {
            this.step = null;
        } else {
            this.step = new Step(node.getStep());
        }
        this.level = node.getLevel();
    }

    private NodeStatus status;
    private IStep step;
    private int level;

    @Override
    public NodeStatus getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(NodeStatus status) {
        this.status = status;
    }

    @Override
    public IStep getStep() {
        return this.step;
    }

    @Override
    public void setStep(IStep step) {
        this.step = step;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }
}
