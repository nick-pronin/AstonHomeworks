package homework1;

import java.util.Comparator;

/**
 * Класс {@code ArrayList} реализует динамический массив, который позволяет
 * хранить и управлять объектами любого типа. Он поддерживает основные
 * операции над коллекциями, такие как добавление, удаление, доступ и сортировка
 * элементов. Класс использует массив для хранения элементов, который может
 * увеличиваться по мере необходимости.
 *
 * @param <T> тип элементов, которые будут храниться в этом списке
 *
 * <p>Основные функциональные возможности класса:</p>
 * <ul>
 *     <li>Добавление элементов в конец списка и в произвольную позицию</li>
 *     <li>Удаление элементов</li>
 *     <li>Получение и замена элементов по индексу</li>
 *     <li>Сортировка элементов с использованием алгоритма быстрой сортировки</li>
 *     <li>Очистка списка</li>
 *     <li>Отображение элементов</li>
 * </ul>
 */
public class ArrayList<T> {
    private T[] elements;
    private int elementsCount;
    private int arrayCapacity;
    private static final int INITIAL_VALUE_OF_ARRAY_CAPACITY = 10;


    /**
     * Конструктор по умолчанию, создающий {@code ArrayList} с начальной
     * вместимостью 10.
     */
    public ArrayList() {
        this(INITIAL_VALUE_OF_ARRAY_CAPACITY);
    }

    /**
     * Конструктор, создающий {@code ArrayList} указанной вместимости.
     *
     * @param capacity начальная вместимость списка
     * @throws IllegalArgumentException если указанная вместимость отрицательна
     */
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость не может быть отрицательной");
        }
        this.arrayCapacity = capacity;
        this.elements = (T[]) new Object[capacity];
        this.elementsCount = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param item элемент, который нужно добавить
     * @return {@code true} если элемент был добавлен, иначе {@code false}
     */
    public boolean add(T item) {
        if (item == null) {
            return false;
        }
        ensureCapacity();
        elements[elementsCount++] = item;
        return true;
    }

    /**
     * Добавляет элемент на указанную позицию в списке.
     *
     * @param index индекс, по которому нужно добавить элемент
     * @param element элемент, который нужно добавить
     * @return {@code true} если элемент был добавлен, иначе {@code false}
     * @throws IndexOutOfBoundsException если индекс не в допустимых границах
     */
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

    /**
     * Удаляет из списка первый найденный эемент, указанный в качестве аргумента.
     *
     * @param elem элемент, который нужно удалить
     * @return удаленный элемент или {@code null}, если элемент не найден
     */
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

    /**
     * Удаляет все элементы в {@code ArrayList}.
     */
    public void clear() {
        for (int i = 0; i < elementsCount; i++) {
            elements[i] = null;
        }
        elementsCount = 0;
    }

    /**
     * Заменяет элемент по указанному индексу на {@code item}.
     *
     * @param index индекс элемента для замены
     * @param item новый элемент
     * @return старый элемент, который был заменен
     * @throws IndexOutOfBoundsException если индекс не в допустимых границах
     */
    public T set(int index, T item) {
        if (index < 0 || index >= elementsCount) {
            throw new IndexOutOfBoundsException("Ошибка: индекс " + index + " за пределами допустимых границ");
        }
        T prevItem = elements[index];
        elements[index] = item;
        return prevItem;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента для получения
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс не в допустимых границах
     */
    public T get(int index) {
        if (index < 0 || index >= elementsCount) {
            throw new IndexOutOfBoundsException("Ошибка: индекс " + index + " за пределами допустимых границ");
        }
        return elements[index];
    }

    /**
     * Сортирует список с использованием алгоритма быстрой сортировки.
     * Элементы должны реализовывать интерфейс {@code Comparable}.
     *
     * @throws ClassCastException если элементы не реализуют {@code Comparable}
     */
    public void quickSort() {
        if (elementsCount > 0 && elements[0] instanceof Comparable) {
            Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
            recQuickSort(0, elementsCount - 1, comparator);
        } else {
            throw new ClassCastException("Элементы не реализуют Comparable, сортировка невозможна.");
        }
    }

    /**
     * Выводит все элементы списка в консоль.
     */
    public void display() {
        for (int i = 0; i < elementsCount; i++) {
            System.out.println(elements[i]);
        }
        System.out.println();
    }

    /**
     * Возвращает текущую вместимость массива.
     *
     * @return текущая вместимость массива
     */
    public int capacity() {
        return arrayCapacity;
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return elementsCount;
    }

    private void swap(int index1, int index2) {
        T temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
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