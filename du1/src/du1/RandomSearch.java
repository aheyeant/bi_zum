package du1;

import du1.exceptions.IMapNotFoundException;
import du1.exceptions.IStepNullException;
import du1.exceptions.NoPathException;

import javax.swing.*;
import java.util.Random;
import java.util.Vector;

public class RandomSearch implements ISearch {
    public RandomSearch() {
        map = null;
        expNodes = 0;
        lengthPath = 0;
    }

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
        Vector<IStep> vec = new Vector<>();
        IStep step = map.getStart();
        while (step != null) {
            IStep ret = addToVector(vec, step);
            if (ret != null) {
                step = ret;
                break;
            }
            step = findInVector(vec);
            if (display) {
                map.printMap();
            }
        }
        if (step == null) {
            throw new NoPathException("RandomSearch");
        }
        while (step != null) {
            step = map.makePathNode(step);
            lengthPath++;
            if (display) {
                map.printMap();
            }
        }
    }

    @Override
    public void printFinalInfo() throws IMapNotFoundException {
        if (map == null) {
            throw new IMapNotFoundException("RandomSearch::printFinalInfo()");
        }
        map.printMap();
        map.printStatInfo(expNodes, lengthPath - 1);
    }

    private IStep addToVector(Vector<IStep> vec, IStep step) throws IStepNullException {
        IStep up = step.stepUp();
        if (map.isEnd(up)) {
            map.getNode(up).setStep(step);
            return up;
        }
        if (map.isFree(up)) {
            map.makeOpenNode(up, step);
            vec.add(up);
            expNodes++;
        }

        IStep right = step.stepRight();
        if (map.isEnd(right)) {
            map.getNode(right).setStep(step);
            return right;
        }
        if (map.isFree(right)) {
            map.makeOpenNode(right, step);
            vec.add(right);
            expNodes++;
        }

        IStep down = step.stepDown();
        if (map.isEnd(down)) {
            map.getNode(down).setStep(step);
            return down;
        }
        if (map.isFree(down)) {
            map.makeOpenNode(down, step);
            vec.add(down);
            expNodes++;
        }

        IStep left = step.stepLeft();
        if (map.isEnd(left)) {
            map.getNode(left).setStep(step);
            return left;
        }
        if (map.isFree(left)) {
            map.makeOpenNode(left, step);
            vec.add(left);
            expNodes++;
        }
        return null;
    }

    private IStep findInVector(Vector<IStep> vec) {
        if (vec.size() == 0) {
            return null;
        }
        int num = Math.abs((new Random()).nextInt() % vec.size());
        IStep s = vec.elementAt(num);
        vec.remove(num);
        return s;
    }
}

