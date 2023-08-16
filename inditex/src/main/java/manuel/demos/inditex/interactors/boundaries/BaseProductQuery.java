package manuel.demos.inditex.interactors.boundaries;

import lombok.RequiredArgsConstructor;
import manuel.demos.inditex.entities.jpa.BaseProductDataMapper;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import manuel.demos.inditex.repositories.ProductRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseProductQuery implements ProductQueryDsGateway {

    final ProductRepository productRepository;

    @Override
    public boolean exists(int id) {
        return productRepository.existsById(id);
    }

    @Override
    public boolean doesNotExist(int id) {
        return !exists(id);
    }

    public BaseProductDataMapper findByProductId(int productId) throws ProductNotFoundException {

        return productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Producto no encontrado"));
    }

}
