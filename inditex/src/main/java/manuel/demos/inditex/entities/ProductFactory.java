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
            short priceList,
            short priority,
            float price,
            Currency currency);
}
