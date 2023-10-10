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
