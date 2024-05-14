package com.nybank.banking.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.nybank.banking.dto.AccountDto;
import com.nybank.banking.entity.Account;
import org.junit.jupiter.api.Test;

class AccountMapperTest {
    /**
     * Method under test: {@link AccountMapper#mapToAccount(AccountDto)}
     */
    @Test
    void testMapToAccount() {
        // Arrange and Act
        Account actualMapToAccountResult = AccountMapper.mapToAccount(AccountMapper.mapToAccountDto(new Account()));

        // Assert
        assertNull(actualMapToAccountResult.getId());
        assertNull(actualMapToAccountResult.getAccountHolderName());
        assertEquals(0.0d, actualMapToAccountResult.getBalance());
    }

    /**
     * Method under test: {@link AccountMapper#mapToAccountDto(Account)}
     */
    @Test
    void testMapToAccountDto() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);

        // Act
        AccountDto actualMapToAccountDtoResult = AccountMapper.mapToAccountDto(account);

        // Assert
        assertEquals("Dr Jane Doe", actualMapToAccountDtoResult.getAccountHolderName());
        assertEquals(10.0d, actualMapToAccountDtoResult.getBalance());
        assertEquals(1L, actualMapToAccountDtoResult.getId().longValue());
    }
}
