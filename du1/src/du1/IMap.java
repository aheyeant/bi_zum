package du1;

import du1.exceptions.FileFormatException;
import du1.exceptions.IStepNullException;
import du1.exceptions.NotSupportedMethodException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public interface IMap {

    public void constructMap(InputStream is) throws NotSupportedMethodException, FileFormatException;
    public void constructMap(String str) throws NotSupportedMethodException, FileFormatException;
    public void constructMap(File file) throws NotSupportedMethodException, FileFormatException , FileNotFoundException;
    public IMap copy();
    public INode makeOpenNode(IStep step) throws IStepNullException;
    public INode makeOpenNode(IStep step, IStep prev) throws IStepNullException;
    public INode makeOpenNode(IStep step, IStep prev, int level) throws IStepNullException;

    /**
     * node change status to PATH and return IStep prev node
     * if node is END no change
     * @param step
     * @return prev nodeIStep
     */
    public IStep makePathNode(IStep step) throws IStepNullException;
    public INode getNode(IStep step);
    public IStep getStart();
    public IStep getEnd();
    public boolean isFree(IStep step);
    public boolean isStart(IStep step);
    public boolean isClosed(IStep step);
    public boolean isEnd(IStep step);
    public boolean startIsEnd();
    public void printMap();
    public void printStatInfo(int expNodes, int lengthPath);
    public boolean isCorrect();
    public void setNoPrint(boolean set);
}
