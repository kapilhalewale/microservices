package com.kp.product.service;

import com.kp.product.dto.ProductDto;
import com.kp.product.entity.Product;
import com.kp.product.exception.ProductAlreadyExistsException;
import com.kp.product.exception.ResourceNotFoundException;
import com.kp.product.mapper.ProductMapper;
import com.kp.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto, new Product());
        Optional<Product> productOptional = productRepository.findByName(productDto.getName());
        if (productOptional.isPresent()) {
            throw new ProductAlreadyExistsException("Product already exists with given name " + productDto.getName());
        }
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getProducts() {
        return null;
    }
    @Override
    public ProductDto getProductDetails(long productId) {
        Optional<Product> productObj = Optional.ofNullable(productRepository.
                findById(productId).orElseThrow(
                        () -> new ResourceNotFoundException("Product", "Product Id", String.valueOf(productId))
                ));
        ProductDto productDto = ProductMapper.mapToProductDto(productObj.get(), new ProductDto());
        return productDto;
    }
}