package manuel.demos.inditex.entities.jpa;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import manuel.demos.inditex.entities.Currency;

import java.time.LocalDateTime;

/**
 * This class objects are the specific model of JPA SQL entities
 */
@Getter
@Entity
@Table(name = "prices")
public class BaseProductDataMapper {

    /** Identificador código de producto. */
    @Id
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
    Currency currency;

}
