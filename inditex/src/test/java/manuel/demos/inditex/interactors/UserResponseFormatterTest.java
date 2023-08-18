package manuel.demos.inditex.interactors;

import manuel.demos.inditex.entities.Currency;
import manuel.demos.inditex.interactors.models.ResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

import static manuel.demos.inditex.interactors.UserRequestInteractorTest.TestData.startDate;
import static manuel.demos.inditex.interactors.UserResponseFormatterTest.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserResponseFormatterTest {

    @Autowired
    UserResponseFormatter userResponseFormatter;

    @Test
    @DisplayName("Success View works")
    void checkSuccessViewWorks() {
        ResponseModel responseModel = new ResponseModel(0, productId, brandId, appliedFee, startDate, endDate, price * appliedFee, Currency.EUR);
        ResponseModel formattedResponse = userResponseFormatter.prepareSuccessView(responseModel);

        assertThat(formattedResponse).isEqualTo(responseModel);
    }

    static class TestData {

        static final int productId = 1;
        static final int brandId = 1;
        static final float appliedFee = 1.5F;
        LocalDateTime startDate = LocalDateTime.of(
                2023,
                Month.AUGUST,
                10,
                16,
                13,
                23);
        final static LocalDateTime endDate = LocalDateTime.of(
                2023,
                Month.AUGUST,
                20,
                16,
                13,
                23);
        final static float price = 10;
    }

}
