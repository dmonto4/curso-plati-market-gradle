package com.cursoplatzi.platzimarketG.persistence;

import com.cursoplatzi.platzimarketG.domain.Purchase;
import com.cursoplatzi.platzimarketG.domain.repository.PurchaseRepository;
import com.cursoplatzi.platzimarketG.persistence.crud.CompraCrudRepository;
import com.cursoplatzi.platzimarketG.persistence.entity.Compra;
import com.cursoplatzi.platzimarketG.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;
    @Override
    public List<Purchase> getAll() {
        return purchaseMapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = purchaseMapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return purchaseMapper.toPurchase(compraCrudRepository.save(compra) );
    }
}
