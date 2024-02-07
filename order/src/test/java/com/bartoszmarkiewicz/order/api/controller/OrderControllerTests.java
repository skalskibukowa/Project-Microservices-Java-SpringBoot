package com.bartoszmarkiewicz.order.api.controller;

import com.bartoszmarkiewicz.order.model.Order;
import com.bartoszmarkiewicz.order.dto.OrderRegistrationRequest;
import com.bartoszmarkiewicz.order.service.OrderService;
import com.bartoszmarkiewicz.order.model.OrderStatus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class OrderControllerTests {

   @Autowired
   private MockMvc mockMvc;
   @MockBean
   private OrderService orderService;

   @Autowired
   private ObjectMapper objectMapper;
   private Order order;
   private OrderRegistrationRequest orderRegistrationRequest;

   @BeforeEach
   public void init() {
       order = Order
               .builder()
               .customerId(1)
               .productId(1)
               .productName("Orange")
               .orderAmount(20)
               .orderValue(200.00)
               .phoneNumber("555-222-444")
               .shippingAddress("London")
               .orderStatus(OrderStatus.PROGRESS)
               .orderCreatedAt(LocalDateTime.now())
               .build();

       orderRegistrationRequest = OrderRegistrationRequest
               .builder()
               .customerId(1)
               .productId(1)
               .productName("Orange")
               .orderAmount(20)
               .orderValue(200.00)
               .phoneNumber("555-222-444")
               .shippingAddress("London")
               .orderStatus(OrderStatus.PROGRESS)
               .orderCreatedAt(LocalDateTime.now())
               .build();
   }

   @Test
   public void OrderController_CreateOrder_ReturnCreated_1() throws Exception {

       // Given
       given(orderService.registerOrder(any(OrderRegistrationRequest.class)))
               .willAnswer((invocation -> invocation.getArgument(0)));


       //Perform
       ResultActions response = mockMvc.perform(post("/api/v1/orders")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(orderRegistrationRequest)));


       // Expected

       response.andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpect(MockMvcResultMatchers.jsonPath("$.customerId", CoreMatchers.is(order.getCustomerId())))
               .andExpect(MockMvcResultMatchers.jsonPath("$.productId", CoreMatchers.is(order.getProductId())))
               .andExpect(MockMvcResultMatchers.jsonPath("$.productName", CoreMatchers.is(order.getProductName())))
               .andExpect(MockMvcResultMatchers.jsonPath("$.orderAmount", CoreMatchers.is(order.getOrderAmount())))
               .andExpect(MockMvcResultMatchers.jsonPath("$.orderValue", CoreMatchers.is(order.getOrderValue())))
               .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber", CoreMatchers.is(order.getPhoneNumber())))
               .andExpect(MockMvcResultMatchers.jsonPath("$.shippingAddress", CoreMatchers.is(order.getShippingAddress())))
               .andExpect(MockMvcResultMatchers.jsonPath("$.orderStatus", CoreMatchers.is(order.getOrderStatus())))
               .andExpect(MockMvcResultMatchers.jsonPath("$.orderCreatedAt", CoreMatchers.is(order.getOrderCreatedAt())));


   }

    @Test
    void OrderController_CreateOrder_ReturnCreated() throws Exception {
        // Arrange
        Order order = new Order();
        order.setOrderId(1);
        order.setCustomerId(1);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(2022, 1, 1).atStartOfDay());
        order.setOrderId(1);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductId(1);
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        when(orderService.registerOrder(Mockito.<OrderRegistrationRequest>any())).thenReturn(order);

        /*
        // Act
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRegistrationRequest)))
                .andReturn();
        ResponseEntity<Order> actualRegisterOrderResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                new TypeReference<ResponseEntity<Order>>() {});

        // Assert
        verify(orderService).registerOrder(Mockito.<OrderRegistrationRequest>any());
        assertTrue(actualRegisterOrderResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualRegisterOrderResult.getStatusCode());
  */
        // when(orderService.registerOrder(any(OrderRegistrationRequest.class))).thenReturn(expectedOrder);



        // Act
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderRegistrationRequest)))
                .andReturn();
        Order actualOrder = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Order.class);

        // Assert
        verify(orderService).registerOrder(argThat(request -> request.customerId() == order.getCustomerId())); // Example of more specific argument matching
        assertEquals(order, actualOrder);
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus()); //HttpStatus.CREATED

    }

}
