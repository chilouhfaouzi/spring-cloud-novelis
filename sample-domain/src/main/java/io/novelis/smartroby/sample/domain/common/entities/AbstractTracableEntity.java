package io.novelis.smartroby.sample.domain.common.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
public abstract class AbstractTracableEntity extends AbstractVersionableEntity {

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void initCreationTrace() {
        createdAt = new Date();
        //TODO: get from sercurity context
        createdBy = "anonyme";
    }

    @PreUpdate
    protected void initUpdateTrace() {
        updatedAt = new Date();
        //TODO: get from sercurity context
        updatedBy = "anonyme";
    }
}
