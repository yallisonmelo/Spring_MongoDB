package br.com.yfsmsystem.springmongo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CustomerDto {

  private String id;
  private String name;
  private String email;

}
