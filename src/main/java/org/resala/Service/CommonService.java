package org.resala.Service;

import org.springframework.http.ResponseEntity;

public interface CommonService<T> {
    ResponseEntity<Object> create(T obj);

    ResponseEntity<Object> delete(T obj);

    ResponseEntity<Object> update(T oldObj, T newObj);

    ResponseEntity<Object> get(int id);
}
