package database;

import java.io.Serializable;
import java.util.List;

public interface DataBase {

    void save(Object o);

    <T> T fetch(Class<T> tClass, Serializable id);

    void update(Object o);

    void delete(Object o);

    <E> List<E> fetchAll(Class<E> entity);


}
