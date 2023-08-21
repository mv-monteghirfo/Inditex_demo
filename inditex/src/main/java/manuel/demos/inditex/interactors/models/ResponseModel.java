package manuel.demos.inditex.interactors.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import manuel.demos.inditex.entities.Currency;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseModel {

    int id;

    int productId;

    int brandId;

    float appliedFee;

    LocalDateTime startDate;

    LocalDateTime endDate;

    float price;

    Currency currency;

}
