package manuel.demos.inditex.http.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Web slice integration tests class, which is, integration between Spring web layer beans and the controller
 */
@WebMvcTest(controllers = ItemRestController.class)
class ItemWebIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenValidInput_thenReturn200() throws Exception {

        mockMvc.perform(get("/item", 42L)
                        .contentType("application/json")
                        .queryParam("applicationDate", LocalDateTime.now().toString())
                        .queryParam("productID", 		"11")
                        .queryParam("chainId", 		"1"))
                .andDo(print())
                // TODO add here all the fields that are expected to be found in the response
                .andExpect(status().isOk());
    }
}
