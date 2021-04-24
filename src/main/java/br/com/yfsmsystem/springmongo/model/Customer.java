package br.com.yfsmsystem.springmongo.model;

import lombok.Builder;
import lombok.Getter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "customer")
@Getter
@Builder
public class Customer {

  @Id
  @Field("_id")
  private String id;
  @Field("_name")
  private String name;
  @Field("_email")
  private String email;

}
