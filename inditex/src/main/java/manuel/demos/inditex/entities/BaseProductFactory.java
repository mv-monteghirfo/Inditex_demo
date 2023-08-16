package manuel.demos.inditex.entities;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * To comply with stable abstractions principle and to isolate creation.
 */
@Component
public class BaseProductFactory implements ProductFactory {

    @Override
    public Product create(
            int productId,
            int brandId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            short priceList,
            short priority,
            float price,
            Currency currency) {

        // TODO fluent setters or other constructor
        // TODO group fields?

        return new BaseProduct(productId, brandId, startDate, endDate, priceList, priority, price, currency);
    }
}
