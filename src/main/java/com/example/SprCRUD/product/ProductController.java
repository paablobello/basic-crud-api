package com.example.SprCRUD.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")

public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> registrarProducto(@RequestBody Product product) {
        return productService.NewProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> acualizarProducto(@RequestBody Product product) {
        return productService.NewProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public ResponseEntity<Object> eliminar(@PathVariable("productId") Long id) {
        return productService.deleteProduct(id);
    }

}
