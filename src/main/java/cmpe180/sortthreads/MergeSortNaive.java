package cmpe180.sortthreads;

import java.util.Arrays;

public class MergeSortNaive<T extends Comparable<? super T>> {
    static final int THRESHOLD = 100;
    protected void sort(T[] arr, int l, int r) {
        if (r-l < THRESHOLD) {
            Arrays.sort(arr, l, r);
        } else {
            int mid = (l+r)  >>> 1;
            sort(arr, l, mid);
            sort(arr, mid, r);
            merge(arr, l, mid, r);
        }
    }
    void merge(T[] arr, int l, int mid, int r) {
        T[] buffer = Arrays.copyOfRange(arr, l, mid);
        for(int i=0, j=l, k=mid; i < buffer.length; j++) {
            T smallerElement = (k == r || buffer[i].compareTo(arr[k]) < 0 ? buffer[i++] : arr[k++]);
            arr[j]= smallerElement;
        }
    }

}
