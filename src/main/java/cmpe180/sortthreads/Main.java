package org.example;

import org.example.QuickSortTask;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {
  public static void main(String[] args) {
    ForkJoinPool fjPool = new ForkJoinPool();
    Integer[] a = new Integer[3333344];

    for (int i = 0; i < a.length; i++) {
      int k = (int) (Math.random() * 22222);
      a[i] = k;
    }

    QuickSortTask<Integer> forkJoinQuicksortTask = new QuickSortTask<>(a,0, a.length - 1);
    long start = System.nanoTime();
    fjPool.invoke(forkJoinQuicksortTask);
    System.out.println("Time: " + (System.nanoTime() - start));
  }
}










