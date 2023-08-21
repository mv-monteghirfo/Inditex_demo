package manuel.demos.inditex.interactors;

import manuel.demos.inditex.entities.BaseProduct;
import manuel.demos.inditex.entities.Currency;
import manuel.demos.inditex.entities.jpa.BaseProductSQLEntity;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import manuel.demos.inditex.interactors.boundaries.BaseProductQuery;
import manuel.demos.inditex.interactors.models.RequestModel;
import manuel.demos.inditex.interactors.models.ResponseModel;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static manuel.demos.inditex.interactors.UserRequestInteractorTest.TestData.entitiesList;
import static manuel.demos.inditex.interactors.UserRequestInteractorTest.TestData.requestModel;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest
class UserRequestInteractorTest {

    @Autowired
    UserRequestInteractor userRequestInteractor;
    @MockBean
    BaseProductQuery productQueryDsGateway;

    @SpyBean
    ProductPresenter productPresenter;

    @Test
    @DisplayName("Given a valid request comes," +
                    "When the query is performed," +
                    "Then the Success View is returned")
    void expectedIncomeResultsInPrepareSuccessView() throws ProductNotFoundException {

        given(productQueryDsGateway.doesNotExist(TestData.requestModel.getProductId()))
                .willReturn(false);
        given(productQueryDsGateway.findByProductIdAndAppDateBetweenByPriority(requestModel.getProductId(), requestModel.getApplicationDate()))
                .willReturn(entitiesList);

        ResponseModel responseModel = userRequestInteractor.queryForProduct(TestData.requestModel);

        Assertions.assertThat(responseModel).hasFieldOrPropertyWithValue("id", 2);
        then(productPresenter).should().prepareSuccessView(responseModel);
    }

    @Test
    @DisplayName("Given a valid request comes," +
            "When query for a non-existent product," +
            "Then the Fail View is returned")
    void expectedBadRequestPrepareFailView() throws ProductNotFoundException {

        given(productQueryDsGateway.doesNotExist(TestData.requestModel.getProductId()))
                .willReturn(true);

        BDDAssertions.assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> userRequestInteractor.queryForProduct(TestData.requestModel))
                .withMessage("404 NOT_FOUND \"Producto inexistente\"");

        then(productPresenter).should().prepareFailView("Producto inexistente");
    }

    static class TestData {

        final static int productId = 1;
        final static int brandId = 1;
        final static int fee = 1;
        final static int highPriority = 1;
        final static int lowPriority = 2;
        final static int price1 = 10;
        final static int price2 = 15;

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

        final static List<BaseProductSQLEntity> entitiesList =
                List.of(new BaseProductSQLEntity(1, productId, brandId, applicationDate.minusHours(1),
                                applicationDate.plusHours(1L), fee, highPriority, price1, Currency.EUR),
                        new BaseProductSQLEntity(2, productId, brandId, applicationDate.minusHours(1),
                                applicationDate.plusHours(1L), fee, lowPriority, price2, Currency.EUR));

        final static RequestModel requestModel = new RequestModel(productId, brandId, applicationDate);
    }

}
