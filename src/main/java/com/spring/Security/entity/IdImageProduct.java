package com.spring.Security.entity;

import lombok.Data;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
@SuppressWarnings("serial")
@Data
@Embeddable
public class IdImageProduct implements Serializable {
    @ManyToOne
    private Products product;

}
