package manuel.demos.inditex.entities;

import manuel.demos.inditex.utility.FinalPriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * To comply with stable abstractions principle and to isolate creation.
 */
@Component
public class BaseProductFactory implements ProductFactory {

    /** Creation of products with policies, constraints and so on */
    @Override
    public BaseProduct create(
            int productId,
            int brandId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            int fee,
            int priority,
            float price,
            Currency currency) {

        // TODO fluent setters or other constructor
        // TODO group fields in auxiliary tables, like dates in date range

        return new BaseProduct(productId, brandId, startDate, endDate, fee, priority, price, currency);
    }
}
