package manuel.demos.inditex.entities.jpa;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
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

    /** Identificador código de producto. */
    int productId;

    /** foreign key de la cadena del grupo (1 = ZARA) */
    int brandId;

    /** rango de fechas en el que aplica el precio tarifa indicado. */
    LocalDateTime startDate;

    /** rango de fechas en el que aplica el precio tarifa indicado.*/
    LocalDateTime endDate;

    /** Identificador de la tarifa de precios aplicable. */
    int fee;

    /** Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico). */
    int priority;

    /** precio final de venta. */
    float price;

    /** iso de la moneda.*/
    @Enumerated(EnumType.STRING)
    Currency currency;

    public void setId(Integer id) {
        this.id = id;
    }

}
