package manuel.demos.inditex.interactors.boundaries;

import manuel.demos.inditex.entities.jpa.BaseProductDataMapper;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Component;

/**
 * This class provides an abstraction over the data layer
 */
@Component
public interface ProductQueryDsGateway {

    boolean exists (int id);

    boolean doesNotExist(int i);

    BaseProductDataMapper findByProductId(int productId) throws ProductNotFoundException;

}
