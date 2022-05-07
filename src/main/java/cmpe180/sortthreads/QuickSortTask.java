package org.example;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

// Quicksort single-threaded implementation
class QuickSortNaive<T extends Comparable<T>>{

   public QuickSortNaive() {
  }

  void quickSort(T[] a, int left, int right)
  {
    if (left < right)
    {
      // pi is partitioning index, arr[p]
      int pi = partition(a, left, right);

      // Separately sort elements before
      // partition and after partition
      quickSort(a, left, pi - 1);
      quickSort(a, pi + 1, right);
    }
  }


  int partition(T[] a, int p, int r) {
    int i = p - 1;
    int res;
    T x = a[r];
    for (int j = p; j < r; j++) {

      res= a[j].compareTo(x); // return -1 if <
      if (res == -1){
        i++;
        swap(a, i, j);
      }
    }
    i++;
    swap(a, i, r);
    return i;
  }

  void swap(T[] a, int p, int r) {
    T t = a[p];
    a[p] = a[r];
    a[r] = t;
  }
}


// QuickSort multi-threaded implementation
class QuickSortTask<T extends Comparable<T>> extends RecursiveAction {

  // Define class variables
  T a[];
  
  int left;
  int right;

  // Define  and implement constructors

  public QuickSortTask(T[] a) {
    this(a, 0, a.length- 1);
  }

  // Paramatrized constructor
  public QuickSortTask(T[] a, int left, int right) {
    this.a = a;
    this.left = left;
    this.right = right;
  }

  // Threshold given via project task
  static int THRESHOLD = 100;
  @Override
  protected void compute() {
      // if size of sublist < 100, then sort directly
    if ((right-left)<THRESHOLD) {
      //Collections.sort(a.subList(left, right));
      Arrays.sort(a, left, right + 1);
    } else {
        // otherwise create subtasks
      int pivotIndex = partition(a, left, right);
      QuickSortTask<T> t1 = new QuickSortTask<T>(a, left,
          pivotIndex - 1);
      QuickSortTask<T> t2 = new QuickSortTask<T>(a, pivotIndex + 1,
          right);
      t1.fork();
      t2.compute();
      t1.join();
    }
  }

  int partition(T[] a, int p, int r) {
    int i = p - 1;
    int res;
    T x = a[r];
    for (int j = p; j < r; j++) {
      res= a[j].compareTo(x); // return -1 if <
      if (res == -1){
        i++;
        swap(a, i, j);
      }
    }
    i++;
    swap(a, i, r);
    return i;
  }

  void swap(T[] a, int p, int r) {
    T t = a[p];
    a[p] = a[r];
    a[r] = t;
  }
}
