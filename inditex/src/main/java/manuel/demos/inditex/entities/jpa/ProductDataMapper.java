package manuel.demos.inditex.entities.jpa;

import manuel.demos.inditex.entities.BaseProduct;
import org.springframework.stereotype.Component;

/**
 * This class translates data into database entities for Product object
 */
@Component
public class ProductDataMapper {

    public BaseProductSQLEntity toBaseProductSQLEntity (BaseProduct product){

        return new BaseProductSQLEntity(
                null,
                product.getProductId(),
                product.getBrandId(),
                product.getStartDate(),
                product.getEndDate(),
                product.getFee(),
                product.getPriority(),
                product.getPrice(),
                product.getCurrency());
    }
}
