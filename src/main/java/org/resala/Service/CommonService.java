package org.resala.Service;

import java.util.List;

public interface CommonService <T>{
    T get(int id);
    List<T> getAll();
}
