package cmpe180.sortthreads;

public class QuickSortNaive <T extends Comparable<T>>{

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