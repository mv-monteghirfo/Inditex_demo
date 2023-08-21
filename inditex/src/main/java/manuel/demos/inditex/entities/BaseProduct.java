package manuel.demos.inditex.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BaseProduct implements Product {

    private int productId;

    private int brandId; // UUID?

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int fee;

    private int priority;

    private float price;

    private Currency currency;

}
