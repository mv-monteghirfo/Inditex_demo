package manuel.demos.inditex.http.controller;

import lombok.RequiredArgsConstructor;
import manuel.demos.inditex.http.dto.ItemResponseDto;
import manuel.demos.inditex.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemRestController {

    private ItemService itemService;

    @GetMapping("/item")
    ItemResponseDto getItem(
            @RequestParam("applicationDate") String applicationDate,
            @RequestParam("productID") String productId,
            @RequestParam("chainId") String chainId){

        return itemService.getItem(applicationDate, productId, chainId);

    }



}
