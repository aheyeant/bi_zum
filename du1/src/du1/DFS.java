package du1;

import du1.exceptions.IMapNotFoundException;
import du1.exceptions.IStepNullException;
import du1.exceptions.NoPathException;

public class DFS implements ISearch {

    private IMap map;
    private int expNodes;
    private int lengthPath;

    @Override
    public void getMap(IMap map) {
        this.map = map;
    }

    @Override
    public void calculate(boolean display) throws IMapNotFoundException, IStepNullException, NoPathException {
        if (map == null) {
            throw new IMapNotFoundException("RandomSearch::calculate(boolean display)");
        }
        if (dfsRec(map.getStart(), null, display)) {
            IStep step = map.getEnd();
            while (step != null) {
                step = map.makePathNode(step);
                lengthPath++;
                if (display) {
                    map.printMap();
                }
            }
        } else {
            throw new NoPathException("DFS");
        }
    }

    @Override
    public void printFinalInfo() throws IMapNotFoundException {
        if (map == null) {
            throw new IMapNotFoundException("RandomSearch::calculate(boolean display)");
        }
        map.printMap();
        map.printStatInfo(expNodes, lengthPath - 1);
    }

    private boolean dfsRec (IStep step, IStep prev, boolean display) throws IStepNullException {
        if (step == null) {
            throw new IStepNullException("dfsRec (IStep step, IStep prev)");
        }
        if (map.isFree(step) || map.isEnd(step) || map.isStart(step)) {
            expNodes++;
            if (!map.isStart(step)) {
                map.makeOpenNode(step, prev);
            }
            if (display) {
                map.printMap();
            }
        } else {
            return false;
        }
        IStep up = step.stepUp();
        if (map.isEnd(up)) {
            map.getNode(up).setStep(step);
            return true;
        }
        if (!dfsRec(up, step, display)) {
            IStep right = step.stepRight();
            if (map.isEnd(right)) {
                map.getNode(right).setStep(step);
                return true;
            }
            if (!dfsRec(right, step, display)) {
                IStep down = step.stepDown();
                if (map.isEnd(down)) {
                    map.getNode(down).setStep(step);
                    return true;
                }
                if (!dfsRec(down, step, display)) {
                    IStep left = step.stepLeft();
                    if (map.isEnd(left)) {
                        map.getNode(left).setStep(step);
                        return true;
                    }
                    return dfsRec(left, step, display);
                } else  {
                    return true;
                }
            } else {
                return true;
            }
        } else  {
            return true;
        }
    }
}
