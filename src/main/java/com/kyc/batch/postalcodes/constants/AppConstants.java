package com.kyc.batch.postalcodes.constants;

import com.kyc.core.constants.BatchConstants;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class AppConstants {

    public static final String JOB_NAME = String.format(BatchConstants.KYC_BATCH_JOB_TEMPLATE,
            "POSTAL-CODES","LOADING");
    public static final String LOADING_DATA_STEP = String.format(BatchConstants.KYC_BATCH_STEP_TEMPLATE,
            "POSTAL-CODES","LOADING-DATA");
    public static final String CLEAN_FILE_TASK = String.format(BatchConstants.KYC_BATCH_TASKLET_TEMPLATE,
            "POSTAL-CODES","CLEAN-FILE");

    public static final String D_CODIGO = "d_codigo";
    public static final String D_ASENTA = "d_asenta";
    public static final String D_TIPO_ASENTA = "d_tipo_asenta";
    public static final String D_MNPIO = "D_mnpio";
    public static final String D_ESTADO = "d_estado";
    public static final String D_CIUDAD = "d_ciudad";
    public static final String D_CP = "d_CP";
    public static final String C_ESTADO = "c_estado";
    public static final String C_OFICINA = "c_oficina";
    public static final String C_CP = "c_CP";
    public static final String C_TIPO_ASENTA = "c_tipo_asenta";
    public static final String C_MNPIO = "c_mnpio";
    public static final String ID_ASENTA_CPCONS = "id_asenta_cpcons";
    public static final String D_ZONA = "d_zona";
    public static final String C_CVE_CIUDAD = "c_cve_ciudad";
}
