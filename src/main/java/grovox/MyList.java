package grovox;

/**
 *  This interface describes the basic methods for a dynamic array.
 * @param <E> The type of data that will be stored here.
 * @author Max Artemov
 */
public interface MyList<E>{
    /** Adds an element to the end of the array.
     * @param e Data to add.
     * @return whether the add operation was successful.
     */
    boolean add(E e);

    /**
     * Adds an element to the specified location in the array.
     * @param index The place where the element should be insertedю
     * @param e Data to add.
     * @return whether the add operation was successful.
     */
    boolean add(int index, E e);

    /**
     * Adds an array of elements to the end of the array.
     * @param c An array of objects to add.
     * @return  whether the add operation was successful.
     */
    boolean addAll(E[] c);

    /**
     * Removes the specified element from the array.
     * @param index The place where the element should be deleted.
     * @return Deleted Item.
     */
    E remove(int index);

    /**
     * Removes all the elements from our array.
     */
    void clear();

    /**
     * Returns the specified element.
     * @param index Indicates the element to be returned.
     * @return The item that was requested.
     */
    E get(int index);

    /**
     * Replaces the element with a new one.
     * @param index Indicates the element to be replaced.
     * @param e The element that needs to be replaced.
     * @return The element that has been replaced.
     */
    E set(int index, E e);

    /**
     * Returns the number of elements in the array.
     * @return The number of stored items.
     */
    int size();

    /**
     * Checking for the presence of elements in the array.
     * @return Indicates whether the array is empty.
     */
    boolean isEmpty();
}
