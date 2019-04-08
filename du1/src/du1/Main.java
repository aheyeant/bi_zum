package du1;

import du1.tests.Tests;

public class Main {
    public static void main (String[] args) {
        IMap map = new Map();
        Tests tests = new Tests();
        try {
            map.constructMap(tests.test0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (map.startIsEnd()) {
            map.printMap();
            map.printStatInfo(0, 0);
        }

        if (map.isCorrect()) {
            boolean display = false;
            map.setNoPrint(false);
            //startRandomSearch(display, map.copy());
            //startDFS(display, map.copy());
            //startBFS(display, map.copy());
            startDijkstra(display, map.copy());
            //startGreedySearch(display, map.copy());
            startHeuristicSearch(display, map.copy());
        }

    }

    private static String prefix = "\n== ";
    private static String postfix = " ==\n";

    private static void startRandomSearch(boolean display, IMap map) {
        System.out.println(prefix + "startRandomSearch" + postfix);
        RandomSearch randomSearch = new RandomSearch();
        randomSearch.getMap(map);
        try {
            randomSearch.calculate(display);
            randomSearch.printFinalInfo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void startDFS(boolean display, IMap map) {
        System.out.println(prefix + "startDFS" + postfix);
        DFS dfs = new DFS();
        dfs.getMap(map);
        try {
            dfs.calculate(display);
            dfs.printFinalInfo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void startBFS(boolean display, IMap map) {
        System.out.println(prefix + "startBFS" + postfix);
        BFS bfs = new BFS();
        bfs.getMap(map);
        try {
            bfs.calculate(display);
            bfs.printFinalInfo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void startGreedySearch(boolean display, IMap map) {
        System.out.println(prefix + "startGreedySearch" + postfix);
        GreedySearch greedySearch = new GreedySearch();
        greedySearch.getMap(map);
        try {
            greedySearch.calculate(display);
            greedySearch.printFinalInfo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void startDijkstra(boolean display, IMap map) {
        System.out.println(prefix + "startDijkstra" + postfix);
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.getMap(map);
        try {
            dijkstra.calculate(display);
            dijkstra.printFinalInfo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void startHeuristicSearch(boolean display, IMap map) {
        System.out.println(prefix + "startHeuristicSearch" + postfix);
        HeuristicSearch heuristicSearch = new HeuristicSearch();
        heuristicSearch.getMap(map);
        try {
            heuristicSearch.calculate(display);
            heuristicSearch.printFinalInfo();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
