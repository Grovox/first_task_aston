package grovox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private List<Integer> data;

    @BeforeEach
    void prepareData(){
        data = new ArrayList<Integer>();
    }
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void add(int element) {
        int nextIndex = data.size();
        int size = data.size();

        data.add(element);

        assertEquals(data.get(nextIndex), element);
        assertEquals(data.size(), size + 1);
    }

    @ParameterizedTest
    @CsvSource({"1, 11", "3, 22", "5, 33"})
    void addByIndex(int index, int element) {
        data.add(123);
        data.add(123);
        data.add(123);
        data.add(123);
        data.add(123);
        int size = data.size();

        data.add(index, element);

        assertEquals(data.get(index), element);
        assertEquals(data.size(), size + 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1})
    void addByNotExistingIndex(int index){
        int size = data.size();

        assertThrows(IndexOutOfBoundsException.class,() -> {
            data.add(index, 123);
        }, "Index " + index + " out of bounds for length " + size);
    }

    @Test
    void addAllInEmptyData() {
        int nextIndex = data.size();
        int size = data.size();
        Integer[] addData = new Integer[]{1, 2, 3};

        data.addAll(addData);

        for (int i = 0; i < addData.length; i++){
            assertEquals(data.get(nextIndex + i), addData[i]);
        }
        assertEquals(data.size(), size + addData.length);
    }

    @Test
    void addAllInNotEmptyData() {
        data.add(123);
        data.add(123);
        data.add(123);
        int nextIndex = data.size();
        int size = data.size();
        Integer[] addData = new Integer[]{1, 2, 3};

        data.addAll(addData);

        for (int i = 0; i < addData.length; i++){
            assertEquals(data.get(nextIndex + i), addData[i]);
        }
        assertEquals(data.size(), size + addData.length);
    }
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void remove(int index) {
        Integer[] addData = new Integer[]{1, 2, 3};
        data.addAll(addData);
        int size = data.size();

        int removeElement = data.remove(index);

        assertEquals(removeElement, addData[index]);
        Set<Integer> notRemoveElement = new HashSet<>();
        for (int i = 0; i < data.size(); i++)
            notRemoveElement.add(data.get(i));
        for (int i = 0; i < addData.length; i++){
            if (i != index){
               assertEquals(notRemoveElement.contains(addData[i]), true);
            }
        }
        assertEquals(data.size(), size - 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    void removeByNotExistingIndex(int index){
        int size = data.size();

        assertThrows(IndexOutOfBoundsException.class,() -> {
            data.remove(index);
        }, "Index " + index + " out of bounds for length " + size);
    }

    @Test
    void clear() {
        Integer[] addData = new Integer[]{1, 2, 3};
        data.addAll(addData);

        data.clear();

        assertEquals(data.size(), 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void get(int index) {
        Integer[] addData = new Integer[]{1, 2, 3};
        data.addAll(addData);

        int element = data.get(index);

        assertEquals(element, addData[index]);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    void getByNotExistingIndex(int index){
        int size = data.size();

        assertThrows(IndexOutOfBoundsException.class,() -> {
            data.get(index);
        }, "Index " + index + " out of bounds for length " + size);
    }
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void set(int index) {
        Integer[] addData = new Integer[]{1, 2, 3};
        data.addAll(addData);

        int oldElement = data.set(index, 123);

        assertEquals(oldElement, addData[index]);
        assertEquals(data.get(index), 123);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    void setByNotExistingIndex(int index){
        int size = data.size();

        assertThrows(IndexOutOfBoundsException.class,() -> {
            data.set(index, 123);
        }, "Index " + index + " out of bounds for length " + size);
    }

    @Test
    void sizeByEmptyData() {

        int size = data.size();

        assertEquals(size, 0);
    }

    @Test
    void sizeByNotEmptyData() {
        Integer[] addData = new Integer[]{1, 2, 3};
        data.addAll(addData);

        int size = data.size();

        assertEquals(size, addData.length);
    }

    @Test
    void isEmptyByEmptyData() {

        boolean isEmpty = data.isEmpty();

        assertEquals(isEmpty, true);
    }

    @Test
    void isEmptyByNotEmptyData() {
        data.add(1);

        boolean isEmpty = data.isEmpty();

        assertEquals(isEmpty, false);
    }

    @Test
    void grow(){
        Integer[] addData = new Integer[100];

        data.addAll(addData);
        data.add(123);

        assertEquals(data.size(), addData.length + 1);
        assertEquals(data.get(addData.length), 123);
    }

    @Test
    void overflow() throws NoSuchMethodException {
        data.addAll(new Integer[100]);
        Method method = ArrayList.class.getDeclaredMethod("grow", int.class);
        method.setAccessible(true);

        assertThrows(Exception.class,() -> {
            method.invoke(data, Integer.MAX_VALUE + data.size());
        }, "Required array length " + 100 + " + " + Integer.MAX_VALUE + " is too large");

    }

}