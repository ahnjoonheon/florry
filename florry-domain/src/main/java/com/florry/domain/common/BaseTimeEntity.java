package com.florry.domain.common;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    @CreatedDate
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

    public LocalDateTime getCreatedDate(){
        return this.createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return this.modifiedDate;
    }
}

