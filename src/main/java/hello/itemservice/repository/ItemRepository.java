package hello.itemservice.repository;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Item> findById(Long itemId) {
        return Optional.ofNullable(store.get(itemId));
    }

    public void update(Long itemId, Item itemParam) {
        Item findItem = store.get(itemId);
        findItem.setItemName(itemParam.getItemName());
        findItem.setPrice(itemParam.getPrice());
        findItem.setQuantity(itemParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
