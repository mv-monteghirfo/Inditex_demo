package manuel.demos.inditex.repositories;

import manuel.demos.inditex.entities.jpa.BaseProductSQLEntity;
import manuel.demos.inditex.exceptions.ProductNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<BaseProductSQLEntity, Integer> {
    boolean existsByProductId(int productId);

    List<BaseProductSQLEntity> findByProductIdAndStartDateBeforeAndEndDateAfter(int productId, LocalDateTime startDate, LocalDateTime endDate);

}
