package me.leila.myapp.controller;

import lombok.RequiredArgsConstructor;
import me.leila.myapp.controller.dto.CreateProductRequest;
import me.leila.myapp.controller.dto.UpdateProductRequest;
import me.leila.myapp.controller.dto.ProductDto;
import me.leila.myapp.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PreAuthorize("hasAuthority('SELLER')")
    @PostMapping("/create")
    public ResponseEntity<ProductDto> create(@RequestBody CreateProductRequest request) {
        ProductDto created = productService.create(request);
        return ResponseEntity.ok(created);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/by_id")
    public ResponseEntity<ProductDto> getById(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PreAuthorize("hasAuthority('SELLER')")
    @PutMapping("/update")
    public ResponseEntity<ProductDto> update(@RequestBody UpdateProductRequest request) {
        ProductDto updated = productService.update(request);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasAuthority('SELLER')")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam(name = "id") Long id,@RequestParam(name = "usr_id") Long usrId) {
        productService.delete(id, usrId);
        return ResponseEntity.noContent().build();
    }
}

