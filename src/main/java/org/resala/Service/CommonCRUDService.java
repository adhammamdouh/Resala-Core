package org.resala.Service;

import org.springframework.http.ResponseEntity;

public interface CommonCRUDService<T> {
    ResponseEntity<Object> create(T obj);

    ResponseEntity<Object> delete(T obj);

    ResponseEntity<Object> update(T newObj);
}
