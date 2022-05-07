package cmpe180.sortthreads;


import java.util.concurrent.ForkJoinPool;

public class QuickSortTest {
  public static void main(String[] args) {
    ForkJoinPool fjPool = new ForkJoinPool();
    Integer[] a = new Integer[3333344];

    for (int i = 0; i < a.length; i++) {
      int k = (int) (Math.random() * 22222);
      a[i] = k;
    }

    //QuickSortTask<Integer> forkJoinQuicksortTask = new QuickSortTask<>(a,0, a.length - 1);
    //long start = System.nanoTime();
    //fjPool.invoke(forkJoinQuicksortTask);
    //System.out.println("Time multi-threaded: " + (System.nanoTime() - start));



    // Instantiate naiveQuickSort

    QuickSortNaive naiveQuickSort = new QuickSortNaive() ;
   long start2 = System.nanoTime();
   naiveQuickSort.quickSort(a,0, a.length - 1);
   System.out.println("Time single-threaded: " + (System.nanoTime() - start2));
    
    

  }
}
