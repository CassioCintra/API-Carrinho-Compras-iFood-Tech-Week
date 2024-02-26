package me.dio.bagApi.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.bagApi.enumeration.PaymentMethod;
import me.dio.bagApi.model.Bag;
import me.dio.bagApi.model.Item;
import me.dio.bagApi.model.Restaurant;
import me.dio.bagApi.repository.BagRepository;
import me.dio.bagApi.repository.ItemRepository;
import me.dio.bagApi.repository.ProductRepository;
import me.dio.bagApi.resource.dto.ItemDto;
import me.dio.bagApi.service.BagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {
    private final BagRepository bagRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    @Override
    public Bag showBag(Long id) {
        return bagRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Bag not found")
        );
    }

    @Override
    public Bag closeBag(Long id, int numberPaymentMethod) {
        Bag bag = showBag(id);
        if(bag.getItems().isEmpty()) {
            throw new RuntimeException("Include items in the bag");
        }
        PaymentMethod paymentMethod =
                numberPaymentMethod == 0 ? PaymentMethod.MONEY : PaymentMethod.CREDIT_CARD;

        bag.setPaymentMethod(paymentMethod);
        bag.setClosed(true);
        return bagRepository.save(bag);
    }

    @Override
    public Item includeItem(ItemDto itemDto) {
        Bag bag = showBag(itemDto.getBagId());

        if(bag.isClosed()) {
            throw new RuntimeException("The bag is already closed");
        }

        Item addItem = Item.builder()
                .quantity(itemDto.getQuantity())
                .bag(bag)
                .product(productRepository.findById(itemDto.getProductId()).orElseThrow(
                () -> new RuntimeException("Product not found")))
                .build();

        List<Item> bagItems = bag.getItems();
        if(bagItems.isEmpty()){
            bagItems.add(addItem);
        } else{
            Restaurant restaurant = bagItems.get(0).getProduct().getRestaurant();
            Restaurant otherRestaurant = addItem.getProduct().getRestaurant();
            if(restaurant.equals(otherRestaurant)){
                bagItems.add(addItem);
            }
            else{
                throw new RuntimeException("The items are not from the same restaurant");
            }
        }
        List<Double> itemsValue = new ArrayList<>();
        for(Item bagItem : bagItems){
            double allValue = bagItem.getQuantity() * bagItem.getProduct().getUnitPrice();
            itemsValue.add(allValue);
        }

        double bagValue = itemsValue.stream()
                .mapToDouble(itemAllValue -> itemAllValue)
                .sum();

        bag.setAmount(bagValue);
        bagRepository.save(bag);
        return addItem;
    }
}