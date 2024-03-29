package com.cursoplatzi.platzimarketG.persistence.crud;

import com.cursoplatzi.platzimarketG.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudReposiroty extends CrudRepository<Producto, Integer> {

    //@Query(value = "select * from productos where id_categoria = = ?", nativeQuery = true)
    List<Producto> findByIdCategoria(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, Boolean estado);
}
