package br.com.yfsmsystem.springmongo.service;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.yfsmsystem.springmongo.converters.CustomerConverter;
import br.com.yfsmsystem.springmongo.dto.CustomerDto;
import br.com.yfsmsystem.springmongo.repository.CustomerRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;


  public List<CustomerDto> getAllCustomer() {
    return customerRepository.findAll().stream().map(CustomerConverter::mapEntityToObject)
        .collect(Collectors.toList());
  }

  public CustomerDto getCustomerByEmail(String email) {
    return CustomerConverter
        .mapEntityToObject(customerRepository.findByEmail(email));
  }

  public CustomerDto saveNewCustomer(CustomerDto customerDto) {
    if (Boolean.FALSE.equals(customerRepository.existsByEmail(customerDto.getEmail()))) {
      return CustomerConverter.mapEntityToObject(customerRepository
          .save(CustomerConverter.mapObjectToEntity(customerDto)));
    } else {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer already exist");
    }
  }

  public void deleteCustomer(String email) {
    var customer = customerRepository.findByEmail(email);
    if (Objects.nonNull(customer)) {
      customerRepository.delete(customer);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }

  }


}
