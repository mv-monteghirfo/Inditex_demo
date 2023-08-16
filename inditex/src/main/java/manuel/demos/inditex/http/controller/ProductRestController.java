package manuel.demos.inditex.http.controller;

import lombok.RequiredArgsConstructor;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import manuel.demos.inditex.interactors.boundaries.UserRequestBoundary;
import manuel.demos.inditex.interactors.models.RequestModel;
import manuel.demos.inditex.interactors.models.ResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@RestController
//@Controller
public class ProductRestController {

    final UserRequestBoundary userRequest;

    @GetMapping(value = "/product", consumes = "application/json")
    ResponseModel getItem(
            @RequestParam("productID") int productId,
            @RequestParam("brandId") int brandId,
            @RequestParam("applicationDate") String applicationDate) throws ProductNotFoundException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(applicationDate, formatter);

        return userRequest.queryForProduct(new RequestModel(productId, brandId, dateTime));

    }

}
