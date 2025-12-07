package repository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T> {
    private final List<T> store = new ArrayList<>();

    public void save(T t) { store.add(t); }
    public List<T> findAll() { return new ArrayList<>(store); }
    public void clear() { store.clear(); }
}

