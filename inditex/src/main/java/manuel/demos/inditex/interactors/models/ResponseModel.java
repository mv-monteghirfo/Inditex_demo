package manuel.demos.inditex.interactors.models;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ResponseModel {

    int productId;

    int brandId;

    float appliedFee;

    LocalDateTime startDate;

    LocalDateTime endDate;

    float price;

}
