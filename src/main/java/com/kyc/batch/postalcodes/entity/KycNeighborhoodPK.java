package com.kyc.batch.postalcodes.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
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
