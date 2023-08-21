package manuel.demos.inditex.interactors.boundaries;

import manuel.demos.inditex.entities.jpa.BaseProductSQLEntity;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class provides an abstraction over the data layer
 */
@Component
public interface ProductQueryDsGateway {

    boolean exists(int id);

    boolean doesNotExist(int i);

    List<BaseProductSQLEntity> findByProductIdAndAppDateBetweenByPriority(int productId, LocalDateTime appDate) throws ProductNotFoundException;

}