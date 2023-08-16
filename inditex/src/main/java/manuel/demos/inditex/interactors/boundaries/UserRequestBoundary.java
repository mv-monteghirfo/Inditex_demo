package manuel.demos.inditex.interactors.boundaries;

import manuel.demos.inditex.exceptions.ProductNotFoundException;
import manuel.demos.inditex.interactors.models.RequestModel;
import manuel.demos.inditex.interactors.models.ResponseModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public interface UserRequestBoundary {

    public ResponseModel queryForProduct(RequestModel requestModel) throws ProductNotFoundException;

}
