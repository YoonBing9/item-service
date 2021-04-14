package hello.itemservice.repository;

import hello.itemservice.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("item", 10000, 10);

        //when
        Item saveItem = itemRepository.save(item);

        //then
        assertThat(item).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(2).isEqualTo(items.size());
        assertThat(items).contains(item1, item2);
    }

    @Test
    void update() {
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item saveItem = itemRepository.save(item1);

        //when
        itemRepository.update(saveItem.getId(), new Item("item2", 20000, 20));

        //then
        Optional<Item> otFindItem = itemRepository.findById(saveItem.getId());
        Item findItem = otFindItem.orElseThrow(() -> new IllegalStateException("No find Item \nitemId = "+saveItem.getId()));
        assertThat(item1.getItemName()).isEqualTo(findItem.getItemName());
        assertThat(item1.getPrice()).isEqualTo(findItem.getPrice());
        assertThat(item1.getQuantity()).isEqualTo(findItem.getQuantity());
    }
}