package com.substring.agent.backend.tools;

import java.util.Map;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class OrderTools {

    private final Map<String, String> orders = Map.of(
            "1042", "Shipped - arriving tomorrow",
            "1043", "Processing - not yet shipped",
            "1044", "Cancelled - out of stock",
            "1045", "Shipped - arriving next week"
    );

    @Tool(description = "Get the status of a customer order by its order ID")
    public String getOrderStatus(String orderId) {
        System.out.println("calling getOrderStatus Tool");
        System.out.println("Fetching status for order ID: " + orderId);
        return orders.getOrDefault(orderId, "Order not found");
    }

    @Tool(description = "Cancel a customer order by its order ID")
    public String cancelOrder(String orderId) {
        System.out.println("calling getOrderStatus Tool");
        System.out.println("Fetching status for order ID: " + orderId);
        if (orders.containsKey(orderId)) {
            return "Order " + orderId + " has been cancelled.";
        }
        return "Order not found";
    }
@Tool(description = "Get the total count of customer orders ")
public Integer getOrderQuantity() {
    System.out.println("calling getOrderQuantity Tool");
    return orders.size(); // Example logic, assuming each order has a quantity of 1
}

}