package manuel.demos.inditex.interactors.boundaries;

import lombok.RequiredArgsConstructor;
import manuel.demos.inditex.entities.jpa.BaseProductSQLEntity;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import manuel.demos.inditex.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BaseProductQuery implements ProductQueryDsGateway {

    final ProductRepository productRepository;

    @Override
    public boolean exists(int productId) {
        return productRepository.existsByProductId(productId);
    }

    @Override
    public boolean doesNotExist(int productId) {
        return !exists(productId);
    }

    public List<BaseProductSQLEntity> findByProductIdAndAppDateBetweenByPriority(int productId, LocalDateTime appDate) throws ProductNotFoundException {

        List<BaseProductSQLEntity> listOfProductsInsideRange = productRepository.findByProductIdAndStartDateBeforeAndEndDateAfter(productId, appDate, appDate);
        if (listOfProductsInsideRange.isEmpty()) throw new ProductNotFoundException("Producto no encontrado");

        return listOfProductsInsideRange;
    }

}
