package org.resala.Service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommonCRUDService<T> {
    ResponseEntity<Object> create(List<T> dto);

    ResponseEntity<Object> archive(T dto);

    ResponseEntity<Object> update(T newDto);
}
