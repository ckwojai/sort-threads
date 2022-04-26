package cmpe180.sortthreads;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuickSortTest {
    @Test
    public void QuickSortNormalNumbers() {

        QuickSortTask fb = new QuickSortTask();
        Assertions.assertEquals("1", fb.convert(1));
        Assertions.assertEquals("2", fb.convert(2));
    }

    @Test
    public void QuickSortThreeNumbers() {

        QuickSortTask fb = new QuickSortTask();
        Assertions.assertEquals("Fizz", fb.convert(3));
    }

    @Test
    public void QuickSortFiveNumbers() {

        QuickSortTask fb = new QuickSortTask();
        Assertions.assertEquals("Buzz", fb.convert(5));
    }

    @Test
    public void QuickSortThreeAndFiveNumbers() {

        QuickSortTask fb = new QuickSortTask();
        Assertions.assertEquals("Buzz", fb.convert(5));
    }
}