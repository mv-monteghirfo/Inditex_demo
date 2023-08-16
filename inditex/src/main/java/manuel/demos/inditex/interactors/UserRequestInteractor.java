package manuel.demos.inditex.interactors;

import lombok.RequiredArgsConstructor;
import manuel.demos.inditex.entities.BaseProductFactory;
import manuel.demos.inditex.entities.jpa.BaseProductDataMapper;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import manuel.demos.inditex.interactors.boundaries.ProductQueryDsGateway;
import manuel.demos.inditex.interactors.boundaries.UserRequestBoundary;
import manuel.demos.inditex.interactors.models.RequestModel;
import manuel.demos.inditex.interactors.models.ResponseModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class UserRequestInteractor implements UserRequestBoundary {

    final BaseProductFactory baseProductFactory;
    final ProductQueryDsGateway productQueryDsGateway; // input
    final ProductPresenter productPresenter; // output


    public ResponseModel queryForProduct(RequestModel requestModel) throws ProductNotFoundException {

        if (productQueryDsGateway.doesNotExist(requestModel.getProductId())){
            return productPresenter.prepareFailView("Producto inexistente");
        }

        BaseProductDataMapper product = productQueryDsGateway.findByProductId(requestModel.getProductId());

        ResponseModel response = new ResponseModel (
                product.getProductId(),
                product.getBrandId(),
                product.getFee(),
                product.getStartDate(),
                product.getEndDate(),
                product.getPrice() * product.getFee()/10);

        return productPresenter.prepareSuccessView(response);

    }

}
