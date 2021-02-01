package org.resala.Controllers;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommonController<T> {
    ResponseEntity<Object> insert(T obj);
    ResponseEntity<Object> delete(T obj);
    ResponseEntity<Object> update(T oldObj,T newObj);
    ResponseEntity<Object> getAll();

}
