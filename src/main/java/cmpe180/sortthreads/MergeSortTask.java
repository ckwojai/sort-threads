package cmpe180.sortthreads;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MergeSortTask<T extends Comparable<T>> extends RecursiveAction {
    final T[] arr; final int l, r;
    MergeSortTask(T[] arr, int l, int r) {
        this.arr = arr; this.l = l; this.r = r;
    }
    MergeSortTask(T[] arr) {
        this(arr, 0, arr.length);
    }
    static final int THRESHOLD = 100;
    @Override
    protected void compute() {
        if (r-l < THRESHOLD) {
            Arrays.sort(arr, l, r);
        } else {
            int mid = (l+r)  >>> 1;
            invokeAll(new MergeSortTask<T>(arr, l, mid), new MergeSortTask<T>(arr, mid, r));
            merge(l, mid, r);
        }
    }
    void merge(int l, int mid, int r) {
        T[] buffer = Arrays.copyOfRange(arr, l, mid);
        for(int i=0, j=l, k=mid; i < buffer.length; j++) {
            T smallerElement = (k == r || buffer[i].compareTo(arr[k]) < 0 ? buffer[i++] : arr[k++]);
            arr[j]= smallerElement;
        }
    }
}
