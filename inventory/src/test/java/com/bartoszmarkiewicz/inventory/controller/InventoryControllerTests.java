package com.bartoszmarkiewicz.inventory.controller;


import com.bartoszmarkiewicz.inventory.dto.InventoryRecord;
import com.bartoszmarkiewicz.inventory.model.Inventory;
import com.bartoszmarkiewicz.inventory.service.InventoryService;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;




import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class InventoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private Inventory inventory;

    private InventoryRecord inventoryRecord;


    @BeforeEach
    public void init() {

        inventory = Inventory.builder()
                .productId(1)
                .productName("Apple")
                .productQuantity(30)
                .productPrice(20.0F)
                .createdAt(LocalDateTime.parse("2024-02-13T21:26:31.4342175"))
                .updatedAt(null)
                .build();

        inventoryRecord = new InventoryRecord(1, "Apple", 30, 20.0F, LocalDateTime.parse("2024-02-13T21:26:31.4342175"));

    }


    @Test
    public void InventoryController_AddProduct_ReturnCreatedProduct() throws Exception {

        //Given

        given(inventoryService.addProduct(inventoryRecord)).willReturn(inventory);

        // Perform

        ResultActions response = mockMvc.perform(post("/api/v1/inventories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventoryRecord)));


        // Expected

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId", CoreMatchers.is(inventory.getProductId())))
                .andExpect(jsonPath("$.productName", CoreMatchers.is(inventory.getProductName())))
                .andExpect(jsonPath("$.productQuantity", CoreMatchers.is(inventory.getProductQuantity())))
                .andExpect(jsonPath("$.productPrice", CoreMatchers.is(inventory.getProductPrice().doubleValue())))
                .andExpect(jsonPath("$.createdAt", CoreMatchers.startsWith("2024-02-13")));

        // Verify service call with expected argument
        Mockito.verify(inventoryService).addProduct(inventoryRecord);

    }

    @Test
    public void InventoryController_GetAllProducts_ReturnAllProducts() throws Exception {

        // Given (Mock service behavior with more realistic data)
        List<Inventory> expectedInventories = Arrays.asList(
                new Inventory(1, "Banana", 12, 13.00F, LocalDateTime.parse("2024-02-13T21:26:31.4342175"),null),
                new Inventory(2, "Apple", 10, 10.00F, LocalDateTime.parse("2024-02-13T21:26:31.4342175"),null)
        );
        when(inventoryService.getAllProducts()).thenReturn(expectedInventories);

        // Perform (Invoke endpoint with correct content type and JSON representation)
        ResultActions response = mockMvc.perform(get("/api/v1/inventories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Expected
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", CoreMatchers.is(expectedInventories.size()))); // Use dedicated JSONPath matcher
    }

    @Test
    public void InventoryController_GetProductById_ReturnProduct() throws Exception {

        int pokemonId = 1;

        //Given
        when(inventoryService.getProductById(pokemonId)).thenReturn(Optional.ofNullable(inventory));

        // Perform

        ResultActions response = mockMvc.perform(get("/api/v1/inventories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventoryRecord)));

        //Expected

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.productId", CoreMatchers.is(inventory.getProductId())))
                .andExpect(jsonPath("$.productName", CoreMatchers.is(inventory.getProductName())))
                .andExpect(jsonPath("$.productQuantity", CoreMatchers.is(inventory.getProductQuantity())))
                .andExpect(jsonPath("$.productPrice", CoreMatchers.is(inventory.getProductPrice().doubleValue())))
                .andExpect(jsonPath("$.createdAt", CoreMatchers.startsWith("2024-02-13")));

    }


    @Test
    public void InventoryController_UpdateProduct_ReturnUpdatedProduct() throws Exception {

        Inventory newProduct = Inventory.builder()
                .productId(1)
                .productName("Banana")
                .productQuantity(30)
                .productPrice(20.0F)
                .createdAt(LocalDateTime.parse("2024-02-13T21:26:31.4342175"))
                .updatedAt(LocalDateTime.parse("2024-02-15T21:26:31.4342175"))
                .build();

        //Given

        when(inventoryService.updateProduct(inventory)).thenReturn(newProduct);

        // Perform

        ResultActions response = mockMvc.perform(put("/api/v1/inventories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventoryRecord)));

        // Expected

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.productId", CoreMatchers.is(newProduct.getProductId())))
                .andExpect(jsonPath("$.productId", CoreMatchers.is(newProduct.getProductId())))
                .andExpect(jsonPath("$.productName", CoreMatchers.is(newProduct.getProductName())))
                .andExpect(jsonPath("$.productQuantity", CoreMatchers.is(newProduct.getProductQuantity())))
                .andExpect(jsonPath("$.productPrice", CoreMatchers.is(newProduct.getProductPrice().doubleValue())))
                .andExpect(jsonPath("$.createdAt", CoreMatchers.startsWith("2024-02-13")))
                .andExpect(jsonPath("$.updatedAt", CoreMatchers.startsWith("2024-02-15")));

    }

    @Test
    public void InventoryController_DeleteProduct() throws Exception {

        int productId = 1;

        // Given

        when(inventoryService.removeProduct(productId)).thenReturn(inventory);

        // Perform

        ResultActions response = mockMvc.perform(delete("/api/v1/inventories/1")
                .contentType(MediaType.APPLICATION_JSON));

        // Expected

        response.andExpect(MockMvcResultMatchers.status().isNoContent());

    }

}
