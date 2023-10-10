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
public class KycPostalCodeMainPK implements Serializable {

    private String postalCode;

    private Long version;
}
