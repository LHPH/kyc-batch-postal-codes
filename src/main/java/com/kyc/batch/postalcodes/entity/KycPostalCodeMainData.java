package com.kyc.batch.postalcodes.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "KYC_POSTAL_CODES_CENTRAL")
@Setter
@Getter
@IdClass(KycPostalCodeMainPK.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KycPostalCodeMainData implements Serializable {

    @Id
    @Column(name="POSTAL_CODE")
    private String postalCode;

    @Id
    @Column(name="VERSION")
    private Long version;

    @Column(name="ID_CITY")
    private Long idCity;

    @Column(name="CITY")
    private String city;

    @Column(name="ID_STATE")
    private Long idState;

    @Column(name="STATE")
    private String state;

    @Column(name="UPLOAD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date date = new Date();
}
