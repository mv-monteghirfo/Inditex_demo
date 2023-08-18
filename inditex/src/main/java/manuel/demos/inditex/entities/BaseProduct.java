package manuel.demos.inditex.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BaseProduct implements Product {

     /** Identificador código de producto. */
     private int productId;

     /** foreign key de la cadena del grupo (1 = ZARA) */
     private int brandId; // UUID?

     /** rango de fechas en el que aplica el precio tarifa indicado. */
     private LocalDateTime startDate;

     /** rango de fechas en el que aplica el precio tarifa indicado.*/
     private LocalDateTime endDate;

     /** Identificador de la tarifa de precios aplicable. */
     private int fee;

     /** Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico). */
     private int priority;

     /** precio final de venta. */
     private float price;

     /** iso de la moneda.*/
     private Currency currency;

}
