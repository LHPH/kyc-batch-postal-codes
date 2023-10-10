package com.kyc.batch.postalcodes.mappers;

import com.kyc.batch.postalcodes.model.PostalCodeRawRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.util.Locale;

import static com.kyc.batch.postalcodes.constants.AppConstants.C_CP;
import static com.kyc.batch.postalcodes.constants.AppConstants.C_CVE_CIUDAD;
import static com.kyc.batch.postalcodes.constants.AppConstants.C_ESTADO;
import static com.kyc.batch.postalcodes.constants.AppConstants.C_MNPIO;
import static com.kyc.batch.postalcodes.constants.AppConstants.C_OFICINA;
import static com.kyc.batch.postalcodes.constants.AppConstants.C_TIPO_ASENTA;
import static com.kyc.batch.postalcodes.constants.AppConstants.D_ASENTA;
import static com.kyc.batch.postalcodes.constants.AppConstants.D_CIUDAD;
import static com.kyc.batch.postalcodes.constants.AppConstants.D_CODIGO;
import static com.kyc.batch.postalcodes.constants.AppConstants.D_CP;
import static com.kyc.batch.postalcodes.constants.AppConstants.D_ESTADO;
import static com.kyc.batch.postalcodes.constants.AppConstants.D_MNPIO;
import static com.kyc.batch.postalcodes.constants.AppConstants.D_TIPO_ASENTA;
import static com.kyc.batch.postalcodes.constants.AppConstants.D_ZONA;
import static com.kyc.batch.postalcodes.constants.AppConstants.ID_ASENTA_CPCONS;

public class PostalCodeFieldSetMapper implements FieldSetMapper<PostalCodeRawRecord> {

    @Override
    public PostalCodeRawRecord mapFieldSet(FieldSet fieldSet) {

        PostalCodeRawRecord record = new PostalCodeRawRecord();
        Locale locale = new Locale("es");

        record.setPostalCode(fieldSet.readString(D_CODIGO));
        record.setNeighborhood(StringUtils.upperCase(fieldSet.readString(D_ASENTA),locale));
        record.setSettlementType(fieldSet.readString(D_TIPO_ASENTA));
        record.setTown(StringUtils.upperCase(fieldSet.readString(D_MNPIO),locale));
        record.setState(StringUtils.upperCase(fieldSet.readString(D_ESTADO),locale));
        record.setCity(StringUtils.upperCase(fieldSet.readString(D_CIUDAD),locale));
        record.setOffice(fieldSet.readString(D_CP));
        record.setIdState(fieldSet.readString(C_ESTADO));
        record.setIdOffice(fieldSet.readString(C_OFICINA));
        record.setOfficeOther(fieldSet.readString(C_CP));
        record.setIdSettlementType(fieldSet.readString(C_TIPO_ASENTA));
        record.setIdTown(fieldSet.readString(C_MNPIO));
        record.setIdNeighborhood(fieldSet.readString(ID_ASENTA_CPCONS));
        record.setZone(StringUtils.upperCase(fieldSet.readString(D_ZONA),locale));
        record.setIdCity(fieldSet.readString(C_CVE_CIUDAD));

        return record;
    }
}
