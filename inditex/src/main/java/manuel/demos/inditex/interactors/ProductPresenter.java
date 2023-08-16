package manuel.demos.inditex.interactors;

import manuel.demos.inditex.interactors.models.ResponseModel;
import org.springframework.stereotype.Component;

@Component
interface ProductPresenter {

    ResponseModel prepareSuccessView(ResponseModel responseModel);

    ResponseModel prepareFailView(String errorMessage);

}
