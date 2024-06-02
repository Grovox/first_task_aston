package grovox;

import java.util.Comparator;

/**
 * This is an abstract class that implements a sorting algorithm for an ArrayList.
 *@author Max Artemov
 */
public abstract class QuickSorting{

    /**
     * Starts the sorting process by running the
     * {@code quicksort(List<E> data, Comparator<E> comparator)} method.
     * If an object with data to sort, let it return false.
     * This method accepts a comparator for comparison.
     * @param data The data store that will be sorted.
     * @param comparator A comparator that will compare items when sorting.
     * @return Was the sorting successful.
     * @param <E> The type of data to be sorted.
     */
    public static <E> boolean run(List<E> data, Comparator<E> comparator){
        if (data.isEmpty())
            return false;
        quicksort(data, comparator);
        return true;
    }

    /**
     *  Starts the sorting process by running the
     * {@code quicksort(List<E> data)}  method.
     *  If an object with data to sort, let it return false.
     *  This method accepts data that inherits Comparable
     *  and is able to compare itself with the same data.
     * @param data The data store that will be sorted.
     * @return Was the sorting successful.
     * @param <E> The type of data to be sorted.
     */
    public static <E extends Comparable<E>> boolean run(List<E> data){
        if (data.isEmpty())
            return false;
        quicksort(data);
        return true;
    }

    /**
     * The quicksort method,
     * this method launches its
     * {@code quicksort(List<E> data, Comparator<E> comparator, int start, int end)}
     * extended version with the number of the start and end elements for sorting.
     * @param data The data store that will be sorted.
     * @param comparator A comparator that will compare items when sorting.
     * @param <E> The type of data to be sorted.
     */
    private static <E> void quicksort(List<E> data, Comparator<E> comparator){
        quicksort(data, comparator, 0, data.size() - 1);
    }

    /**
     * Runs the {@code partOfSort(List<E> data, Comparator<E> comparator, int left, int right)}
     * method of sorting the array from the left element to the right element,
     * taking from it the element where sorting stopped {@code int rightStart}.
     * Next, this method launches itself with a range from left to int rightStart
     * and once again itself from int rightStart to right. If, when starting this method,
     * start >= end, the method ends.
     * @param data The data store that will be sorted.
     * @param comparator A comparator that will compare items when sorting.
     * @param start The element to sort.
     * @param end The final element to sort.
     * @param <E> The type of data to be sorted.
     */
    private static <E> void quicksort(List<E> data, Comparator<E> comparator, int start, int end){
        if (start >= end) return;
        int rightStart = partOfSort(data, comparator, start, end);
        quicksort(data, comparator, start, rightStart - 1);
        quicksort(data, comparator, rightStart, end);
    }

    /**
     * Takes the middle one between left and right as a reference element
     * and assigns it as a reference element.
     * Next, the cycle begins until left <= right, in which
     * an element is searched from left to right, when compared through Comparator,
     * more than the reference element is added to left + 1 and from right to left,
     * an element less than the reference element is added to right plus
     * one and if left <= right then these elements change.
     * @param data The data store that will be sorted.
     * @param comparator A comparator that will compare items when sorting.
     * @param left The left edge of the sorted subarray.
     * @param right The right edge of the sorted subarray.
     * @return The final value of left.
     * @param <E> The type of data to be sorted.
     */

    private static <E> int partOfSort(List<E> data, Comparator<E> comparator, int left, int right){
        E pivot = data.get((left + right) / 2);

        while (left <= right) {
            while (comparator.compare(data.get(left), pivot) < 0) left++;
            while (comparator.compare(data.get(right), pivot) > 0) right--;

            if(left <= right){
                E temp = data.get(left);
                data.set(left, data.get(right));
                data.set(right, temp);
                left++;
                right--;
            }
        }
        return left;
    }

    /**
     * The quicksort method,
     * this method launches its
     * {@code quicksort(List<E> data, int start, int end)}
     * extended version with the number of the start and end elements for sorting.
     * @param data The data store that will be sorted.
     * @param <E> The type of data to be sorted.
     */
    private static <E extends Comparable<E>> void quicksort(List<E> data){
        quicksort(data, 0, data.size() - 1);
    }

    /**
     * Runs the {@code partOfSort(List<E> data, int left, int right)}
     * method of sorting the array from the left element to the right element,
     * taking from it the element where sorting stopped {@code int rightStart}.
     * Next, this method launches itself with a range from left to int rightStart
     * and once again itself from int rightStart to right. If, when starting this method,
     * start >= end, the method ends.
     * @param data The data store that will be sorted.
     * @param start The element to sort.
     * @param end The final element to sort.
     * @param <E> The type of data to be sorted.
     */
    private static <E extends Comparable<E>> void quicksort(List<E> data, int start, int end){
        if (start >= end) return;
        int rightStart = partOfSort(data, start, end);
        quicksort(data, start, rightStart - 1);
        quicksort(data, rightStart, end);
    }

    /**
     * Takes the middle one between left and right as a reference element
     * and assigns it as a reference element.
     * Next, the cycle begins until left <= right, in which an element
     * is searched from left to right, when compared through Comparable,
     * more than the reference element is added to left + 1 and from right to left,
     * an element less than the reference element is added to right plus one and
     * if left <= right then these elements change.
     * @param data The data store that will be sorted.
     * @param left The left edge of the sorted subarray.
     * @param right The right edge of the sorted subarray.
     * @return The final value of left.
     * @param <E> The type of data to be sorted.
     */
    private static <E extends Comparable<E>> int partOfSort(List<E> data, int left, int right){
        E pivot = data.get((left + right) / 2);

        while (left <= right) {
            while (data.get(left).compareTo(pivot) < 0) left++;
            while (data.get(right).compareTo(pivot) > 0) right--;

            if(left <= right){
                E temp = data.get( left);
                data.set(left,  data.get(right));
                data.set(right, temp);
                left++;
                right--;
            }
        }
        return left;
    }
}
