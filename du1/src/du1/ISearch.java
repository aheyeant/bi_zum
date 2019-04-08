package du1;

import du1.exceptions.IMapNotFoundException;
import du1.exceptions.IStepNullException;
import du1.exceptions.NoPathException;

public interface ISearch {

    public void getMap(IMap map);
    public void calculate(boolean display) throws IMapNotFoundException, IStepNullException, NoPathException;
    public void printFinalInfo() throws IMapNotFoundException;
}
