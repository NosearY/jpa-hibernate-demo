package io.github.noseary.hibernatedemo.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateCustomerDto {

    private String username;

    private String passwordHash;

}
