package du1;

import du1.exceptions.NotSupportedMethodException;
import du1.exceptions.FileFormatException;
import du1.exceptions.IStepNullException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Vector;

public class Map implements IMap {
    public Map() {
        map = null;
        start = null;
        end = null;
        xSize = 0;
        ySize = 0;
    }

    private Map(INode[][] map, IStep start, IStep end, int x, int y, boolean noPrint) {
        this.xSize = x;
        this.ySize = y;
        this.start = start;
        this.end = end;
        this.noPrint = noPrint;
        this.map = new Node[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                this.map[i][j] = new Node(map[i][j]);
            }
        }
        noPrint = false;
    }

    private boolean noPrint;
    private INode[][] map;
    private IStep start;
    private IStep end;
    private int xSize;
    private int ySize;

    @Override
    public void setNoPrint(boolean set) {
        this.noPrint = set;
    }

    @Override
    public boolean isCorrect() {
        return map != null &&
               getNode(start).getStatus() != NodeStatus.WALL &&
               getNode(end).getStatus() != NodeStatus.WALL &&
               !startIsEnd();
    }

    @Override
    public void constructMap(InputStream is) throws NotSupportedMethodException, FileFormatException {
        Scanner scanner = new Scanner(is);
        mapReader(scanner);
    }

    @Override
    public void constructMap(String str) throws NotSupportedMethodException, FileFormatException {
            Scanner scanner = new Scanner(str);
            mapReader(scanner);
    }

    @Override
    public void constructMap(File file) throws NotSupportedMethodException, FileFormatException, FileNotFoundException {
        //throw new NotSupportedMethodException("constructMap(File file)");
        Scanner scanner = new Scanner(file);
        mapReader(scanner);
    }

    @Override
    public IMap copy() {
        try {
            return new Map(map, start, end, xSize, ySize, noPrint);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public INode makeOpenNode(IStep step) throws IStepNullException {
        if (step == null) {
            throw new IStepNullException("makeOpenNode(IStep step)");
        }
        this.getNode(step).setStatus(NodeStatus.OPEN_NODE);
        return this.getNode(step);
    }

    @Override
    public INode makeOpenNode(IStep step, IStep prev) throws IStepNullException {
        if (step == null) {
            throw new IStepNullException("makeOpenNode(IStep step, IStep prev)");
        }
        INode node = this.getNode(step);
        node.setStatus(NodeStatus.OPEN_NODE);
        node.setStep(prev);
        return node;
    }

    @Override
    public boolean isClosed(IStep step) {
        return map[step.getY()][step.getX()].getStatus() == NodeStatus.OPEN_NODE;
    }

    @Override
    public INode makeOpenNode(IStep step, IStep prev, int level) throws IStepNullException {
        if (step == null || prev == null) {
            throw new IStepNullException("makeOpenNode(IStep step, IStep prev, int level)");
        }
        INode node = this.getNode(step);
        node.setStatus(NodeStatus.OPEN_NODE);
        node.setStep(prev);
        node.setLevel(level);
        return node;
    }

    @Override
    public IStep makePathNode(IStep step) throws IStepNullException {
        if (step == null) {
            throw new IStepNullException("makePathNode");
        }
        if (this.isEnd(step)) {
            return this.getNode(step).getStep();
        }
        if (this.isStart(step)) {
            return null;
        }
        this.getNode(step).setStatus(NodeStatus.PATH);
        return this.getNode(step).getStep();
    }

    @Override
    public INode getNode(IStep step) {
        return map[step.getY()][step.getX()];
    }

    @Override
    public IStep getStart() {
        return this.start;
    }

    @Override
    public IStep getEnd() {
        return this.end;
    }

    @Override
    public boolean isFree(IStep step) {
        return map[step.getY()][step.getX()].getStatus() == NodeStatus.FREE;
    }

    @Override
    public boolean isStart(IStep step) {
        return map[step.getY()][step.getX()].getStatus() == NodeStatus.START;
    }

    @Override
    public boolean isEnd(IStep step) {
        return map[step.getY()][step.getX()].getStatus() == NodeStatus.END;
    }

    @Override
    public boolean startIsEnd() {
        return start.equals(end);
    }

    @Override
    public void printMap() {
        if (noPrint)
            return;
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                System.out.print(getSymbol(map[i][j].getStatus()));
            }
            System.out.println();
        }
        for (int i = 0; i < xSize; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    @Override
    public void printStatInfo(int expNodes, int lengthPath) {
        System.out.println("---------------------------------------");
        System.out.println("S Start");
        System.out.println("E End");
        System.out.println("# Opened node");
        System.out.println("o Path");
        System.out.println("X Wall");
        System.out.println("space Fresh node");
        System.out.println("---------------------------------------");
        System.out.println("Nodes expanded: " + expNodes);
        System.out.println("Path length: " + lengthPath);
    }

    private char getSymbol(NodeStatus states) {
        switch (states) {
            default:        return 'F';
            case WALL:      return 'X';
            case END:       return 'E';
            case FREE:      return ' ';
            case PATH:      return 'o';
            case START:     return 'S';
            case OPEN_NODE: return '#';
        }
    }

    private NodeStatus symbolControl(char c) throws FileFormatException {
        if (c == 'X') {
            return NodeStatus.WALL;
        }
        if (c == ' ') {
            return NodeStatus.FREE;
        }
        throw new FileFormatException("symbolControl(char c)");
    }

    private void startControl(String start) throws FileFormatException {
        if (start.matches("start[ ]+[0-9]+[ ]*,[ ]*[0-9]+[ ]*")){
            int x, y;
            int i = start.length() - 6;
            x = 0;
            while(start.charAt(start.length() - i) - '0' < 10 && start.charAt(start.length() - i) - '0' >= 0) {
                x *= 10;
                x += start.charAt(start.length() - i) - '0';
                i--;
                if (i == 0) {
                    throw new FileFormatException("startControl(String start)");
                }
            }
            while(start.charAt(start.length() - i) - '0' > 9 || start.charAt(start.length() - i) - '0' < 0) {
                i--;
                if (i == 0) {
                    throw new FileFormatException("startControl(String start)");
                }
            }
            y = 0;
            while(start.charAt(start.length() - i) - '0' < 10 && start.charAt(start.length() - i) - '0' >= 0) {
                y *= 10;
                y += start.charAt(start.length() - i) - '0';
                i--;
                if (i == 0) {
                    break;
                }
            }
            this.start = new Step(x, y);
            if (i != 0) {
                while(start.charAt(start.length() - i) == ' ') {
                    i--;
                    if (i == 0) {
                        return;
                    }
                }
                throw new FileFormatException("startControl(String start)");
            }
        }
    }

    private void endControl(String end) throws FileFormatException {
        if (end.matches("end[ ]+[0-9]+[ ]*,[ ]*[0-9]+[ ]*")){
            int x, y;
            int i = end.length() - 4;
            x = 0;
            while(end.charAt(end.length() - i) - '0' < 10 && end.charAt(end.length() - i) - '0' >= 0) {
                x *= 10;
                x += end.charAt(end.length() - i) - '0';
                i--;
                if (i == 0) {
                    throw new FileFormatException("endControl(String end)");
                }
            }
            while(end.charAt(end.length() - i) - '0' > 9 || end.charAt(end.length() - i) - '0' < 0) {
                i--;
                if (i == 0) {
                    throw new FileFormatException("endControl(String end)");
                }
            }
            y = 0;
            while(end.charAt(end.length() - i) - '0' < 10 && end.charAt(end.length() - i) - '0' >= 0) {
                y *= 10;
                y += end.charAt(end.length() - i) - '0';
                i--;
                if (i == 0) {
                    break;
                }
            }
            this.end = new Step(x, y);
            if (i != 0) {
                while(end.charAt(end.length() - i) == ' ') {
                    i--;
                    if (i == 0) {
                        return;
                    }
                }
                throw new FileFormatException("endControl(String end)");
            }
        }
    }

    private void setStartAndEnd() {
        if (isCorrect()) {
            map[start.getY()][start.getX()].setStatus(NodeStatus.START);
            map[end.getY()][end.getX()].setStatus(NodeStatus.END);
        }
    }

    private void mapReader(Scanner scanner) throws FileFormatException {
        Vector<String> vec = new Vector<>();
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            vec.add(line);
            if (line.charAt(0) == 'e' && line.charAt(1) == 'n') {
                break;
            }
        }
        ySize = vec.size() - 2;
        xSize = vec.elementAt(0).length();
        map = new Node[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            String line = vec.elementAt(i);
            if (line.length() != xSize) {
                throw new FileFormatException("mapReader(Scanner scanner)");
            }
            for (int j = 0; j < xSize; j++) {
                map[i][j] = new Node(symbolControl(line.charAt(j)));
            }
        }
        String startLine = vec.elementAt(ySize);
        String endLine = vec.elementAt(ySize + 1);
        startControl(startLine);
        endControl(endLine);
        setStartAndEnd();
        start = new Step(start.getX(), start.getY());
        end = new Step(end.getX(), end.getY());
    }

}
