package com.alanpoi.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;

/**
 * 参照ArrayList,优化部分逻辑
 *
 * @param <T>
 */
public class AlanList<T> extends AbstractList<T> implements List<T>, Cloneable, Serializable {
    protected static final Logger logger = LoggerFactory.getLogger(AlanList.class);
    private int length;
    private int maxLength = 30;
    private static int MAX_ADD_SIZE = 150;
    private Object[] element = {};
    private static final Object[] emptyElement = {};

    public AlanList(int initSize) {
        this.length = initSize;
        if (initSize > maxLength) maxLength = initSize;
        if (initSize > 0) {
            this.element = new Object[initSize];
        } else if (initSize == 0) {
            this.element = emptyElement;
        } else {
            this.element = emptyElement;
            logger.warn("init size must >0");
        }
    }

    public AlanList(Collection<? extends T> collection) {
        element = collection.toArray();
        if ((length = element.length) != 0) {
            if (element.getClass() != Object[].class)
                element = Arrays.copyOf(element, length, Object[].class);
        } else {
            this.element = emptyElement;
        }
    }

    public AlanList() {
        this.element = new Object[maxLength];
    }

    private synchronized void checkSize(int size) {
        if (size <= maxLength) {
            return;
        }
        maxLength += Math.max(size - length, Math.min(maxLength * 2, MAX_ADD_SIZE));
        element = Arrays.copyOf(element, maxLength);
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    protected Object clone() {
        try {
            AlanList<?> v = (AlanList<?>) super.clone();
            v.element = Arrays.copyOf(element, length);
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public Iterator<T> iterator() {
        LinkedList linkedList = new LinkedList<T>();
        for (int i = 0; i < length; i++) {
            linkedList.add((T) element);
        }
        return linkedList.listIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(element, length);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < length) return (T1[]) Arrays.copyOf(element, length, a.getClass());
        System.arraycopy(element, 0, a, 0, length);
        if (a.length > length)
            a[length] = null;
        return a;
    }

    @Override
    public boolean add(T t) {
        checkSize(length + 1);
        element[length++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < length; index++)
                if (element[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < length; index++)
                if (o.equals(element[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    /**
     * 替换法快速运算
     *
     * @param index
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = length - index - 1;
        if (numMoved > 0)
            System.arraycopy(element, index + 1, element, index,
                    numMoved);
        element[--length] = null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        checkSize(length + numNew);
        System.arraycopy(a, 0, element, length, numNew);
        length += numNew;
        return numNew != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > length || index < 0) {
            logger.warn("index should <= 0; index:{}", index);
            index = 0;
        }
        Object[] a = c.toArray();
        int numNew = a.length;
        checkSize(length + numNew);  // Increments modCount

        int numMoved = length - index;
        if (numMoved > 0)
            System.arraycopy(element, index, element, index + numNew,
                    numMoved);

        System.arraycopy(a, 0, element, index, numNew);
        length += numNew;
        return numNew != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null || c.size() == 0) {
            return false;
        }
        return batchRemove(c, false);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null || c.size() == 0) {
            return false;
        }
        return batchRemove(c, true);
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; i++)
            element[i] = null;
        length = 0;
    }

    @Override
    public T get(int index) {
        if (index >= length) return null;
        return (T) element[index];
    }

    @Override
    public T set(int index, T element) {
        T oldValue = elementData(index);
        this.element[index] = element;
        return oldValue;
    }

    private T elementData(int index) {
        return (T) element[index];
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > length) {
            logger.warn("index should > 0");
            return;
        }
        checkSize(index + 1);  // Increments modCount!!
        System.arraycopy(element, index, element, index + 1,
                length - index);
        this.element[index] = element;
        length++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        T oldValue = elementData(index);

        int numMoved = length - index - 1;
        if (numMoved > 0)
            System.arraycopy(element, index + 1, element, index,
                    numMoved);
        element[--length] = null;

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < length; i++)
                if (element[i] == null)
                    return i;
        } else {
            for (int i = 0; i < length; i++)
                if (((T) element[i]).equals(o))
                    return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = length - 1; i >= 0; i--)
                if (element[i] == null)
                    return i;
        } else {
            for (int i = length - 1; i >= 0; i--)
                if (o.equals(element[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        LinkedList linkedList = new LinkedList<T>();
        for (int i = 0; i < length; i++) {
            linkedList.add((T) element);
        }
        return linkedList.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        LinkedList linkedList = new LinkedList<T>();
        for (int i = 0; i < length; i++) {
            linkedList.add((T) element);
        }
        return linkedList.listIterator(index);
    }

    static void subListRangeCheck(int var0, int var1, int var2) {
        if (var0 < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + var0);
        } else if (var1 > var2) {
            throw new IndexOutOfBoundsException("toIndex = " + var1);
        } else if (var0 > var1) {
            throw new IllegalArgumentException("fromIndex(" + var0 + ") > toIndex(" + var1 + ")");
        }
    }

//    @Override
//    public List<T> subList(int fromIndex, int toIndex) {
//
//    }

    private boolean batchRemove(Collection<?> c, boolean complement) {
        final Object[] elementData = this.element;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < length; r++)
                if (c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
        } finally {
            if (r != length) {
                System.arraycopy(elementData, r,
                        elementData, w,
                        length - r);
                w += length - r;
            }
            if (w != length) {
                for (int i = w; i < length; i++)
                    elementData[i] = null;
                modCount += length - w;
                length = w;
                modified = true;
            }
        }
        return modified;
    }
}
