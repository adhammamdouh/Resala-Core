package org.resala.Service;

import java.util.List;

public interface CommonService <T>{
    T getById(int id);
    List<T> getAll();
}
