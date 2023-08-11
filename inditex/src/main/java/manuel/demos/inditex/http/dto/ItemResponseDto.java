package manuel.demos.inditex.http.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Interface for generic items
 */
@Data
public class ItemResponseDto {

    String productId;

    String brandId;

    LocalDateTime startDate;

    String endDate;

    String curr;

    String price;


}
