package du1;

import du1.exceptions.IMapNotFoundException;
import du1.exceptions.IStepNullException;
import du1.exceptions.NoPathException;

public class GreedySearch implements ISearch {

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
        IHeep<IStep> heep = new Heep<>();
        map.getNode(map.getStart()).setLevel(0);
        heep.insert(map.getStart(), 0);
        while (!heep.isEmpty()) {
            IStep step = heep.extractMin();
            IStep up = step.stepUp();
            if (expandNode(up, step, heep)) {
                break;
            }
            IStep right = step.stepRight();
            if (expandNode(right, step, heep)) {
                break;
            }
            IStep down = step.stepDown();
            if (expandNode(down, step, heep)) {
                break;
            }
            IStep left = step.stepLeft();
            if (expandNode(left, step, heep)) {
                break;
            }
            if (display) {
                map.printMap();
            }
        }
        lengthPath = map.getNode(map.getEnd()).getLevel();
        if (lengthPath == 0x0f_ff_ff_ff) {
            throw new NoPathException("GreedySearch");
        }
        IStep step = map.getEnd();
        while (step != null) {
            step = map.makePathNode(step);
            if (display) {
                map.printMap();
            }
        }

    }

    @Override
    public void printFinalInfo() throws IMapNotFoundException {
        if (map == null) {
            throw new IMapNotFoundException("RandomSearch::calculate(boolean display)");
        }
        map.printMap();
        map.printStatInfo(expNodes, lengthPath);
    }

    private int stepDistance(IStep step) {
        return Math.abs(step.getX() - map.getEnd().getX()) + Math.abs(step.getY() - map.getEnd().getY());
    }

    private int airDistance(IStep step) {
        return (int)(Math.sqrt(Math.pow(step.getX() - map.getEnd().getX(),2) + Math.pow(step.getY() - map.getEnd().getY(),2)) * 100);
    }

    private boolean expandNode(IStep step, IStep prev, IHeep<IStep> heep) throws IStepNullException {
        if (map.isEnd(step)) {
            map.getNode(step).setStep(prev);
            map.getNode(step).setLevel(map.getNode(prev).getLevel() + 1);
            expNodes++;
            return true;
        }
        if (map.isFree(step)) {
            expNodes++;
            map.makeOpenNode(step, prev, map.getNode(prev).getLevel() + 1);
            //heep.insert(step, stepDistance(step));
            heep.insert(step, airDistance(step));
        }
        return false;
    }

}
