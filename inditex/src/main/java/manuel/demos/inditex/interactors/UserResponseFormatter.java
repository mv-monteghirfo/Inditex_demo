package manuel.demos.inditex.interactors;

import manuel.demos.inditex.interactors.models.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserResponseFormatter implements ProductPresenter {

    @Override
    public ResponseModel prepareSuccessView(ResponseModel response) {
        // Here is where aggregation bussiness logic would take place
        return response;
    }

    @Override
    public ResponseModel prepareFailView(String error) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, error);
    }

}
