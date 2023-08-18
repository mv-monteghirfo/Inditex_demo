package manuel.demos.inditex.http.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import manuel.demos.inditex.interactors.boundaries.UserRequestBoundary;
import manuel.demos.inditex.interactors.models.RequestModel;
import manuel.demos.inditex.interactors.models.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static manuel.demos.inditex.http.controller.ProductRestController.Keywords.RequestParamsIdentifiers.*;


/**
 * Controller class responsible of communications, parsings and other REST messaging-related issues
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class ProductRestController {

    final UserRequestBoundary userRequest;

    @GetMapping(value = "/product", produces = "application/json")
    ResponseModel getItem(
            @RequestParam(PRODUCT_ID) int productId,
            @RequestParam(BRAND_ID) int brandId,
            @RequestParam(APPLICATION_DATE) String applicationDate) throws ProductNotFoundException, JsonProcessingException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(applicationDate, formatter);

        var response = userRequest.queryForProduct(new RequestModel(productId, brandId, dateTime));

        // TODO there´s a problem parsing the response to JSON format. This log will show the response for the time being
        log.info(response.toString());

        return response;
    }

    @GetMapping(value = "/ping")
    String doPing() {

        return "PING SUCCEEDED";

    }

    static class Keywords {

        static class RequestParamsIdentifiers {

            static final String PRODUCT_ID = "productId";
            static final String BRAND_ID = "brandId";
            static final String APPLICATION_DATE = "applicationDate";

        }

    }

}
