package com.kyc.batch.postalcodes.tasklets;

import com.kyc.batch.postalcodes.constants.AppConstants;
import com.kyc.batch.postalcodes.repositories.KycParameterRepository;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class UpdatePostalCodeParameterTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePostalCodeParameterTasklet.class);

    private final KycParameterRepository kycParameterRepository;

    public UpdatePostalCodeParameterTasklet(KycParameterRepository kycParameterRepository){
        this.kycParameterRepository = kycParameterRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        ExecutionContext executionContext = chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext();

        if(Boolean.TRUE.equals(executionContext.get(AppConstants.WRITTEN_ITEMS))){

            LOGGER.info("Updating the postal code parameter version");
            int result = kycParameterRepository.updatePostalCodeParameter();
            LOGGER.info("The parameter was updated {}", BooleanUtils.toBoolean(result));
        }
        else{
            LOGGER.warn("The parameter was not updated due no new version or the job does not successfully completed");
        }
        return RepeatStatus.FINISHED;
    }
}
