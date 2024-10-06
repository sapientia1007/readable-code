package cleancode.assignment;
import java.util.logging.Logger;

public class Validate {

    private static final Logger log = Logger.getGlobal();

    public boolean validateOrder(Order order) {
        if (hasEmptyItems(order)) {
            log.info("주문 항목이 없습니다.");
            return false;
        }

        else {
            return isTotalPriceOverZero(order) && hasCustomersInfo(order);
        }

    }

    public boolean hasEmptyItems(Order order) {
        return order.isEmptyItems();
    }

    public boolean isTotalPriceOverZero(Order order){
        if(order.isOverZeroPrice()) {
            return true;
        }
        else {
            log.info("올바르지 않은 총 가격입니다.");
            return false;
        }
    }

    public boolean hasCustomersInfo(Order order) {
        if (order.hasCustomerInfo()){
            return true;
        } else {
            log.info("사용자 정보가 없습니다.");
            return false;
        }
    }
}