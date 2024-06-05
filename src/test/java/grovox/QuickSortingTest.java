package grovox;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    private MyList<People> dataWithComparable;
    private MyList<Student> data;
    private static Comparator<Student> comparator;
    @BeforeEach
    void prepareData(){
        dataWithComparable = new MyArrayList<People>();
        data = new MyArrayList<Student>();
    }

    @BeforeAll
    static void prepareStaticData(){
        comparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.name.compareTo(o2.name);
            }
        };
    }

    @Test
    void runSortingWithComparatorAndEmptyData() {

        boolean result = QuickSort.runSorting(data, comparator);

        assertEquals(result, false);
    }

    @Test
    void runSortingWithComparator() {
        Student[] students = new Student[]{new Student("C"), new Student("D"), new Student("A"), new Student("B"), new Student("E")};
        String[] sortStudentsName = new String[]{"A", "B", "C", "D", "E"};
        data.addAll(students);
        int size = data.size();

        boolean result = QuickSort.runSorting(data, comparator);

        for (int i = 0; i < data.size(); i++){
            assertEquals(data.get(i).name, sortStudentsName[i]);
        }
        assertEquals(size, data.size());
        assertEquals(result, true);
    }

    @Test
    void runSortingWithComparableEmptyData() {

        boolean result = QuickSort.runSorting(dataWithComparable);

        assertEquals(result, false);
    }

    @Test
    void runSortingWithComparableDate() {
        People[] peoples = new People[]{new People("D"), new People("B"), new People("A"), new People("E"), new People("C")};
        String[] sortPeopleName = new String[]{"A", "B", "C", "D", "E"};
        dataWithComparable.addAll(peoples);
        int size = dataWithComparable.size();

        boolean result = QuickSort.runSorting(dataWithComparable);

        for (int i = 0; i < dataWithComparable.size(); i++){
            assertEquals(dataWithComparable.get(i).name, sortPeopleName[i]);
        }
        assertEquals(size, dataWithComparable.size());
        assertEquals(result, true);
    }

    class People implements Comparable<People>{
        String name;

        public People(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(People o) {
            return this.name.compareTo(o.name);
        }
    }

     class Student{
        String name;

        public Student(String name) {
            this.name = name;
        }
    }
}