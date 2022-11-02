package be.condorcet.projet3_2.services;

import be.condorcet.projet3_2.entities.Invest;

import java.util.List;

public interface InterfService<T,U> {
    public T create(T t) throws Exception;
    public T read(U id) throws Exception;
    public T update(T t) throws Exception;
    public void delete(T t) throws Exception;

    public List<T> all() throws Exception;

}
