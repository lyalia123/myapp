package me.leila.myapp.service;


import me.leila.myapp.controller.dto.CreateProductRequest;
import me.leila.myapp.controller.dto.UpdateProductRequest;
import me.leila.myapp.controller.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto create(CreateProductRequest request);
    List<ProductDto> findAll();
    ProductDto findById(Long id);
    ProductDto update(UpdateProductRequest request);
    void delete(Long id, Long usrId);
}
