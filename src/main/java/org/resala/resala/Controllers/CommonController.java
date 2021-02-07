package org.resala.resala.Controllers;


import org.springframework.http.ResponseEntity;

public interface CommonController<T> {
    ResponseEntity<Object> insert(T obj);
    ResponseEntity<Object> delete(T obj);
    ResponseEntity<Object> update(T newObj);
    ResponseEntity<Object> getAll();

}
