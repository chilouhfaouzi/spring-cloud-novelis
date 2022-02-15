package io.novelis.smartroby.sample.domain;

import io.novelis.smartroby.sample.domain.common.entities.AbstractTracableEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="person")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity extends AbstractTracableEntity  {

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

}
