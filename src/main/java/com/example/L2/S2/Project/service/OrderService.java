package com.example.L2.S2.Project.service;

import com.example.L2.S2.Project.dao.request.OrderItemRequest;
import com.example.L2.S2.Project.dao.request.OrderRequest;
import com.example.L2.S2.Project.model.*;
import com.example.L2.S2.Project.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    public final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final ProductRepository productRepository;

    // public OrderService(OrderRepository orderRepository, CustomerRepository
    // customerRepository, DriverRepository driverRepository, ProductRepository
    // productRepository) {
    // this.orderRepository = orderRepository;
    // this.customerRepository = customerRepository;
    // this.driverRepository = driverRepository;
    // this.productRepository = productRepository;
    // }

    @Transactional
    public void createOrder(OrderRequest orderRequest) {
        // Retrieve customer
        // Customer customer =
        // customerRepository.findCustomerByEmail(orderRequest.getUserEmail())
        // .orElseThrow(() -> new RuntimeException("Customer not found with Email: " +
        // orderRequest.getUserEmail()));
        User user = userRepository.findByEmail(orderRequest.getUserEmail())
                .orElseThrow(() -> new RuntimeException("User not found with Email: " + orderRequest.getUserEmail()));
        // create order

        /*
         * Order order= new Order();
         * order.setCustomer(customer);
         * order.setReturnDate(orderRequest.getReturnDate());
         */

        Order order = new Order();
        order.setUser(user);
        order.setReturnDate(orderRequest.getReturnDate());

        // Assign Deliver

        Driver driver = driverRepository.findById(orderRequest.getDriverId())
                .orElseThrow(() -> new RuntimeException("Delivery person not found"));

        order.setDriver(driver);

        DeliverDetails deliverDetails = new DeliverDetails();
        deliverDetails.setDriver(driver);

        // process order items
        for (OrderItemRequest itemRequest : orderRequest.getOrderItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(
                            () -> new RuntimeException("Product not found with ID: " + itemRequest.getProductId()));

            // Check if there is enough quantity available
            if (product.getQuantity() >= itemRequest.getQuantity()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setQuantity(itemRequest.getQuantity());
                order.addOrderItem(orderItem);

                // update product quantity
                product.setQuantity(product.getQuantity() - itemRequest.getQuantity());
                productRepository.save(product);
            } else {
                throw new RuntimeException(
                        "Not enough quantity available for product with ID: " + itemRequest.getProductId());
            }
        }
        order.setCreateAt(LocalDateTime.now());
        deliverDetails.setOrder(order);
        driver.addOrder(deliverDetails);
        // save the order
        orderRepository.save(order);
    }

    public List<Order> allOrders() {
        return orderRepository.findAll();
    }
}
