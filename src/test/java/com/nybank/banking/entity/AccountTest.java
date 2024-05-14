package com.nybank.banking.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AccountTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Account#Account()}
     *   <li>{@link Account#setAccountHolderName(String)}
     *   <li>{@link Account#setBalance(double)}
     *   <li>{@link Account#setId(Long)}
     *   <li>{@link Account#getAccountHolderName()}
     *   <li>{@link Account#getBalance()}
     *   <li>{@link Account#getId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Account actualAccount = new Account();
        actualAccount.setAccountHolderName("Dr Jane Doe");
        actualAccount.setBalance(10.0d);
        actualAccount.setId(1L);
        String actualAccountHolderName = actualAccount.getAccountHolderName();
        double actualBalance = actualAccount.getBalance();

        // Assert that nothing has changed
        assertEquals("Dr Jane Doe", actualAccountHolderName);
        assertEquals(10.0d, actualBalance);
        assertEquals(1L, actualAccount.getId().longValue());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Account#Account(Long, String, double)}
     *   <li>{@link Account#setAccountHolderName(String)}
     *   <li>{@link Account#setBalance(double)}
     *   <li>{@link Account#setId(Long)}
     *   <li>{@link Account#getAccountHolderName()}
     *   <li>{@link Account#getBalance()}
     *   <li>{@link Account#getId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        Account actualAccount = new Account(1L, "Dr Jane Doe", 10.0d);
        actualAccount.setAccountHolderName("Dr Jane Doe");
        actualAccount.setBalance(10.0d);
        actualAccount.setId(1L);
        String actualAccountHolderName = actualAccount.getAccountHolderName();
        double actualBalance = actualAccount.getBalance();

        // Assert that nothing has changed
        assertEquals("Dr Jane Doe", actualAccountHolderName);
        assertEquals(10.0d, actualBalance);
        assertEquals(1L, actualAccount.getId().longValue());
    }
}
