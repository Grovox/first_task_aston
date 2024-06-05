package grovox;

/**
 *  This is an implementation of the {@link MyList} interface.
 *  All his methods are fully implemented here. This
 *  implementation stores the data in an array that
 *  expands when filled.
 * @param <E> The type of data that will be stored here.
 * @author Max Artemov
 */
public class MyArrayList<E> implements MyList<E> {
    /**
     * Stores the original size of the array.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Stores the maximum size of the array.
     */
    private static final int MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
    /**
     * The array in which we store the elements.
     */
    private Object[] elementData;
    /**
     * An empty array that is the base for the main
     * array before adding the first element.
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    /**
     * A value that indicates the number of elements.
     */
    private int size;

    /**
     * The constructor that creates the default array.
     */
    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * Adds an element to the end of the array,
     * if there is not enough space in the array,
     * then the {@code grow(int minCapacity)} method is run to increase the array.
     * @param element Data to add.
     * @return whether the add operation was successful.
     */
    @Override
    public boolean add(E element) {
        if (size == elementData.length)
            elementData = grow(size + 1);
        elementData[size] = element;
        size++;
        return true;
    }

    /**
     * Adds an element to the specified location in the array
     * if there is not enough space in the array,
     * then the {@code grow(int minCapacity)} method is run to increase the array.
     * By first calling a method {@code checkAddIndex(int index)} that checks if there is an element
     * in the array with this number.
     * @param index The place where the element should be inserted.
     * @param element Data to add.
     * @return whether the add operation was successful.
     */
    @Override
    public boolean add(int index, E element) {
        checkAddIndex(index);
        Object[] elementData = this.elementData;
        if (this.elementData.length == size)
            elementData = grow(size + 1);

        for (int i = size; i >= index; i--)
            elementData[i + 1] = elementData[i];
        elementData[index] = element;

        size++;
        return true;
    }

    /**
     * Adds an array of elements to the end of the array
     * if there is not enough space in the array,
     * then the {@code grow()} method is run to increase the array.
     * @param elements An array of objects to add.
     * @return whether the add operation was successful.
     */
    @Override
    public boolean addAll(E[] elements) {
        if (elements.length == 0)
            return false;
        if (elements.length > elementData.length - size)
            elementData = grow(size + elements.length);
        for (int i = 0; i < elements.length; i++){
            elementData[size] = elements[i];
            size++;
        }
        return true;
    }

    /**
     * Increases the array by creating a new one and
     * copying all stored elements into it.
     * If the required size is larger than possible,
     * an exception will be thrown.
     * If the array is still default and nothing is saved in it,
     * then a new array is returned that has the maximum size of
     * the accepted value and the default.
     * If the array already had this one, then a new array will
     * be created from the current size plus the maximum required
     * for storage and the current size of the broken one shifted by one.
     * @param minCapacity A value indicating the required size.
     * @return A link to the new array
     * @throws OutOfMemoryError with a message "Required array length " + current size + " + " + the number of missing items + " is too large"
     */
    private Object[] grow(int minCapacity){
        if (minCapacity < 0) {
            throw new OutOfMemoryError("Required array length " + size + " + " + (minCapacity - size) + " is too large");
        }
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = oldCapacity + Math.max(minCapacity - oldCapacity, oldCapacity >> 1);
            if (newCapacity < 0 && newCapacity > MAX_ARRAY_LENGTH)
                newCapacity = minCapacity;

            Object[] newElementData = new Object[newCapacity];
            for (int i = 0; i < elementData.length; i++)
                newElementData[i] = elementData[i];

            return elementData = newElementData;
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    /**
     *  Removes the specified element from the array.
     *  By first calling a {@code checkModifierIndex(int index)} method that checks if there
     *  is an element in the array with this number.
     * @param index The place where the element should be deleted.
     * @return Deleted Item.
     */
    @Override
    public E remove(int index) {
        checkModifierIndex(index);
        E result = (E) elementData[index];
        for (int i = index + 1; i < size; i++)
            elementData[i - 1] = elementData[i];
        size--;
        elementData[size] = null;
        return result;
    }

    /**
     * Removes all the elements from our array.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            elementData[i] = null;
        size = 0;
    }

    /**
     *  Returns the specified element.
     *  By first calling a {@code checkModifierIndex(int index)} method
     *  that checks if there is an element in the array with this number.
     * @param index Indicates the element to be returned.
     * @return The item that was requested.
     */
    @Override
    public E get(int index) {
        checkModifierIndex(index);
        return (E) elementData[index];
    }

    /**
     * Replaces the element with a new one.
     * By first calling a {@code checkModifierIndex(int index)} method that checks if
     * there is an element in the array with this number.
     * @param index Indicates the element to be replaced.
     * @param element The element that needs to be replaced.
     * @return The element that has been replaced.
     */
    @Override
    public E set(int index, E element) {
        checkModifierIndex(index);
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * @return The number of stored items.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *  Returns the number of elements in the array.
     * @return Indicates whether the array is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  Checks whether it is possible to add an element to this position.
     * @param index
     * @throws
     */
    private void checkAddIndex(int index){
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
    }

    /**
     *  Checking for the presence of elements in the array.
     * @param index
     * @throws
     */
    private void checkModifierIndex(int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + size);
    }

}
