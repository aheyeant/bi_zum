package du1;

public interface INode {

    public NodeStatus getStatus();
    public void setStatus(NodeStatus status);
    public IStep getStep();
    public void setStep(IStep step);
    public int getLevel();
    public void setLevel(int level);
}
