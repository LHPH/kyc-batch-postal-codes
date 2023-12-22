package com.kyc.batch.postalcodes.tasklets;

import com.kyc.batch.postalcodes.repositories.KycParameterRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class UpdatePostalCodeParameterTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePostalCodeParameterTasklet.class);

    private final KycParameterRepository kycParameterRepository;

    public UpdatePostalCodeParameterTasklet(KycParameterRepository kycParameterRepository){
        this.kycParameterRepository = kycParameterRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        LOGGER.info("Updating the postal code parameter version");
        int result = kycParameterRepository.updatePostalCodeParameter();
        LOGGER.info("The parameter was updated {}", BooleanUtils.toBoolean(result));
        return RepeatStatus.FINISHED;
    }
}
