package com.kyc.batch.postalcodes.model;

import com.kyc.batch.postalcodes.entity.KycNeighborhoodData;
import com.kyc.batch.postalcodes.entity.KycPostalCodeMainData;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostalCodeWrapper {

    private KycPostalCodeMainData mainData;
    private KycNeighborhoodData neighborhoodData;
}
