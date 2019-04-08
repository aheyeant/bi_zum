package du1;

import du1.exceptions.IMapNotFoundException;
import du1.exceptions.IStepNullException;
import du1.exceptions.NoPathException;

import java.time.chrono.IsoChronology;

public class HeuristicSearch implements ISearch {
    public HeuristicSearch() {
        map = null;
        expNodes = 0;
        lengthPath = 0;
        minEndSteps = 0x0f_ff_ff_ff;
    }

    private IMap map;
    private int expNodes;
    private int lengthPath;
    private int minEndSteps;
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
        heep.insert(map.getStart(), heuristic(map.getStart()));
        while (!heep.isEmpty()) {
            IStep step = heep.extractMin();
            if (map.getNode(step).getLevel() + stepDistance(step) > minEndSteps) {
                continue;
            }
            IStep up = step.stepUp();
            expandNode(up, step, heep);
            IStep right = step.stepRight();
            expandNode(right, step, heep);
            IStep down = step.stepDown();
            expandNode(down, step, heep);
            IStep left = step.stepLeft();
            expandNode(left, step, heep);
            if (display) {
                map.printMap();
            }
        }
        lengthPath = map.getNode(map.getEnd()).getLevel();
        if (lengthPath == 0x0f_ff_ff_ff) {
            throw new NoPathException("HeuristicSearch");
        }
        IStep step = map.getEnd();
        while(step != null) {
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

    private int heuristic(IStep step) {
        return airDistance(step);
        //return stepDistance(step);
    }

    private void expandNode(IStep step, IStep prev, IHeep<IStep> heep) throws IStepNullException {
        if (map.isEnd(step)) {
            if (map.getNode(step).getLevel() <= map.getNode(prev).getLevel() + 1) {
                return;
            }
            map.getNode(step).setStep(prev);
            map.getNode(step).setLevel(map.getNode(prev).getLevel() + 1);
            minEndSteps = map.getNode(prev).getLevel() + 1;
            expNodes++;
            return;
        }
        if (map.isFree(step)) {
            expNodes++;
            map.makeOpenNode(step, prev, map.getNode(prev).getLevel() + 1);
            //heep.insert(step, map.getNode(step).getLevel(), stepDistance(step));
            heep.insert(step, heuristic(step), map.getNode(step).getLevel());
            //heep.insert(step, heuristic(step));
            return;
        }
        if (map.isClosed(step)) {
            if (map.getNode(step).getLevel() > map.getNode(prev).getLevel() + 1) {
                map.makeOpenNode(step, prev, map.getNode(prev).getLevel() + 1);
                heep.insert(step, heuristic(step), map.getNode(step).getLevel());
            }
        }
    }
}
