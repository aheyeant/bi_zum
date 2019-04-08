package du1;

import du1.exceptions.IMapNotFoundException;
import du1.exceptions.IStepNullException;
import du1.exceptions.NoPathException;

public class Dijkstra implements ISearch {

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
            if (expandNode(up, step, map.getNode(step).getLevel() + 1, heep)) {
                break;
            }
            IStep right = step.stepRight();
            if (expandNode(right, step, map.getNode(step).getLevel() + 1, heep)) {
                break;
            }
            IStep down = step.stepDown();
            if (expandNode(down, step, map.getNode(step).getLevel() + 1, heep)) {
                break;
            }
            IStep left = step.stepLeft();
            if (expandNode(left, step, map.getNode(step).getLevel() + 1, heep)) {
                break;
            }
            if (display) {
                map.printMap();
            }
        }
        lengthPath = map.getNode(map.getEnd()).getLevel();
        if (lengthPath == 0x0f_ff_ff_ff) {
            throw new NoPathException("dijkstra");
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

    private boolean expandNode(IStep step, IStep prev, int price, IHeep<IStep> heep) throws IStepNullException {
        if (map.isEnd(step)) {
            expNodes++;
            map.getNode(step).setStep(prev);
            map.getNode(step).setLevel(price);
            return true;
        }
        if (map.isFree(step)) {
            expNodes++;
            map.makeOpenNode(step, prev, price);
            heep.insert(step, price);
        }
        return false;
    }
}
