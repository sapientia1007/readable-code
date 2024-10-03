package cleancode.assignment;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public List<String> items = new ArrayList<>();
    public int totalPrice;
    public String customer;

    public List<String> getItems() {
        return items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public boolean hasCustomerInfo() {
        return !customer.isEmpty();
    }

    public boolean isEmptyItems() {
        return items.size() == 0;
    }

    public boolean isOverZeroPrice() {
        return totalPrice > 0;
    }

}


