package manuel.demos.inditex.repositories;

import manuel.demos.inditex.entities.jpa.BaseProductDataMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<BaseProductDataMapper, Integer> {
}
