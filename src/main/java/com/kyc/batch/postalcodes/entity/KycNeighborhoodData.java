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
@Table(name = "KYC_POSTAL_CODES_NEIGHBORHOOD")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(KycNeighborhoodPK.class)
public class KycNeighborhoodData implements Serializable {

    @Id
    @Column(name="ID_NEIGHBORHOOD")
    private Long idNeighborhood;

    @Id
    @Column(name="RELATED_POSTAL_CODE")
    private String postalCode;

    @Id
    @Column(name="RELATED_VERSION")
    private Long version;

    @Column(name="NEIGHBORHOOD")
    private String neighborhood;

    @Column(name="UPLOAD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @Builder.Default
    private Date date = new Date();

}
