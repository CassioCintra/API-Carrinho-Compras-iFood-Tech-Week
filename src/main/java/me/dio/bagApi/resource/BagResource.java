package me.dio.bagApi.resource;

import lombok.RequiredArgsConstructor;
import me.dio.bagApi.model.Bag;
import me.dio.bagApi.model.Item;
import me.dio.bagApi.resource.dto.ItemDto;
import me.dio.bagApi.service.BagService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/bag")
@RequiredArgsConstructor
public class BagResource {
    private final BagService bagService;

    @PostMapping
    public Item includeItem(@RequestBody ItemDto itemDto) {
        return bagService.includeItem(itemDto);
    }
    @GetMapping("/{id}")
    public Bag showBag(@PathVariable("id") Long id){
        return bagService.showBag(id);
    }
    @PatchMapping("/close-bag/{bagId}")
    public Bag closeBag(@PathVariable("bagId") Long bagId,
                        @RequestParam("paymentMethod") int paymentMethod){
        return bagService.closeBag(bagId, paymentMethod);
    }
}
