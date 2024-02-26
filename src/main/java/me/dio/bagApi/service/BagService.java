package me.dio.bagApi.service;

import me.dio.bagApi.model.Bag;
import me.dio.bagApi.model.Item;
import me.dio.bagApi.resource.dto.ItemDto;

public interface BagService {
    Bag showBag(Long id);
    Bag closeBag(Long id, int paymentMethod);
    Item includeItem(ItemDto itemDto);
}
