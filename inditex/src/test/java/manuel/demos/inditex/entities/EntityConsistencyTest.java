package manuel.demos.inditex.entities;

import manuel.demos.inditex.entities.jpa.BaseProductSQLEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static manuel.demos.inditex.entities.EntityConsistencyTest.TestData.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/** This test is a long-term consistency protection. If any further change results in an entity modification the test
 * will fail as a double check confirmation
 */
class EntityConsistencyTest {

    @Test
    @DisplayName("baseItemEntity is kept")
    void baseItemEntityIsKept (){

        assertThat(BaseProduct.class).hasDeclaredFields(PRODUCT_ID, BRAND_ID, START_DATE, END_DATE, FEE, PRIORITY,
                PRICE, CURRENCY);
        /*
        These classes don´t have to necessary match although, in this case, both are matching representations of the same
        thing
         */
        assertThat(BaseProductSQLEntity.class).hasDeclaredFields(PRODUCT_ID, BRAND_ID, START_DATE, END_DATE, FEE, PRIORITY,
                PRICE, CURRENCY);

    }

    static class TestData {

        static String PRODUCT_ID = "productId";
        static String BRAND_ID = "brandId";
        static String START_DATE = "startDate";
        static String END_DATE = "endDate";
        static String FEE = "fee";
        static String PRIORITY = "priority";
        static String PRICE = "price";
        static String CURRENCY = "currency";

    }

}
