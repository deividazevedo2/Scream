package br.ifpb.monteiro.ads.projeto2.scream.dao.facade;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mauricio
 * @param <T>
 */
public interface GenericDaoIF<T> extends Serializable {

    int count();

    void create(T entity);

    void edit(T entity);

    T find(Long id);

    List<T> findAll();

    List<T> findRange(int[] range);

    public void remove(T entity);

}
