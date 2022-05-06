package org.example;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;


class QuickSortTask<T extends Comparable<T>> extends RecursiveAction {

  // Define class variables
  T a[];
  
  int left;
  int right;

  // Define  and implement constructors
  //public QuickSortTask(ArrayList<T> a) {
   // this(a, 0, a.size()- 1);
  //}
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
      //if (a[j] < x) {
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
