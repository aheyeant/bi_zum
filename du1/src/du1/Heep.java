package du1;

import java.util.Scanner;

public class Heep <T> implements IHeep <T> {
    public Heep() {
        size = 10;
        items = new TItem[10];
        count = 0;
    }

    public Heep(int size) {
        this.size = size;
        if (this.size <= 1) {
            this.size = 10;
        }
        count = 0;
        items = new TItem[size];
    }

    private TItem<T>[] items;
    private int size;
    private int count;
    private final int heepRoot = 1;
    private class TItem <P>{
        TItem(P arg, int first, int second) {
            this.arg = arg;
            this.first = first;
            this.second = second;
        }

        private TItem(TItem<P> item) {
            if (item == null) {
                first = 0;
                second = 0;
                arg = null;
                return;
            }
            this.first = item.getFirst();
            this.second = item.getSecond();
            this.arg = item.getArg();
        }

        private P arg;
        private int first;
        private int second;

        P getArg() {
            return arg;
        }

        int getFirst() {
            return first;
        }

        int getSecond() {
            return second;
        }

        boolean isLess(TItem item) {
            if (this.first < item.getFirst()) {
                return true;
            } else if (this.first == item.getFirst()) {
                return this.second < item.getSecond();
            }
            return false;
        }
    }

    @Override
    public void insert(T arg, int first) {
        count++;
        resize();
        items[count] = new TItem<>(arg, first, 0);
        bubbleUp(count);
    }

    @Override
    public void insert(T arg, int first, int second) {
        count++;
        resize();
        items[count] = new TItem<>(arg, first, second);
        bubbleUp(count);
    }

    @Override
    public T extractMin() {
        if (size == 0)
            return null;
        TItem<T> tmp = items[heepRoot];
        swap(heepRoot, count);
        count--;
        bubbleDown(heepRoot);
        return tmp.getArg();
    }

    @Override
    public T getMin() {
        if (!isEmpty()) {
            return items[heepRoot].getArg();
        } else {
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int getSize() {
        return this.count;
    }

    @Override
    public void printHeep() {
        System.out.println("heep state: size(" + size + "), count(" + count + ")");

        int pom = 0;
        int pow = 1;
        for (int i = 1; i <= count; i++) {
            System.out.print("(" + items[i].getFirst() + "," + items[i].getSecond() + ") ");
            pom++;
            if (pow == pom) {
                System.out.println();
                pow *= 2;
                pom = 0;
            }
        }
        System.out.println();
        System.out.println("--------------------------------");
    }

    @Override
    public void testHeep() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - insert\n2 - show min\n3 - extract min\n4 - print\n5 - exit");
        while (scanner.hasNextInt()) {
            switch (scanner.nextInt()) {
                case 1: {
                    insert(null, scanner.nextInt());
                    System.out.println("insert - ok");
                    break;
                }
                case 2: {
                    System.out.println(getTestMin() + "\nshow - ok");
                    break;
                }
                case 3: {
                    System.out.println(extractTestMin() + "\nextract - ok");
                    break;
                }
                case 4: {
                    printHeep();
                    break;
                }
                case 5: {
                    return;
                }
                default: {
                    break;
                }
            }
            System.out.println("1 - insert\n2 - show min\n3 - extract min\n4 - print\n5 - exit");
        }
    }

    private void bubbleUp(int iterator) {
        if (iterator == 1)
            return;
        int father = iterator / 2;
        if (items[iterator].isLess(items[father])) {
            swap(iterator, father);
            bubbleUp(father);
        }
    }

    private void bubbleDown(int iterator) {
        int l = 2 * iterator;
        int r = 2 * iterator + 1;
        if (l < count && r <= count) {
            if (items[r].isLess(items[l])) {
                if (items[r].isLess(items[iterator])) {
                    swap(iterator, r);
                    bubbleDown(r);
                }
            } else  {
                if (items[l].isLess(items[iterator])) {
                    swap(l, iterator);
                    bubbleDown(l);
                }
            }
        } else if (l <= count) {
            if (items[l].isLess(items[iterator])) {
                swap(l, iterator);
                bubbleDown(l);
            }
        }
    }

    private void resize() {
        if (count != size)
            return;
        size *= 2;
        TItem<T>[] tmp = new TItem[size];
        for (int i = 1; i < count; i++) {
            tmp[i] = new TItem(items[i]);
        }
        items = tmp;
    }

    private void swap(int it1, int it2) {
        TItem<T> tmp = items[it1];
        items[it1] = items[it2];
        items[it2] = tmp;
    }

    private int getTestMin() {
        if (count == 0)
            return 0xff_ff_ff_ff;
        return items[heepRoot].getFirst();
    }

    private int extractTestMin() {
        if (count == 0)
            return 0xff_ff_ff_ff;
        TItem tmp = items[heepRoot];
        swap(heepRoot, count);
        count--;
        bubbleDown(heepRoot);
        return tmp.getFirst();
    }
}
