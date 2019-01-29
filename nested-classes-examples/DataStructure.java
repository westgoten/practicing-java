import java.util.Iterator;

public class DataStructure {
    private static final int SIZE = 15;
    private int[] arrayOfInts = new int[SIZE];

    public DataStructure() {
        for (int i = 0; i < SIZE; i++)
            arrayOfInts[i] = i;
    }

    public void printEven() {
        DataStructureIterator anEvenIterator = new EvenIterator();
        while (anEvenIterator.hasNext()) {
            System.out.print(anEvenIterator.next() + " ");
        }
        System.out.println();
    }

    interface DataStructureIterator extends Iterator<Integer> {}

    private class EvenIterator implements DataStructureIterator {
        private int nextIndex = 0;

        public boolean hasNext() {
            return nextIndex <= SIZE - 1;
        }

        public Integer next() {
            Integer value = Integer.valueOf(arrayOfInts[nextIndex]);
            nextIndex += 2;
            return value;
        }
    }

    public static void main(String[] args) {
        DataStructure ds = new DataStructure();
        ds.printEven();
    }
}
