package org.example.app.server.api_v1.repository.interfaces;

import org.example.app.server.api_v1.entity.BaseEntity;
import org.example.app.server.api_v1.utils.ActionAnswer;

import java.util.List;

public interface IRepository<T extends BaseEntity> {
    ActionAnswer<T> create(T obj);
    ActionAnswer<T> readAll(List<String> excludeColumns, int limit, int offset);
    ActionAnswer<T> update(T obj);
    ActionAnswer<T> delete(Long id);
    ActionAnswer<T> readById(Long id, List<String> excludeColumns);
}
