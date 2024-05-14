package com.nybank.banking.controller;

import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nybank.banking.dto.AccountDto;
import com.nybank.banking.service.AccountService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AccountController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AccountControllerTest {
    @Autowired
    private AccountController accountController;

    @MockBean
    private AccountService accountService;

    /**
     * Method under test: {@link AccountController#getAccountById(Long)}
     */
    @Test
    void testGetAccountById() throws Exception {
        // Arrange
        when(accountService.getAccountById(Mockito.<Long>any())).thenReturn(new AccountDto(1L, "Dr Jane Doe", 10.0d));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/accounts/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"accountHolderName\":\"Dr Jane Doe\",\"balance\":10.0}"));
    }

    /**
     * Method under test: {@link AccountController#deposit(Long, Map)}
     */
    @Test
    void testDeposit() throws Exception {
        // Arrange
        when(accountService.deposit(Mockito.<Long>any(), anyDouble())).thenReturn(new AccountDto(1L, "Dr Jane Doe", 10.0d));

        HashMap<String, Double> stringResultDoubleMap = new HashMap<>();
        stringResultDoubleMap.put("amount", 10.0d);
        String content = (new ObjectMapper()).writeValueAsString(stringResultDoubleMap);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/accounts/{id}/deposit", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"accountHolderName\":\"Dr Jane Doe\",\"balance\":10.0}"));
    }

    /**
     * Method under test: {@link AccountController#withdraw(Long, Map)}
     */
    @Test
    void testWithdraw() throws Exception {
        // Arrange
        when(accountService.withdraw(Mockito.<Long>any(), anyDouble()))
                .thenReturn(new AccountDto(1L, "Dr Jane Doe", 10.0d));

        HashMap<String, Double> stringResultDoubleMap = new HashMap<>();
        stringResultDoubleMap.put("amount", 10.0d);
        String content = (new ObjectMapper()).writeValueAsString(stringResultDoubleMap);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/accounts/{id}/withdraw", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"accountHolderName\":\"Dr Jane Doe\",\"balance\":10.0}"));
    }

    /**
     * Method under test: {@link AccountController#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() throws Exception {
        // Arrange
        when(accountService.getAllAccounts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/accounts/allAccounts");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AccountController#addAccount(AccountDto)}
     */
    @Test
    void testAddAccount() throws Exception {
        // Arrange
        when(accountService.createAccount(Mockito.<AccountDto>any())).thenReturn(new AccountDto(1L, "Dr Jane Doe", 10.0d));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/accounts/newAccount")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new AccountDto(1L, "Dr Jane Doe", 10.0d)));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"accountHolderName\":\"Dr Jane Doe\",\"balance\":10.0}"));
    }

    /**
     * Method under test: {@link AccountController#deleteAccount(Long)}
     */
    @Test
    void testDeleteAccount() throws Exception {
        // Arrange
        doNothing().when(accountService).deleteAccount(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/accounts/deleteAccount/{id}",
                1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Account is deleted successfully!"));
    }

    /**
     * Method under test: {@link AccountController#deleteAccount(Long)}
     */
    @Test
    void testDeleteAccount2() throws Exception {
        // Arrange
        doNothing().when(accountService).deleteAccount(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/accounts/deleteAccount/{id}",
                1L);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Account is deleted successfully!"));
    }
}
