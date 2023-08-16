package manuel.demos.inditex.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/** This test is a long-term consistency protection. If any further change results in an entity modification the test
 * will fail as a double check confirmation
 */
class EntityConsistencyTest {

    @Test
    @DisplayName("baseItemEntity is kept")
    void baseItemEntityIsKept (){

        //TODO This should be done over the entity, not the logical object. double check this

        assertThat(BaseProduct.class).hasDeclaredFields("productId","brandId","startDate", "endDate", "fee", "priority", "price", "currency");

    }

}
