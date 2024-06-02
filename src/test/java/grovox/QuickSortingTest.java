package grovox;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortingTest {

    private ArrayList<Integer> data;
    private static Comparator<Integer> comparator;

    @BeforeEach
    void prepareData(){
        data = new ArrayList<Integer>();
    }

    @BeforeAll
    static void prepareComparator(){
        comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
    }

    @Test
    void runWithComparatorAndEmptyData() {

        boolean result = QuickSorting.run(data, comparator);

        assertEquals(result, false);
    }

    @Test
    void runWithComparator() {
        Integer[] addData = new Integer[]{2, 3, 1, 6, 5, 4};
        Integer[] resultData = new Integer[]{1, 2, 3, 4, 5, 6};
        data.addAll(addData);
        int size = data.size();

        boolean result = QuickSorting.run(data, comparator);

        for (int i = 0; i < data.size(); i++){
            assertEquals(data.get(i), resultData[i]);
        }
        assertEquals(size, data.size());
        assertEquals(result, true);
    }

    @Test
    void RunWithComparableEmptyData() {

        boolean result = QuickSorting.run(data);

        assertEquals(result, false);
    }

    @Test
    void RunWithComparableDate() {
        Integer[] addData = new Integer[]{2, 3, 1, 6, 5, 4};
        Integer[] resultData = new Integer[]{1, 2, 3, 4, 5, 6};
        data.addAll(addData);
        int size = data.size();

        boolean result = QuickSorting.run(data);

        for (int i = 0; i < data.size(); i++){
            assertEquals(data.get(i), resultData[i]);
        }
        assertEquals(size, data.size());
        assertEquals(result, true);
    }
}