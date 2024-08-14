package com.kyc.batch.postalcodes.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
@EqualsAndHashCode
public class KycNeighborhoodPK implements Serializable {

    private Long idNeighborhood;

    private String postalCode;

    private Long version;
}
