package com.github.daniyar.trademarket.Utils; // importing packages

import java.io.Serializable;
import java.util.List;

public interface DaoInterface <T, Id extends Serializable> { // DAO interface with all methods for creating DAO classes
    public void persist (T entity);
    public void update (T entity);
    public T findById (Id id);
    public void delete (T entity);
    public List<T> findAll();
    public void deleteAll();
}
