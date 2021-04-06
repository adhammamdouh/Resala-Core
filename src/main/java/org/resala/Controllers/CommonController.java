package org.resala.Controllers;


import org.springframework.http.ResponseEntity;

public interface CommonController<T> {
    ResponseEntity<Object> add(T obj);
    ResponseEntity<Object> archive(T obj);
    ResponseEntity<Object> update(T newObj);
    ResponseEntity<Object> getAll();

}
