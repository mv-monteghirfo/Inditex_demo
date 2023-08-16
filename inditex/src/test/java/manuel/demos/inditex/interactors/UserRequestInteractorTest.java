package manuel.demos.inditex.interactors;

import manuel.demos.inditex.entities.BaseProduct;
import manuel.demos.inditex.entities.Currency;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import manuel.demos.inditex.interactors.boundaries.BaseProductQuery;
import manuel.demos.inditex.interactors.boundaries.ProductQueryDsGateway;
import manuel.demos.inditex.interactors.models.RequestModel;
import manuel.demos.inditex.interactors.models.ResponseModel;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.Month;

import static manuel.demos.inditex.interactors.UserRequestInteractorTest.TestData.requestModel;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest
class UserRequestInteractorTest {

    @Autowired
    UserRequestInteractor userRequestInteractor;
    @MockBean
    BaseProductQuery productQueryDsGateway;
    @MockBean
    ProductPresenter productPresenter;

    @Test
    @DisplayName("Given a valid request comes," +
                    "When the query is performed," +
                    "Then the Success View is returned")
    void expectedIncomeResultsInPrepareSuccessView() throws ProductNotFoundException {

        given(productQueryDsGateway.doesNotExist(TestData.itemId))
                .willReturn(true);

        ResponseModel responseModel = userRequestInteractor.queryForProduct(requestModel);

        BDDAssertions.then(responseModel).isEqualTo(TestData.responseModel);
        then(productPresenter).should().prepareFailView("Producto inexistente");
    }

    static class TestData {

        final static int itemId = 1;
        final static int brandId = 1;
        final static LocalDateTime applicationDate = LocalDateTime.of(
                2023,
                Month.AUGUST,
                16,
                16,
                13,
                23);

        final static LocalDateTime startDate = LocalDateTime.of(
                2023,
                Month.AUGUST,
                10,
                16,
                13,
                23);

        final static LocalDateTime endDate = LocalDateTime.of(
                2023,
                Month.AUGUST,
                20,
                16,
                13,
                23);

        final static BaseProduct itemToBeReturned = new BaseProduct(
                itemId, brandId, startDate, endDate, 2, 1, 10, Currency.EUR);

        final static RequestModel requestModel = new RequestModel(itemId, brandId, applicationDate);
        final static ResponseModel responseModel = new ResponseModel(
                itemToBeReturned.getProductId(),
                itemToBeReturned.getBrandId(),
                itemToBeReturned.getFee(),
                itemToBeReturned.getStartDate(),
                itemToBeReturned.getEndDate(),
                itemToBeReturned.getPrice() * itemToBeReturned.getFee()/10);

    }

}
