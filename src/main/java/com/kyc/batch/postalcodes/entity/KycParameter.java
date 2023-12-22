package com.kyc.batch.postalcodes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name="KYC_PARAMETERS")
@Getter
@Setter
public class KycParameter {

    @Id
    private Long id;

    @Column(name="PARAM_KEY")
    private String key;

    @Column(name="PARAM_VALUE")
    private String value;

    @Temporal(TemporalType.DATE)
    @Column(name="CREATION_DATE")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column(name="UPDATED_DATE")
    private Date updatedDate;
}
