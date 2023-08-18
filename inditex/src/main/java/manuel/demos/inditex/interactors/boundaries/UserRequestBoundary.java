package manuel.demos.inditex.interactors.boundaries;

import manuel.demos.inditex.exceptions.ProductNotFoundException;
import manuel.demos.inditex.interactors.models.RequestModel;
import manuel.demos.inditex.interactors.models.ResponseModel;
import org.springframework.stereotype.Component;

/**
 * This boundary class works as a connector to be implemented by compatible drivers for the client to make queries
 * about products
 */
@Component
public interface UserRequestBoundary {

    ResponseModel queryForProduct(RequestModel requestModel) throws ProductNotFoundException;

}
