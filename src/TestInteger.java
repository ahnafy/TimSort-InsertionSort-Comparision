import java.lang.reflect.Array;
import java.util.Arrays;

public class TestInteger implements Comparable<TestInteger> {
    public int value;
    static long counter;

    public TestInteger(){}

    public TestInteger(int initVal) {
        this.value = initVal;
    }

    public static void main(String args[]) {
        TestInteger program = new TestInteger();
        program.compareAlgorithms();
    }

    public void compareAlgorithms(){
        TestInteger[] qsArray = generateRandomArray(10000);
        TestInteger[] tsArray = qsArray.clone();


        System.out.println(("## Random Arrays\n"));
            runQuicksort(qsArray);
            runTimSort(tsArray);


        System.out.println("\n\n## Ordered Arrays\n");
            runQuicksort(qsArray);
            runTimSort(tsArray);


        System.out.println("\n\n## 10 Sorted Seqs of 1000\n");
            makeArrayIntoSortedChunks(qsArray, 1000);
            tsArray = qsArray.clone();

            runQuicksort(qsArray);
            runTimSort(tsArray);


        System.out.println("\n\n## 100 Sorted Seqs of 100\n");
            makeArrayIntoSortedChunks(qsArray, 100);
            tsArray = qsArray.clone();

            runQuicksort(qsArray);
            runTimSort(tsArray);
    }

    public void runQuicksort(TestInteger[] array) {
        TestInteger.resetCounter();

        long startTime = System.currentTimeMillis();
        TestInteger.quicksort(array,0,array.length-1);
        long endTime = System.currentTimeMillis();

        long counter = TestInteger.counter;


        System.out.println("### Quicksort");
        System.out.println("\t* Comparisons: " + counter);
        System.out.println("\t* Runtime: " + (endTime-startTime) + " ms");
        System.out.println("\t* Sorted: " + TestInteger.isSorted(array) + "\n");
    }

    public void runTimSort(TestInteger[] array) {
        TestInteger.resetCounter();

        long startTime = System.currentTimeMillis();
        Arrays.sort(array);
        long endTime = System.currentTimeMillis();

        long counter = TestInteger.counter;

        System.out.println("### Tim Sort");
        System.out.println("\t* Comparisons: " + counter);
        System.out.println("\t* Runtime: " + (endTime-startTime) + " ms");
        System.out.println("\t* Sorted: " + TestInteger.isSorted(array) + "\n");
    }

    public int generateRandomInt() { return (int) (Math.random() * 1000000); }

    public TestInteger[] generateRandomArray(int size) {
        TestInteger[] result = new TestInteger[size];

        for (int i = 0; i < size; i++) {
            result[i] = new TestInteger(generateRandomInt());
        }

        return result;
    }

    public void makeArrayIntoSortedChunks(TestInteger[] array, int chunkSize) {
        // Thanks for the inspiration of this idea, Kai!
        // for every chunk of 1000 elements in qsArray
        for (int chunkIndex = 0; chunkIndex < array.length; chunkIndex += chunkSize) {
            int startValue = generateRandomInt();

            // for every element within each of those thousand chunks
            for (int relativeIndex = chunkIndex; relativeIndex < chunkIndex + chunkSize; relativeIndex++) {
                array[relativeIndex].value = startValue + relativeIndex;
            }
        }
    }

    public static void quicksort(TestInteger[] array, int start, int end) {
        if (start < end) {
            int pivot = partition(array, start, end);
            quicksort(array,start,pivot - 1);
            quicksort(array,pivot + 1, end);
        }
    }

    public static int partition(TestInteger[] array, int start, int end) {
        TestInteger pivotValue = array[end];

        //System.out.println("Start: "+start+" end: "+end);
        int endSmall = start - 1;
        for (int beginUnsorted = start; beginUnsorted <= end - 1; beginUnsorted++) {
            if (array[beginUnsorted].compareTo(pivotValue) <= 0) {
                endSmall++;
                TestInteger temp = array[endSmall];
                array[endSmall] = array[beginUnsorted];
                array[beginUnsorted] = temp;
            }
        }
        //System.out.println("\tendSmall: "+endSmall+" end: "+end);
        TestInteger temp = array[endSmall + 1];
        array[endSmall + 1] = array[end];
        array[end] = temp;

        return endSmall + 1;

    }

    public static void resetCounter() { TestInteger.counter = 0; }

    public int compareTo(TestInteger t) {
        TestInteger.counter++;

        if (this.value == t.value) {
            return 0;
        } else if (this.value < t.value) {
            return -1;
        } else {
            return 1;
        }
    }

    public static boolean isSorted(TestInteger[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].value > array[i+1].value) { return false; }
        }
        return true;
    }

    public int[] getValues(TestInteger[] array){
        int[] numberArray = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            numberArray[i] = array[i].value;
        }

        return numberArray;
    }
}