package com.simon.world_banking_app_v1.domain.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.angus.mail.imap.protocol.ID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@EnableJpaAuditing
@Getter
@Setter
public class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column
    private LocalDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime dateModified;

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if ((obj == null) || getClass() != obj.getClass()) return false;

        BaseClass that = (BaseClass) obj;

        return Objects.equals(id, that.id);
    }

    @PrePersist
    @PreUpdate
    public void prePersist(){
        if(dateCreated == null){
            dateCreated = LocalDateTime.now();
        }
        dateModified = LocalDateTime.now();
    }

    @Override
    public int hashCode(){
        return id != null ? id.hashCode() : 0;
    }
}
