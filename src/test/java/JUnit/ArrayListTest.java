package JUnit;

import homework1.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private ArrayList<String> arrayList;

    @BeforeEach
    void setUp() {
        arrayList = new ArrayList<>();
    }

    @Test
    void testAdd() {
        Assertions.assertTrue(arrayList.add("Hello"));
        assertEquals(1, arrayList.size());
        assertEquals("Hello", arrayList.get(0));
    }

    @Test
    void testAddWithNull() {
        assertFalse(arrayList.add(null));
        assertEquals(0, arrayList.size());
    }

    @Test
    void testAddAtIndex() {
        arrayList.add("One");
        arrayList.add("Two");

        arrayList.add(1, "Three");

        assertEquals(3, arrayList.size());
        assertEquals("One", arrayList.get(0));
        assertEquals("Three", arrayList.get(1));
        assertEquals("Two", arrayList.get(2));
    }

    @Test
    void testAddAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(-1, "Invalid"));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(1, "Invalid"));
    }

    @Test
    void testRemove() {
        arrayList.add("One");
        arrayList.add("Two");
        arrayList.add("Three");

        assertEquals("Two", arrayList.remove("Two"));
        assertEquals(2, arrayList.size());
        assertEquals("One", arrayList.get(0));
        assertEquals("Three", arrayList.get(1));

        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(2));
    }

    @Test
    void testRemoveNonExistingElement() {
        arrayList.add("One");
        arrayList.add("Two");

        Assertions.assertNull(arrayList.remove("Three"));
        assertEquals(2, arrayList.size());
    }

    @Test
    void testClear() {
        arrayList.add("One");
        arrayList.add("Two");
        arrayList.clear();
        assertEquals(0, arrayList.size());
    }

    @Test
    void testSet() {
        arrayList.add("One");
        arrayList.add("Two");

        String previousValue = arrayList.set(1, "Three");
        assertEquals("Two", previousValue);
        assertEquals("Three", arrayList.get(1));
    }

    @Test
    void testSetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(-1, "Invalid"));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(0, "Invalid"));
    }

    @Test
    void testGet() {
        arrayList.add("One");
        arrayList.add("Two");
        assertEquals("One", arrayList.get(0));
        assertEquals("Two", arrayList.get(1));
    }

    @Test
    void testGetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(0));
    }

    @Test
    void testQuickSort() {
        arrayList.add("Banana");
        arrayList.add("Apple");
        arrayList.add("Cherry");

        arrayList.quickSort();

        assertEquals("Apple", arrayList.get(0));
        assertEquals("Banana", arrayList.get(1));
        assertEquals("Cherry", arrayList.get(2));
    }

    @Test
    void testQuickSortWithNonComparable() {
        ArrayList<Object> nonComparableList = new ArrayList<>();
        nonComparableList.add(new Object());
        assertThrows(ClassCastException.class, nonComparableList::quickSort);
    }

    @Test
    void testCapacity() {
        assertEquals(10, arrayList.capacity());
        for (int i = 0; i < 15; i++) {
            arrayList.add("Element " + i);
        }
        Assertions.assertTrue(arrayList.capacity() > 10);
    }

    @Test
    void testIncreaseCapacity() {
        int initialCapacity = arrayList.capacity();
        for (int i = 0; i < initialCapacity + 5; i++) {
            arrayList.add("Element " + i);
        }
        Assertions.assertTrue(arrayList.capacity() > initialCapacity);
    }

    @Test
    void testAddAndRemove() {
        for (int i = 0; i < 1000; i++) {
            arrayList.add("Item " + i);
        }

        assertEquals(1000, arrayList.size());

        for (int i = 0; i < 1000; i++) {
            assertTrue(arrayList.get(i).equals("Item " + i));
        }

        for (int i = 0; i < 1000; i++) {
            assertEquals("Item " + i, arrayList.remove("Item " + i));
        }

        assertEquals(0, arrayList.size());
    }
}