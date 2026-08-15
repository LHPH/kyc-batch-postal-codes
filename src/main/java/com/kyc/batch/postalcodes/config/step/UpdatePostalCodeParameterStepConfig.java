package com.kyc.batch.postalcodes.config.step;

import com.kyc.batch.postalcodes.repositories.KycParameterRepository;
import com.kyc.batch.postalcodes.tasklets.UpdatePostalCodeParameterTasklet;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static com.kyc.batch.postalcodes.constants.AppConstants.UPDATE_POSTAL_CODE_PARAM_TASK;

@Configuration
public class UpdatePostalCodeParameterStepConfig {

    @Bean
    public Step updatePostalCodeParameterStep(JobRepository jobRepository,
                                              PlatformTransactionManager transactionManager,
                                              KycParameterRepository repository){

        return new StepBuilder(UPDATE_POSTAL_CODE_PARAM_TASK,jobRepository)
                .tasklet(updatePostalCodeParameterTasklet(repository),transactionManager)
                .build();
    }

    @Bean
    public Tasklet updatePostalCodeParameterTasklet(KycParameterRepository repository){
        return new UpdatePostalCodeParameterTasklet(repository);
    }
}
