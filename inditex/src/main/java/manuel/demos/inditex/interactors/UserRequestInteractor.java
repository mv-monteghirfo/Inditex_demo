package manuel.demos.inditex.interactors;

import lombok.RequiredArgsConstructor;
import manuel.demos.inditex.entities.BaseProductFactory;
import manuel.demos.inditex.entities.jpa.BaseProductSQLEntity;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import manuel.demos.inditex.interactors.boundaries.ProductQueryDsGateway;
import manuel.demos.inditex.interactors.boundaries.UserRequestBoundary;
import manuel.demos.inditex.interactors.models.RequestModel;
import manuel.demos.inditex.interactors.models.ResponseModel;
import manuel.demos.inditex.utility.FinalPriceCalculator;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRequestInteractor implements UserRequestBoundary {

    final BaseProductFactory baseProductFactory;
    final FinalPriceCalculator finalPriceCalculator;
    final ProductQueryDsGateway productQueryDsGateway; // input
    final ProductPresenter productPresenter; // output


    public ResponseModel queryForProduct(RequestModel requestModel) throws ProductNotFoundException {

        if (productQueryDsGateway.doesNotExist(requestModel.getProductId())) {
            return productPresenter.prepareFailView("Producto inexistente");
        }

        List<BaseProductSQLEntity> productList = productQueryDsGateway.findByProductIdAndAppDateBetweenByPriority(requestModel.getProductId(), requestModel.getApplicationDate());
        var product = productList.stream().max(Comparator.comparing(BaseProductSQLEntity::getPriority)).orElseThrow(() -> new ProductNotFoundException("Tarifa no encontrada para los rangos de fechas del producto"));

        ResponseModel response = new ResponseModel(
                product.getId(),
                product.getProductId(),
                product.getBrandId(),
                product.getFee(),
                product.getStartDate(),
                product.getEndDate(),
                finalPriceCalculator.calculatePrice(product.getPrice(), product.getFee()),
                product.getCurrency());

        return productPresenter.prepareSuccessView(response);

    }

}
