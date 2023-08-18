package manuel.demos.inditex.entities;

import java.time.LocalDateTime;

/**
 * To stock to the stable abstractions principle and to isolate the user creation.
 */
public interface ProductFactory {

    Product create(
            int productId,
            int brandId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            int fee,
            int priority,
            float price,
            Currency currency);
}
