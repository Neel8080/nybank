package com.nybank.banking.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AccountDtoTest {
    /**
     * Method under test: {@link AccountDto#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new AccountDto(1L, "Dr Jane Doe", 10.0d)).canEqual("Other"));
    }

    /**
     * Method under test: {@link AccountDto#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        AccountDto accountDto = new AccountDto(1L, "Dr Jane Doe", 10.0d);

        // Act and Assert
        assertTrue(accountDto.canEqual(new AccountDto(1L, "Dr Jane Doe", 10.0d)));
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AccountDto#equals(Object)}
     *   <li>{@link AccountDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
        // Arrange
        AccountDto accountDto = new AccountDto(1L, "Dr Jane Doe", 10.0d);
        AccountDto accountDto2 = new AccountDto(1L, "Dr Jane Doe", 10.0d);

        // Act and Assert
        assertEquals(accountDto, accountDto2);
        int expectedHashCodeResult = accountDto.hashCode();
        assertEquals(expectedHashCodeResult, accountDto2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AccountDto#equals(Object)}
     *   <li>{@link AccountDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
        // Arrange
        AccountDto accountDto = new AccountDto(null, "Dr Jane Doe", 10.0d);
        AccountDto accountDto2 = new AccountDto(null, "Dr Jane Doe", 10.0d);

        // Act and Assert
        assertEquals(accountDto, accountDto2);
        int expectedHashCodeResult = accountDto.hashCode();
        assertEquals(expectedHashCodeResult, accountDto2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AccountDto#equals(Object)}
     *   <li>{@link AccountDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3() {
        // Arrange
        AccountDto accountDto = new AccountDto(1L, null, 10.0d);
        AccountDto accountDto2 = new AccountDto(1L, null, 10.0d);

        // Act and Assert
        assertEquals(accountDto, accountDto2);
        int expectedHashCodeResult = accountDto.hashCode();
        assertEquals(expectedHashCodeResult, accountDto2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AccountDto#equals(Object)}
     *   <li>{@link AccountDto#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        AccountDto accountDto = new AccountDto(1L, "Dr Jane Doe", 10.0d);

        // Act and Assert
        assertEquals(accountDto, accountDto);
        int expectedHashCodeResult = accountDto.hashCode();
        assertEquals(expectedHashCodeResult, accountDto.hashCode());
    }

    /**
     * Method under test: {@link AccountDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        AccountDto accountDto = new AccountDto(2L, "Dr Jane Doe", 10.0d);

        // Act and Assert
        assertNotEquals(accountDto, new AccountDto(1L, "Dr Jane Doe", 10.0d));
    }

    /**
     * Method under test: {@link AccountDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        AccountDto accountDto = new AccountDto(null, "Dr Jane Doe", 10.0d);

        // Act and Assert
        assertNotEquals(accountDto, new AccountDto(1L, "Dr Jane Doe", 10.0d));
    }

    /**
     * Method under test: {@link AccountDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
        // Arrange
        AccountDto accountDto = new AccountDto(1L, "Mr John Smith", 10.0d);

        // Act and Assert
        assertNotEquals(accountDto, new AccountDto(1L, "Dr Jane Doe", 10.0d));
    }

    /**
     * Method under test: {@link AccountDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
        // Arrange
        AccountDto accountDto = new AccountDto(1L, null, 10.0d);

        // Act and Assert
        assertNotEquals(accountDto, new AccountDto(1L, "Dr Jane Doe", 10.0d));
    }

    /**
     * Method under test: {@link AccountDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
        // Arrange
        AccountDto accountDto = new AccountDto(1L, "Dr Jane Doe", 0.5d);

        // Act and Assert
        assertNotEquals(accountDto, new AccountDto(1L, "Dr Jane Doe", 10.0d));
    }

    /**
     * Method under test: {@link AccountDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange, Act and Assert
        assertNotEquals(new AccountDto(1L, "Dr Jane Doe", 10.0d), null);
    }

    /**
     * Method under test: {@link AccountDto#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange, Act and Assert
        assertNotEquals(new AccountDto(1L, "Dr Jane Doe", 10.0d), "Different type to AccountDto");
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link AccountDto#AccountDto(Long, String, double)}
     *   <li>{@link AccountDto#setAccountHolderName(String)}
     *   <li>{@link AccountDto#setBalance(double)}
     *   <li>{@link AccountDto#setId(Long)}
     *   <li>{@link AccountDto#toString()}
     *   <li>{@link AccountDto#getAccountHolderName()}
     *   <li>{@link AccountDto#getBalance()}
     *   <li>{@link AccountDto#getId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        AccountDto actualAccountDto = new AccountDto(1L, "Dr Jane Doe", 10.0d);
        actualAccountDto.setAccountHolderName("Dr Jane Doe");
        actualAccountDto.setBalance(10.0d);
        actualAccountDto.setId(1L);
        String actualToStringResult = actualAccountDto.toString();
        String actualAccountHolderName = actualAccountDto.getAccountHolderName();
        double actualBalance = actualAccountDto.getBalance();

        // Assert that nothing has changed
        assertEquals("AccountDto(id=1, accountHolderName=Dr Jane Doe, balance=10.0)", actualToStringResult);
        assertEquals("Dr Jane Doe", actualAccountHolderName);
        assertEquals(10.0d, actualBalance);
        assertEquals(1L, actualAccountDto.getId().longValue());
    }
}
