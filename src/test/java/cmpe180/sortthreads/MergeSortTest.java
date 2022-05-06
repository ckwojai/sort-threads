package cmpe180.sortthreads;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

class MergeSortTest {
    @Test
    public void MergeSortSimpleTest() {
        final int n = 10000;
        final int max_limit = 100000;
//        final int n = 20;
//        final int max_limit = 20;
        Random rand = new Random();
        Integer[] inputArray = new Integer[n];
        for(int i=0; i<n; i++) {
            Integer r = rand.nextInt(max_limit);
            inputArray[i] = r;
        }
        Integer[] sortedArray = inputArray.clone();
        Arrays.sort(sortedArray);

        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask<Integer>(inputArray);
        pool.invoke(task);
        Assertions.assertArrayEquals(sortedArray, inputArray);
    }
}