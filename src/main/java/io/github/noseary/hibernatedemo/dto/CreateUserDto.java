package io.github.noseary.hibernatedemo.dto;

import java.util.Date;
import javax.persistence.Column;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateUserDto {

    private String username;

    private String passwordHash;

    private String customerName;

}
