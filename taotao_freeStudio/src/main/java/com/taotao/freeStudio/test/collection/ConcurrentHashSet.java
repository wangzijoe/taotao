package com.taotao.freeStudio.test.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于ConcurrentHashMap实现 线程安全的 Set
 * @param <E>
 */
public class ConcurrentHashSet<E> implements Set<E> {

    private final Map<E, Object> map;

    private static final Object OBJ = new Object();

    public ConcurrentHashSet() {
        this.map = new ConcurrentHashMap<>();
    }


    public ConcurrentHashSet(int size) {
        this.map = new ConcurrentHashMap<>(size);
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.map.containsKey(o);
    }

    @Override
    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }

    @Override
    public Object[] toArray() {
        return this.map.keySet().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.map.keySet().toArray(a);
    }

    @Override
    public boolean add(E e) {
        return this.map.put(e, OBJ) == null;
    }

    @Override
    public boolean remove(Object o) {
        return this.map.remove(o) == null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.map.keySet().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e : c) {
            if (this.map.put(e, OBJ) == null) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.map.clear();
    }
}
