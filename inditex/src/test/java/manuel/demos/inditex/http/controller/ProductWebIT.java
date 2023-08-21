package manuel.demos.inditex.http.controller;

import manuel.demos.inditex.entities.BaseProductFactory;
import manuel.demos.inditex.entities.Currency;
import manuel.demos.inditex.interactors.UserRequestInteractor;
import manuel.demos.inditex.interactors.boundaries.BaseProductQuery;
import manuel.demos.inditex.interactors.boundaries.ProductQueryDsGateway;
import manuel.demos.inditex.interactors.boundaries.UserRequestBoundary;
import manuel.demos.inditex.interactors.models.RequestModel;
import manuel.demos.inditex.interactors.models.ResponseModel;
import manuel.demos.inditex.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static manuel.demos.inditex.http.controller.ProductWebIT.TestData.applicationDate;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Web slice integration tests class, which is, integration between Spring web layer beans and the controller
 *
 * NOTE: Ideally, Integration Tests should be placed in a sepparate Maven Module in order to split fast unit test executions
 * and slow IT executions in different maven runs.
 */
@WebMvcTest(controllers = ProductRestController.class)
class ProductWebIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRequestBoundary userRequest;

    @Test
    void whenValidInput_thenReturn200() throws Exception {

        BDDMockito.given(userRequest.queryForProduct(any(RequestModel.class)))
                .willReturn(new ResponseModel (0, 11, 1, 10, LocalDateTime.now(), LocalDateTime.now(), 10, Currency.EUR));

        mockMvc.perform(get("/product")
                        .contentType("application/json")
                        .queryParam("productId", 		"11")
                        .queryParam("brandId", 		"1")
                        .queryParam("applicationDate", applicationDate.toString()))
                .andDo(print())
                .andExpectAll(status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("id").value("0"),
                        jsonPath("productId").value("11"),
                        jsonPath("brandId").value("1"),
                        jsonPath("appliedFee").value("10.0"),
                        jsonPath("price").value("10.0"));
    }

    static class TestData {
        final static LocalDateTime applicationDate = LocalDateTime.of(
                2023,
                Month.AUGUST,
                16,
                16,
                13,
                23);
    }
}
