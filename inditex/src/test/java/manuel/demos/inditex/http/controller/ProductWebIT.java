package manuel.demos.inditex.http.controller;

import manuel.demos.inditex.entities.BaseProductFactory;
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
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import static manuel.demos.inditex.http.controller.ProductWebIT.TestData.applicationDate;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Web slice integration tests class, which is, integration between Spring web layer beans and the controller
 */
@WebMvcTest(controllers = ProductRestController.class)
//@Import({UserRequestInteractor.class, BaseProductFactory.class, BaseProductQuery.class, ProductRepository.class})
class ProductWebIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRequestBoundary userRequest;

    @Test
    void whenValidInput_thenReturn200() throws Exception {

        BDDMockito.given(userRequest.queryForProduct(any(RequestModel.class)))
                .willReturn(new ResponseModel (11, 1, 10, LocalDateTime.now(), LocalDateTime.now(), 10));

        mockMvc.perform(get("/product")
                        .contentType("application/json")
                        .queryParam("productID", 		"11")
                        .queryParam("brandId", 		"1")
                        .queryParam("applicationDate", applicationDate.toString()))
                .andDo(print())
                // TODO add here all the fields that are expected to be found in the response
                .andExpect(status().isOk());
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
