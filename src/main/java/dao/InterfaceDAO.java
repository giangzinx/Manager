package dao;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface InterfaceDAO <T>{
    public void add(T t);

    public void update(T t);

    public void delete(T t);

    public T selectById(String id);

    public T selectByName(String name);

    public ArrayList<T> selectAll();

    public ArrayList<T> selectByCondition(T t);
}
