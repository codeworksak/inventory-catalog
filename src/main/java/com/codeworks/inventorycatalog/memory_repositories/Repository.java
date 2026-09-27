package com.codeworks.inventorycatalog.memory_repositories;

import java.util.List;
import java.util.UUID;

public interface Repository<T,TId> {
    T add(T item);
    T getById(UUID id);
    List<T> getByName(String name);
    Boolean delete(TId id);
    List<T> getAll();
}
