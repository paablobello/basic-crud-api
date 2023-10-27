package com.example.SprCRUD.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    HashMap<String,Object> datos;

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<Product> getProducts() {
        return productRepository.findAll();
    }


    public ResponseEntity<Object> NewProduct(Product product) {
        Optional<Product> res = productRepository.findProductByName(product.getName());
         datos= new HashMap<>();

        if(res.isPresent() && product.getId()==null){
            datos.put("error",true);
            datos.put("message","El producto ya existe");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        datos.put("message","Producto guardado correctamente");
        if(product.getId()!=null){
            datos.put("message","Producto actualizado correctamente");
        }
        productRepository.save(product);
        datos.put("data",product);

        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteProduct(Long id) {
        datos= new HashMap<>();
        boolean exists = productRepository.existsById(id);
        if(!exists){
            datos.put("error",true);
            datos.put("message","No existe un producto con ese id");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );


        }
        productRepository.deleteById(id);
        datos.put("message","Producto eliminado correctamente");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }




}
