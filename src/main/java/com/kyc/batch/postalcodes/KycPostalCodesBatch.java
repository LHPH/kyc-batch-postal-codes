package com.kyc.batch.postalcodes;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class KycPostalCodesBatch {

	public static void main(String[] args) {
		SpringApplication.run(KycPostalCodesBatch.class, args);
	}

}
