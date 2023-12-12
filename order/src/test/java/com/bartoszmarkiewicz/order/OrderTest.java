package com.bartoszmarkiewicz.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void testCanEqual() {
        assertFalse((new Order()).canEqual("Other"));
    }


    @Test
    void testCanEqual2() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertTrue(order.canEqual(order2));
    }


    @Test
    void testConstructor() {
        assertEquals(OrderStatus.PROGRESS, (new Order()).getOrderStatus());
    }

    @Test
    void testConstructor2() {
        Order actualOrder = new Order(1L, 1L, "Product Name", 1, 10.0d, LocalDate.of(1970, 1, 1).atStartOfDay(),
                "42 Main St", "6625550144", OrderStatus.PROGRESS);

        assertEquals("00:00", actualOrder.getOrderCreatedAt().toLocalTime().toString());
        assertEquals("42 Main St", actualOrder.getShippingAddress());
        assertEquals("6625550144", actualOrder.getPhoneNumber());
        assertEquals("Product Name", actualOrder.getProductName());
        assertEquals(1, actualOrder.getOrderAmount().intValue());
        assertEquals(10.0d, actualOrder.getOrderValue().doubleValue());
        assertEquals(1L, actualOrder.getCustomerId().longValue());
        assertEquals(1L, actualOrder.getOrderId().longValue());
        assertEquals(OrderStatus.PROGRESS, actualOrder.getOrderStatus());
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor3() {
        new Order(null, null, null, null, null, null, null, null, OrderStatus.PROGRESS);

    }

    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor4() {
        new Order(1L, null, "Product Name", 1, 10.0d, LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", "6625550144",
                OrderStatus.PROGRESS);

    }

    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor5() {


        new Order(1L, 1L, null, 1, 10.0d, LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", "6625550144",
                OrderStatus.PROGRESS);

    }


    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor6() {
        new Order(1L, 1L, "Product Name", null, 10.0d, LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", "6625550144",
                OrderStatus.PROGRESS);

    }


    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: orderValue is marked non-null but is null
        //       at com.bartoszmarkiewicz.order.Order.<init>(Order.java:11)
        //   See https://diff.blue/R013 to resolve this issue.

        new Order(1L, 1L, "Product Name", 1, null, LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", "6625550144",
                OrderStatus.PROGRESS);

    }


    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: shippingAddress is marked non-null but is null
        //       at com.bartoszmarkiewicz.order.Order.<init>(Order.java:11)
        //   See https://diff.blue/R013 to resolve this issue.

        new Order(1L, 1L, "Product Name", 1, 10.0d, LocalDate.of(1970, 1, 1).atStartOfDay(), null, "6625550144",
                OrderStatus.PROGRESS);

    }


    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor9() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: phoneNumber is marked non-null but is null
        //       at com.bartoszmarkiewicz.order.Order.<init>(Order.java:11)
        //   See https://diff.blue/R013 to resolve this issue.

        new Order(1L, 1L, "Product Name", 1, 10.0d, LocalDate.of(1970, 1, 1).atStartOfDay(), "42 Main St", null,
                OrderStatus.PROGRESS);

    }


    @Test
    void testConstructor10() {
        Order actualOrder = new Order(1L, 1L, "Product Name", 1, 10.0d, LocalDate.of(1970, 1, 1).atStartOfDay(),
                "42 Main St", "6625550144", OrderStatus.FINISHED);

        assertEquals("00:00", actualOrder.getOrderCreatedAt().toLocalTime().toString());
        assertEquals("42 Main St", actualOrder.getShippingAddress());
        assertEquals("6625550144", actualOrder.getPhoneNumber());
        assertEquals("Product Name", actualOrder.getProductName());
        assertEquals(1, actualOrder.getOrderAmount().intValue());
        assertEquals(10.0d, actualOrder.getOrderValue().doubleValue());
        assertEquals(1L, actualOrder.getCustomerId().longValue());
        assertEquals(1L, actualOrder.getOrderId().longValue());
        assertEquals(OrderStatus.FINISHED, actualOrder.getOrderStatus());
    }


    @Test
    void testEquals() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        assertNotEquals(order, null);
    }


    @Test
    void testEquals2() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        assertNotEquals(order, "Different type to Order");
    }


    @Test
    void testEquals3() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        assertEquals(order, order);
        int expectedHashCodeResult = order.hashCode();
        assertEquals(expectedHashCodeResult, order.hashCode());
    }


    @Test
    void testEquals4() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertEquals(order, order2);
        int expectedHashCodeResult = order.hashCode();
        assertEquals(expectedHashCodeResult, order2.hashCode());
    }


    @Test
    void testEquals5() {
        Order order = new Order();
        order.setCustomerId(2L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertNotEquals(order, order2);
    }

    @Test
    void testEquals6() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(3);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertNotEquals(order, order2);
    }


    @Test
    void testEquals7() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.now().atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertNotEquals(order, order2);
    }

    @Test
    void testEquals8() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(2L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertNotEquals(order, order2);
    }

    @Test
    void testEquals9() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(null);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertNotEquals(order, order2);
    }

    @Test
    void testEquals10() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.FINISHED);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertNotEquals(order, order2);
    }

    @Test
    void testEquals11() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(0.5d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertNotEquals(order, order2);
    }

    @Test
    void testEquals12() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("8605550118");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertNotEquals(order, order2);
    }

    @Test
    void testEquals13() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("42 Main St");
        order.setShippingAddress("42 Main St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertNotEquals(order, order2);
    }

    @Test
    void testEquals14() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("17 High St");

        Order order2 = new Order();
        order2.setCustomerId(1L);
        order2.setOrderAmount(1);
        order2.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order2.setOrderId(1L);
        order2.setOrderStatus(OrderStatus.PROGRESS);
        order2.setOrderValue(10.0d);
        order2.setPhoneNumber("6625550144");
        order2.setProductName("Product Name");
        order2.setShippingAddress("42 Main St");
        assertNotEquals(order, order2);
    }


    @Test
    void testSetCustomerId() {
        Order order = new Order();
        order.setCustomerId(1L);
        assertEquals(1L, order.getCustomerId().longValue());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testSetCustomerId2() {

        (new Order()).setCustomerId(null);
    }

    @Test
    void testSetOrderAmount() {
        Order order = new Order();
        order.setOrderAmount(1);
        assertEquals(1, order.getOrderAmount().intValue());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testSetOrderAmount2() {


        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        order.setOrderAmount(null);
    }


    @Test
    void testSetOrderCreatedAt() {
        Order order = new Order();
        LocalDateTime orderCreatedAt = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setOrderCreatedAt(orderCreatedAt);
        assertSame(orderCreatedAt, order.getOrderCreatedAt());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testSetOrderCreatedAt2() {

        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        order.setOrderCreatedAt(null);
    }


    @Test
    void testSetOrderId() {
        Order order = new Order();
        order.setOrderId(1L);
        assertEquals(1L, order.getOrderId().longValue());
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testSetOrderId2() {

        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        order.setOrderId(null);
    }

    @Test
    void testSetOrderStatus() {
        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        order.setOrderStatus(OrderStatus.PROGRESS);
        String actualToStringResult = order.toString();
        Long actualCustomerId = order.getCustomerId();
        Integer actualOrderAmount = order.getOrderAmount();
        order.getOrderCreatedAt();
        Long actualOrderId = order.getOrderId();
        OrderStatus actualOrderStatus = order.getOrderStatus();
        Double actualOrderValue = order.getOrderValue();
        String actualPhoneNumber = order.getPhoneNumber();
        String actualProductName = order.getProductName();
        assertEquals("42 Main St", order.getShippingAddress());
        assertEquals("6625550144", actualPhoneNumber);
        assertEquals(
                "Order(orderId=1, customerId=1, productName=Product Name, orderAmount=1, orderValue=10.0, orderCreatedAt"
                        + "=1970-01-01T00:00, shippingAddress=42 Main St, phoneNumber=6625550144, orderStatus=PROGRESS)",
                actualToStringResult);
        assertEquals("Product Name", actualProductName);
        assertEquals(1, actualOrderAmount.intValue());
        assertEquals(10.0d, actualOrderValue.doubleValue());
        assertEquals(1L, actualCustomerId.longValue());
        assertEquals(1L, actualOrderId.longValue());
        assertEquals(OrderStatus.PROGRESS, actualOrderStatus);
    }

    @Test
    void testSetOrderValue() {
        Order order = new Order();
        order.setOrderValue(10.0d);
        assertEquals(10.0d, order.getOrderValue().doubleValue());
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testSetOrderValue2() {

        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        order.setOrderValue(null);
    }


    @Test
    void testSetPhoneNumber() {
        Order order = new Order();
        order.setPhoneNumber("6625550144");
        assertEquals("6625550144", order.getPhoneNumber());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testSetPhoneNumber2() {

        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        order.setPhoneNumber(null);
    }

    @Test
    void testSetProductName() {
        Order order = new Order();
        order.setProductName("Product Name");
        assertEquals("Product Name", order.getProductName());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testSetProductName2() {

        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        order.setProductName(null);
    }

    @Test
    void testSetShippingAddress() {
        Order order = new Order();
        order.setShippingAddress("42 Main St");
        assertEquals("42 Main St", order.getShippingAddress());
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testSetShippingAddress2() {

        Order order = new Order();
        order.setCustomerId(1L);
        order.setOrderAmount(1);
        order.setOrderCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PROGRESS);
        order.setOrderValue(10.0d);
        order.setPhoneNumber("6625550144");
        order.setProductName("Product Name");
        order.setShippingAddress("42 Main St");
        order.setShippingAddress(null);
    }
}
