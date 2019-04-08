package du1;

import du1.exceptions.IMapNotFoundException;
import du1.exceptions.IStepNullException;
import du1.exceptions.NoPathException;

import java.time.chrono.IsoChronology;
import java.util.LinkedList;
import java.util.Queue;

public class BFS implements ISearch {

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
        Queue<IStep> fifo = new LinkedList<>();
        fifo.offer(map.getStart());
        map.getNode(map.getStart()).setLevel(0);
        int actLevel = 0;
        while (!fifo.isEmpty()) {
            IStep step = fifo.poll();
            if (actLevel != map.getNode(step).getLevel()) {
                actLevel = map.getNode(step).getLevel();
                if (display) {
                    map.printMap();
                }
            }
            IStep up = step.stepUp();
            if (!expandNode(up, step, fifo)) {
                break;
            }
            IStep right = step.stepRight();
            if (!expandNode(right, step, fifo)) {
                break;
            }
            IStep down = step.stepDown();
            if (!expandNode(down, step, fifo)) {
                break;
            }
            IStep left = step.stepLeft();
            if (!expandNode(left, step, fifo)) {
                break;
            }
        }
        if (map.getNode(map.getEnd()).getLevel() == 0x0f_ff_ff_ff) {
            throw new NoPathException("BFS");
        }
        IStep step = map.getEnd();
        while (step != null) {
            step = map.makePathNode(step);
            if (display) {
                map.printMap();
            }
        }
        lengthPath = map.getNode(map.getEnd()).getLevel();
    }

    @Override
    public void printFinalInfo() throws IMapNotFoundException {
        if (map == null) {
            throw new IMapNotFoundException("RandomSearch::calculate(boolean display)");
        }
        map.printMap();
        map.printStatInfo(expNodes, lengthPath);
    }

    private boolean expandNode (IStep step, IStep prev, Queue<IStep> fifo) throws IStepNullException {
        if (map.isEnd(step)) {
            map.getNode(step).setStep(prev);
            map.getNode(step).setLevel(map.getNode(prev).getLevel() + 1);
            expNodes++;
            return false;
        }
        if (map.isFree(step)) {
            map.makeOpenNode(step, prev, map.getNode(prev).getLevel() + 1);
            fifo.offer(step);
            expNodes++;
        }
        return true;
    }
}
