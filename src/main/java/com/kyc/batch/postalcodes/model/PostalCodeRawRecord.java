package com.kyc.batch.postalcodes.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostalCodeRawRecord {

    private String postalCode;
    private String neighborhood;
    private String settlementType;
    private String town;
    private String state;
    private String city;
    private String office;
    private String idState;
    private String idOffice;
    private String officeOther;
    private String idSettlementType;
    private String idTown;
    private String idNeighborhood;
    private String zone;
    private String idCity;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("postalCode='").append(postalCode).append('\'');
        sb.append(", neighborhood='").append(neighborhood).append('\'');
        sb.append(", settlementType='").append(settlementType).append('\'');
        sb.append(", town='").append(town).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", office='").append(office).append('\'');
        sb.append(", idState='").append(idState).append('\'');
        sb.append(", idOffice='").append(idOffice).append('\'');
        sb.append(", officeOther='").append(officeOther).append('\'');
        sb.append(", idSettlementType='").append(idSettlementType).append('\'');
        sb.append(", idTown='").append(idTown).append('\'');
        sb.append(", idNeighborhood='").append(idNeighborhood).append('\'');
        sb.append(", zone='").append(zone).append('\'');
        sb.append(", idCity='").append(idCity).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
