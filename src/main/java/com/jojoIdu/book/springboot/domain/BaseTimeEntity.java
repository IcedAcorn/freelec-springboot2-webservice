package com.jojoIdu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
//JPA Entity 클래스들이 BaseTimeENtity를 상속할 경우 이 클래스의 필드도 컬럼으로 인식하도록 함
@MappedSuperclass
//BaseTimeEntity 클래스에 Auditing 기능을 포함 (생성일자, 수정일 자 같은 애들이 상속받은 entity에 대해서 자동으로 매핑되도록 해주는것)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
