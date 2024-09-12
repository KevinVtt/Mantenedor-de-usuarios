package org.kvittor.java.jdbc.repositorio;

import java.util.List;

public interface Repositorio <T> {

    List<T> listar();

    void agregar(T t);

    void eliminar(Long id);

    void actualizar(T t);

    void actualizarEntrada();

}
