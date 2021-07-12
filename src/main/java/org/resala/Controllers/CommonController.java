package org.resala.Controllers;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommonController<T> {
    ResponseEntity<Object> add(List<T> obj);
    ResponseEntity<Object> archive(T obj);
    ResponseEntity<Object> update(T newObj);
    ResponseEntity<Object> getAll();

}
