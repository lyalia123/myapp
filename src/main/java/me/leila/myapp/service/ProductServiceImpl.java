package me.leila.myapp.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.leila.myapp.controller.dto.CreateProductRequest;
import me.leila.myapp.controller.dto.UpdateProductRequest;
import me.leila.myapp.controller.dto.ProductDto;
import me.leila.myapp.model.Product;
import me.leila.myapp.model.Usr;
import me.leila.myapp.repository.ProductRepository;
import me.leila.myapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public ProductDto create(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        Usr user = userService.getUserById(request.getUsrId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        product.setUsr(user);

        return toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return toDto(product);
    }

    @Override
    public ProductDto update(UpdateProductRequest request) {
        Product product = productRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (!product.getUsr().getId().equals(request.getUsrId())) {
            throw new SecurityException("You can update only your own product.");
        }

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());

        return toDto(productRepository.save(product));
    }

    @Override
    public void delete(Long id, Long usrId) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (!Objects.equals(product.getUsr().getId(), usrId)) {
            throw new SecurityException("You can delete only your own product.");
        }

        productRepository.delete(product);
    }

    private ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setUsrId(product.getUsr().getId());
        return dto;
    }
}
