package br.com.yfsmsystem.springmongo.converters;

import br.com.yfsmsystem.springmongo.dto.CustomerDto;
import br.com.yfsmsystem.springmongo.model.Customer;

public class CustomerConverter {

  protected CustomerConverter() {
    throw new IllegalStateException("Utility class");
  }


  public static Customer mapObjectToEntity(CustomerDto customerDto) {
    return Customer.builder()
        .email(customerDto.getEmail())
        .name(customerDto.getName())
        .build();
  }


  public static CustomerDto mapEntityToObject(Customer customer) {
    return CustomerDto.builder()
        .id(customer.getId())
        .email(customer.getEmail())
        .name(customer.getName())
        .build();
  }

}
