package org.resala.Service;

import org.springframework.http.ResponseEntity;

public interface CommonCRUDService<T> {
    ResponseEntity<Object> create(T dto);

    ResponseEntity<Object> archive(T dto);

    ResponseEntity<Object> update(T newDto);
}
