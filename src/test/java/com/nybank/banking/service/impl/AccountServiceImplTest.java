package com.nybank.banking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nybank.banking.dto.AccountDto;
import com.nybank.banking.entity.Account;
import com.nybank.banking.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccountServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AccountServiceImplTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    /**
     * Method under test: {@link AccountServiceImpl#createAccount(AccountDto)}
     */
    @Test
    void testCreateAccount() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account);

        // Act
        AccountDto actualCreateAccountResult = accountServiceImpl.createAccount(new AccountDto(1L, "Dr Jane Doe", 10.0d));

        // Assert
        verify(accountRepository).save(isA(Account.class));
        assertEquals("Dr Jane Doe", actualCreateAccountResult.getAccountHolderName());
        assertEquals(10.0d, actualCreateAccountResult.getBalance());
        assertEquals(1L, actualCreateAccountResult.getId().longValue());
    }

    /**
     * Method under test: {@link AccountServiceImpl#createAccount(AccountDto)}
     */
    @Test
    void testCreateAccount2() {
        // Arrange
        when(accountRepository.save(Mockito.<Account>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> accountServiceImpl.createAccount(new AccountDto(1L, "Dr Jane Doe", 10.0d)));
        verify(accountRepository).save(isA(Account.class));
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAccountById(Long)}
     */
    @Test
    void testGetAccountById() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        AccountDto actualAccountById = accountServiceImpl.getAccountById(1L);

        // Assert
        verify(accountRepository).findById(eq(1L));
        assertEquals("Dr Jane Doe", actualAccountById.getAccountHolderName());
        assertEquals(10.0d, actualAccountById.getBalance());
        assertEquals(1L, actualAccountById.getId().longValue());
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAccountById(Long)}
     */
    @Test
    void testGetAccountById2() {
        // Arrange
        Optional<Account> emptyResult = Optional.empty();
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> accountServiceImpl.getAccountById(1L));
        verify(accountRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAccountById(Long)}
     */
    @Test
    void testGetAccountById3() {
        // Arrange
        when(accountRepository.findById(Mockito.<Long>any())).thenThrow(new RuntimeException("Account does not exists"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> accountServiceImpl.getAccountById(1L));
        verify(accountRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link AccountServiceImpl#deposit(Long, double)}
     */
    @Test
    void testDeposit() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);
        Optional<Account> ofResult = Optional.of(account);

        Account account2 = new Account();
        account2.setAccountHolderName("Dr Jane Doe");
        account2.setBalance(10.0d);
        account2.setId(1L);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        AccountDto actualDepositResult = accountServiceImpl.deposit(1L, 10.0d);

        // Assert
        verify(accountRepository).findById(eq(1L));
        verify(accountRepository).save(isA(Account.class));
        assertEquals("Dr Jane Doe", actualDepositResult.getAccountHolderName());
        assertEquals(10.0d, actualDepositResult.getBalance());
        assertEquals(1L, actualDepositResult.getId().longValue());
    }

    /**
     * Method under test: {@link AccountServiceImpl#deposit(Long, double)}
     */
    @Test
    void testDeposit2() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.save(Mockito.<Account>any())).thenThrow(new RuntimeException("foo"));
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> accountServiceImpl.deposit(1L, 10.0d));
        verify(accountRepository).findById(eq(1L));
        verify(accountRepository).save(isA(Account.class));
    }

    /**
     * Method under test: {@link AccountServiceImpl#deposit(Long, double)}
     */
    @Test
    void testDeposit3() {
        // Arrange
        Optional<Account> emptyResult = Optional.empty();
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> accountServiceImpl.deposit(1L, 10.0d));
        verify(accountRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link AccountServiceImpl#withdraw(Long, double)}
     */
    @Test
    void testWithdraw() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);
        Optional<Account> ofResult = Optional.of(account);

        Account account2 = new Account();
        account2.setAccountHolderName("Dr Jane Doe");
        account2.setBalance(10.0d);
        account2.setId(1L);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account2);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        AccountDto actualWithdrawResult = accountServiceImpl.withdraw(1L, 10.0d);

        // Assert
        verify(accountRepository).findById(eq(1L));
        verify(accountRepository).save(isA(Account.class));
        assertEquals("Dr Jane Doe", actualWithdrawResult.getAccountHolderName());
        assertEquals(10.0d, actualWithdrawResult.getBalance());
        assertEquals(1L, actualWithdrawResult.getId().longValue());
    }

    /**
     * Method under test: {@link AccountServiceImpl#withdraw(Long, double)}
     */
    @Test
    void testWithdraw2() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.save(Mockito.<Account>any())).thenThrow(new RuntimeException("foo"));
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> accountServiceImpl.withdraw(1L, 10.0d));
        verify(accountRepository).findById(eq(1L));
        verify(accountRepository).save(isA(Account.class));
    }

    /**
     * Method under test: {@link AccountServiceImpl#withdraw(Long, double)}
     */
    @Test
    void testWithdraw3() {
        // Arrange
        Account account = mock(Account.class);
        when(account.getBalance()).thenReturn(0.5d);
        doNothing().when(account).setAccountHolderName(Mockito.<String>any());
        doNothing().when(account).setBalance(anyDouble());
        doNothing().when(account).setId(Mockito.<Long>any());
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);
        Optional<Account> ofResult = Optional.of(account);
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> accountServiceImpl.withdraw(1L, 10.0d));
        verify(account).getBalance();
        verify(account).setAccountHolderName(eq("Dr Jane Doe"));
        verify(account).setBalance(eq(10.0d));
        verify(account).setId(eq(1L));
        verify(accountRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link AccountServiceImpl#withdraw(Long, double)}
     */
    @Test
    void testWithdraw4() {
        // Arrange
        Optional<Account> emptyResult = Optional.empty();
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> accountServiceImpl.withdraw(1L, 10.0d));
        verify(accountRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts() {
        // Arrange
        when(accountRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<AccountDto> actualAllAccounts = accountServiceImpl.getAllAccounts();

        // Assert
        verify(accountRepository).findAll();
        assertTrue(actualAllAccounts.isEmpty());
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts2() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(accountRepository.findAll()).thenReturn(accountList);

        // Act
        List<AccountDto> actualAllAccounts = accountServiceImpl.getAllAccounts();

        // Assert
        verify(accountRepository).findAll();
        assertEquals(1, actualAllAccounts.size());
        AccountDto getResult = actualAllAccounts.get(0);
        assertEquals("Dr Jane Doe", getResult.getAccountHolderName());
        assertEquals(10.0d, getResult.getBalance());
        assertEquals(1L, getResult.getId().longValue());
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts3() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);

        Account account2 = new Account();
        account2.setAccountHolderName("Mr John Smith");
        account2.setBalance(0.5d);
        account2.setId(2L);

        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account2);
        accountList.add(account);
        when(accountRepository.findAll()).thenReturn(accountList);

        // Act
        List<AccountDto> actualAllAccounts = accountServiceImpl.getAllAccounts();

        // Assert
        verify(accountRepository).findAll();
        assertEquals(2, actualAllAccounts.size());
        AccountDto getResult = actualAllAccounts.get(1);
        assertEquals("Dr Jane Doe", getResult.getAccountHolderName());
        AccountDto getResult2 = actualAllAccounts.get(0);
        assertEquals("Mr John Smith", getResult2.getAccountHolderName());
        assertEquals(0.5d, getResult2.getBalance());
        assertEquals(10.0d, getResult.getBalance());
        assertEquals(1L, getResult.getId().longValue());
        assertEquals(2L, getResult2.getId().longValue());
    }

    /**
     * Method under test: {@link AccountServiceImpl#getAllAccounts()}
     */
    @Test
    void testGetAllAccounts4() {
        // Arrange
        when(accountRepository.findAll()).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> accountServiceImpl.getAllAccounts());
        verify(accountRepository).findAll();
    }

    /**
     * Method under test: {@link AccountServiceImpl#deleteAccount(Long)}
     */
    @Test
    void testDeleteAccount() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);
        Optional<Account> ofResult = Optional.of(account);
        doNothing().when(accountRepository).deleteById(Mockito.<Long>any());
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        accountServiceImpl.deleteAccount(1L);

        // Assert that nothing has changed
        verify(accountRepository).deleteById(eq(1L));
        verify(accountRepository).findById(eq(1L));
        assertTrue(accountServiceImpl.getAllAccounts().isEmpty());
    }

    /**
     * Method under test: {@link AccountServiceImpl#deleteAccount(Long)}
     */
    @Test
    void testDeleteAccount2() {
        // Arrange
        Account account = new Account();
        account.setAccountHolderName("Dr Jane Doe");
        account.setBalance(10.0d);
        account.setId(1L);
        Optional<Account> ofResult = Optional.of(account);
        doThrow(new RuntimeException("foo")).when(accountRepository).deleteById(Mockito.<Long>any());
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> accountServiceImpl.deleteAccount(1L));
        verify(accountRepository).deleteById(eq(1L));
        verify(accountRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link AccountServiceImpl#deleteAccount(Long)}
     */
    @Test
    void testDeleteAccount3() {
        // Arrange
        Optional<Account> emptyResult = Optional.empty();
        when(accountRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> accountServiceImpl.deleteAccount(1L));
        verify(accountRepository).findById(eq(1L));
    }
}
