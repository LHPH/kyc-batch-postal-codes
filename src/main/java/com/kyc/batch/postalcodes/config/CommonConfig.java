package com.kyc.batch.postalcodes.config;

import com.kyc.core.exception.handlers.KycBatchExceptionHandler;
import com.kyc.core.properties.KycMessages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = {KycMessages.class})
@Configuration
public class CommonConfig {

    @Bean
    public KycBatchExceptionHandler exceptionHandler(KycMessages kycMessages){
        return new KycBatchExceptionHandler(kycMessages.getMessage("001"));
    }
}
