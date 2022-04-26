package cmpe180.sortthreads;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

class MergeSortTest {
    @Test
    public void MergeSortSimpleTest() {
        final int n = 10000;
        final int max_limit = 100000;
        Random rand = new Random();
        ArrayList<Integer> inputArray = new ArrayList<>();
        for(int i=0; i<n; i++) {
            Integer r = rand.nextInt(max_limit);
            inputArray.add(r);
        }
        ArrayList<Integer> sortedArray = new ArrayList<>(inputArray);
        sortedArray.sort(null);

        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask<Integer>(inputArray);
        pool.invoke(task);
        Assertions.assertEquals(sortedArray, inputArray);
    }
}