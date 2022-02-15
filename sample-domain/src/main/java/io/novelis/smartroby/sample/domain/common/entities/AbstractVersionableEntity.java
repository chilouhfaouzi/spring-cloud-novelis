package io.novelis.smartroby.sample.domain.common.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@Getter @Setter
public abstract class AbstractVersionableEntity extends AbstractEntity {

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
