package br.com.yfsmsystem.springmongo.controller;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yfsmsystem.springmongo.dto.CustomerDto;
import br.com.yfsmsystem.springmongo.service.CustomerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {

  private final CustomerService customerService;

  @GetMapping("/all")
  public ResponseEntity<List<CustomerDto>> getAllCustomer() {
    return ResponseEntity.ok(customerService.getAllCustomer());
  }

  @GetMapping("/{email}")
  public ResponseEntity<CustomerDto> getCustomerByEmail(@PathVariable String email) {
    return ResponseEntity.ok(customerService.getCustomerByEmail(email));
  }

  @PostMapping
  public ResponseEntity<CustomerDto> sendNewCustomer(@RequestBody CustomerDto customerDto) {
    return ResponseEntity.ok(customerService.saveNewCustomer(customerDto));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteCustomerByEmail(@PathVariable String email) {
    customerService.deleteCustomer(email);
    return ResponseEntity.noContent().build();
  }
}
