package manuel.demos.inditex.utility;

import org.springframework.stereotype.Component;

@Component
public class FinalPriceCalculator {

    public float calculatePrice(float price, int fee) {
        return switch (fee) {
            case 1 -> price * 1.1F;
            case 2 -> price * 1.2F;
            case 3 -> price * 1.3F;
            case 4 -> price * 1.4F;
            default -> price;
        };
    }
}
