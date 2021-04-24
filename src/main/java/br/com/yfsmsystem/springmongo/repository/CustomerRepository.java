package br.com.yfsmsystem.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.yfsmsystem.springmongo.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Long> {

  Customer findByEmail(String email);
  Boolean existsByEmail(String email);
}
