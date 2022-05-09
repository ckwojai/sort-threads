package cmpe180.sortthreads;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

class MergeSortTest {
    @Test
    public void MergeSortThreadedTest() {
        final int n = 10000;
        final int max_limit = 100000;
        Random rand = new Random();
        Integer[] inputArray = new Integer[n];
        for(int i=0; i<n; i++) {
            Integer r = rand.nextInt(max_limit);
            inputArray[i] = r;
        }
        Integer[] expectedArray = inputArray.clone();
        Arrays.sort(expectedArray);

        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask<Integer>(inputArray);
        pool.invoke(task);
        Assertions.assertArrayEquals(expectedArray, inputArray);
    }
    @Test
    public void MergeSortNaiveTest() {
        final int n = 10000;
        final int max_limit = 100000;
        Random rand = new Random();
        Integer[] inputArray = new Integer[n];
        for(int i=0; i<n; i++) {
            Integer r = rand.nextInt(max_limit);
            inputArray[i] = r;
        }
        Integer[] expectedArray = inputArray.clone();
        Arrays.sort(expectedArray);
        MergeSortNaive msn = new MergeSortNaive<Integer>();
        msn.sort(inputArray, 0, inputArray.length);
        Assertions.assertArrayEquals(expectedArray, inputArray);
    }

    @Test
    public void MergeSortBenchMark() {
        double[] threaded_result = new double[4];
        double[] naive_result = new double[4];
        int nt = 10; // num of times to run
        int[] ns = {(int) 1.0e4, (int) 1.0e5, (int) 1.0e6, (int) 1.0e7}; // different n to run in the sorting algorithm
        for (int k=0; k<nt; k++) {
            for (int j = 0; j < ns.length; j++) {
                int n = ns[j];
                final int max_limit = n;
                Random rand = new Random();
                Integer[] inputThreaded = new Integer[n];
                for (int i = 0; i < n; i++) {
                    Integer r = rand.nextInt(max_limit);
                    inputThreaded[i] = r;
                }
                Integer[] inputNaive = inputThreaded.clone();

                ForkJoinPool pool = new ForkJoinPool();
                MergeSortTask task = new MergeSortTask<Integer>(inputThreaded);

                long startTime = System.nanoTime();
                pool.invoke(task);
                long endTime = System.nanoTime();
                long threadedDuration = (endTime - startTime) / 1000000;

                startTime = System.nanoTime();
                MergeSortNaive msn = new MergeSortNaive<Integer>();
                msn.sort(inputNaive, 0, inputNaive.length);
                endTime = System.nanoTime();
                long naiveDuration = (endTime - startTime) / 1000000;
                naive_result[j] += naiveDuration;
                threaded_result[j] += threadedDuration;
                System.out.println("For n=" + n + ",   " + "Naive: " + naiveDuration + "; Threaded: " + threadedDuration);
            }
        }
        double[] ratio = new double[4];
        for (int i = 0; i < ns.length; i++) {
            threaded_result[i] /= nt;
            naive_result[i] /= nt;
            ratio[i] = threaded_result[i] / naive_result[i];
        }
        System.out.println("Break Point");
    }
}