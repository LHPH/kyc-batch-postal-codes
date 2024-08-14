package com.kyc.batch.postalcodes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
