package manuel.demos.inditex.entities.jpa;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import manuel.demos.inditex.entities.Currency;

import java.time.LocalDateTime;

/**
 * This class objects are the specific model of JPA SQL entities
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "prices")
public class BaseProductSQLEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Product identifier
     */
    int productId;

    /**
     * Brand´s foreign key (1 = ZARA)
     */
    int brandId;

    /**
     * Date range in which the given price applies
     */
    LocalDateTime startDate;

    /**
     * Date range in which the given price applies
     */
    LocalDateTime endDate;

    /**
     * Idetifier of fee to be applied to the price
     */
    int fee;

    /**
     * Prices application disambiguator. If two fees match in a date range, the highest in priority applies (highest numeric value)
     */
    int priority;

    /**
     * final price
     */
    float price;

    /**
     * currency´s ISO
     */
    @Enumerated(EnumType.STRING)
    Currency currency;

}
