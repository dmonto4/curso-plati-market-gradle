package com.cursoplatzi.platzimarketG.persistence;

import com.cursoplatzi.platzimarketG.domain.Product;
import com.cursoplatzi.platzimarketG.domain.repository.ProductRepository;
import com.cursoplatzi.platzimarketG.persistence.crud.ProductoCrudReposiroty;
import com.cursoplatzi.platzimarketG.persistence.entity.Producto;
import com.cursoplatzi.platzimarketG.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudReposiroty productoCrudReposiroty;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudReposiroty.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int idCategoria){
        List<Producto> productos = productoCrudReposiroty.findByIdCategoria(idCategoria);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudReposiroty.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productoId) {
        return productoCrudReposiroty.findById(productoId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudReposiroty.save(producto));
    }

    @Override
    public void delete(int idProduct){
        productoCrudReposiroty.deleteById(idProduct);
    }
}
