package br.com.yfsmsystem.springmongo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.yfsmsystem.springmongo.dto.CustomerDto;
import br.com.yfsmsystem.springmongo.model.Customer;
import br.com.yfsmsystem.springmongo.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

class CustomerServiceTest {

  private static final String NAME = "Yallison";
  private static final String EMAIL = "yallisontest@mail.com";
  private static final String ID = "a1b2c3e4";

  @Mock
  private CustomerRepository customerRepository;
  @InjectMocks
  private CustomerService customerService;

  private Customer customer;
  private CustomerDto customerDto;

  @BeforeEach
  void setUp() {
    customer = Customer.builder()
        .name(NAME)
        .email(EMAIL)
        .id(ID)
        .build();

    customerDto = CustomerDto.builder()
        .name(NAME)
        .email(EMAIL)
        .id(ID)
        .build();
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllCustomer() {
    when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));
    List<CustomerDto> result = customerService.getAllCustomer();
    ;
    Assertions.assertNotNull(result);
  }

  @Test
  void testGetCustomerByEmail() {
    when(customerRepository.findByEmail(anyString())).thenReturn(customer);
    CustomerDto result = customerService.getCustomerByEmail(EMAIL);
    Assertions.assertEquals(EMAIL, result.getEmail());
  }

  @Test
  void testSaveNewCustomer() {
    when(customerRepository.existsByEmail(anyString())).thenReturn(Boolean.FALSE);
    when(customerRepository.save(any())).thenReturn(customer);
    CustomerDto result = customerService.saveNewCustomer(customerDto);
    Assertions.assertEquals(customerDto.toString(), result.toString());
  }


  @Test
  void testSaveNewCustomerException() {
    when(customerRepository.existsByEmail(anyString())).thenReturn(Boolean.TRUE);
    try {
      CustomerDto result = customerService.saveNewCustomer(customerDto);
    } catch (ResponseStatusException e) {
      Assertions.assertTrue(e.getStatus().is4xxClientError());
    }
  }

  @Test
  void testDeleteCustomer() {
    when(customerRepository.findByEmail(anyString())).thenReturn(customer);
    doNothing().when(customerRepository).delete(any());
    Assertions.assertDoesNotThrow(
        () -> customerService.deleteCustomer(anyString()));
  }
}

