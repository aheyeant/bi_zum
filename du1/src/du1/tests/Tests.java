package du1.tests;

import java.io.File;

public class Tests {
    public Tests() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            test0 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\0.txt");
            test4 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\4.txt");
            test6 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\6.txt");
            test26 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\26.txt");
            test36 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\36.txt");
            test42 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\42.txt");
            test72 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\72.txt");
            test84 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\84.txt");
            test114 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\114.txt");
            test220 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\220.txt");
            test332 = new File(System.getProperty("user.dir") + "\\src\\du1\\tests\\332.txt");
        } else {
            test0 = new File(System.getProperty("user.dir") +   "/src/du1/tests/0.txt");
            test4 = new File(System.getProperty("user.dir") +   "/src/du1/tests/4.txt");
            test6 = new File(System.getProperty("user.dir") +   "/src/du1/tests/6.txt");
            test26 = new File(System.getProperty("user.dir") +  "/src/du1/tests/26.txt");
            test36 = new File(System.getProperty("user.dir") +  "/src/du1/tests/36.txt");
            test42 = new File(System.getProperty("user.dir") +  "/src/du1/tests/42.txt");
            test72 = new File(System.getProperty("user.dir") +  "/src/du1/tests/72.txt");
            test84 = new File(System.getProperty("user.dir") +  "/src/du1/tests/84.txt");
            test114 = new File(System.getProperty("user.dir") + "/src/du1/tests/114.txt");
            test220 = new File(System.getProperty("user.dir") + "/src/du1/tests/220.txt");
            test332 = new File(System.getProperty("user.dir") + "/src/du1/tests/332.txt");
        }
    }

    public File test0;
    public File test4;
    public File test6;
    public File test26;
    public File test36;
    public File test42;
    public File test72;
    public File test84;
    public File test114;
    public File test220;
    public File test332;
}
