package cmpe180.sortthreads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.RecursiveAction;

public class MergeSortTask<T extends Comparable> extends RecursiveAction {
    final ArrayList<T> arr; final int l, r;
    MergeSortTask(ArrayList<T> arr, int l, int r) {
        this.arr = arr; this.l = l; this.r = r;
    }
    MergeSortTask(ArrayList<T> arr) {
        this(arr, 0, arr.size());
    }
    static final int THRESHOLD = 100;
    @Override
    protected void compute() {
        if (r-l < THRESHOLD) {
            Collections.sort(arr.subList(l, r));
        } else {
            int mid = (l+r)  >>> 1;
            invokeAll(new MergeSortTask<T>(arr, l, mid), new MergeSortTask<T>(arr, mid, r));
            merge(l, mid, r);
        }
    }
    void merge(int l, int mid, int r) {
        ArrayList<T> buffer = new ArrayList<>(arr.subList(l, mid));
        for(int i=0, j=l, k=mid; i < buffer.size(); j++) {
            T smallerElement = (k == r || buffer.get(i).compareTo(arr.get(k)) < 0 ? buffer.get(i++) : arr.get(k++));
            arr.set(j, smallerElement);
        }
    }
}
