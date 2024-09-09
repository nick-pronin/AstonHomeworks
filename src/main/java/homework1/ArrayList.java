package homework1;

import java.util.Comparator;

public class ArrayList<T> {
    private T[] elements;
    private int elementsCount;
    private int arrayCapacity;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость не может быть отрицательной");
        }
        this.arrayCapacity = capacity;
        this.elements = (T[]) new Object[capacity];
        this.elementsCount = 0;
    }

    public boolean add(T item) {
        if (item == null) {
            return false;
        }
        ensureCapacity();
        elements[elementsCount++] = item;
        return true;
    }

    public boolean add(int index, T element) {
        if (element == null) {
            return false;
        }
        if (index < 0 || index > elementsCount) {
            throw new IndexOutOfBoundsException("Ошибка: индекс " + index + " за пределами допустимых границ");
        }
        ensureCapacity();
        for (int i = elementsCount; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        elementsCount++;
        return true;
    }

    public T remove(T elem) {
        if (elem == null) {
            return null;
        }
        for (int i = 0; i < elementsCount; i++) {
            if (elements[i].equals(elem)) {
                T removedElement = elements[i];
                for (int k = i; k < elementsCount - 1; k++) {
                    elements[k] = elements[k + 1];
                }
                elements[elementsCount - 1] = null;
                elementsCount--;
                return removedElement;
            }
        }
        return null;
    }

    public void clear() {
        for (int i = 0; i < elementsCount; i++) {
            elements[i] = null;
        }
        elementsCount = 0;
    }

    public T set(int index, T item) {
        if (index < 0 || index >= elementsCount) {
            throw new IndexOutOfBoundsException("Ошибка: индекс " + index + " за пределами допустимых границ");
        }
        T prevItem = elements[index];
        elements[index] = item;
        return prevItem;
    }

    public T get(int index) {
        if (index < 0 || index >= elementsCount) {
            throw new IndexOutOfBoundsException("Ошибка: индекс " + index + " за пределами допустимых границ");
        }
        return elements[index];
    }

    public void quickSort() {
        if (elementsCount > 0 && elements[0] instanceof Comparable) {
            Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
            recQuickSort(0, elementsCount - 1, comparator);
        } else {
            throw new ClassCastException("Элементы не реализуют Comparable, сортировка невозможна.");
        }
    }

    private void recQuickSort(int left, int right, Comparator<? super T> comparator) {
        if (left < right) {
            T pivot = elements[right];
            int pivotIndex = partitionIt(left, right, pivot, comparator);
            recQuickSort(left, pivotIndex - 1, comparator);
            recQuickSort(pivotIndex + 1, right, comparator);
        }
    }

    private int partitionIt(int left, int right, T pivot, Comparator<? super T> comparator) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (leftPtr < right && comparator.compare(elements[++leftPtr], pivot) < 0);
            while (rightPtr > left && comparator.compare(elements[--rightPtr], pivot) > 0);
            if (leftPtr >= rightPtr) {
                break;
            } else {
                swap(leftPtr, rightPtr);
            }
        }
        swap(leftPtr, right);
        return leftPtr;
    }

    public void display() {
        for (int i = 0; i < elementsCount; i++) {
            System.out.println(elements[i]);
        }
        System.out.println();
    }

    public int capacity() {
        return arrayCapacity;
    }

    public int size() {
        return elementsCount;
    }

    private void swap(int index1, int index2) {
        T temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

    private void ensureCapacity() {
        if (elementsCount == elements.length) {
            elements = increaseCapacity();
        }
    }

    private T[] increaseCapacity() {
        int newCapacity = elements.length + (elements.length / 2) + 1;
        T[] newElements = (T[]) new Object[newCapacity];
        for (int i = 0; i < elementsCount; i++) {
            newElements[i] = elements[i];
        }
        arrayCapacity = newCapacity;
        return newElements;
    }
}