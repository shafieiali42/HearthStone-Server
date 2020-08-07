package database;

import java.io.Serializable;
import java.util.List;

public class DataBaseHandler {

    DataBase dataBase;

    public DataBaseHandler(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void save(Object o) {
        dataBase.save(o);
    }

    public <T> T fetch(Class<T> tClass, Serializable id) {
        return dataBase.fetch(tClass, id);
    }

    public void update(Object o) {
        dataBase.update(o);
    }

    public void delete(Object o) {
        dataBase.delete(o);
    }

    public <E> List<E> fetchAll(Class<E> entity) {
        return dataBase.fetchAll(entity);
    }



}
