package com.kyc.batch.postalcodes.config.step;

import com.kyc.batch.postalcodes.repositories.KycParameterRepository;
import com.kyc.batch.postalcodes.tasklets.UpdatePostalCodeParameterTasklet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.kyc.batch.postalcodes.constants.AppConstants.UPDATE_POSTAL_CODE_PARAM_TASK;

@Configuration
public class UpdatePostalCodeParameterStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step updatePostalCodeParameterStep(KycParameterRepository repository){

        return stepBuilderFactory.get(UPDATE_POSTAL_CODE_PARAM_TASK)
                .tasklet(updatePostalCodeParameterTasklet(repository))
                .build();
    }

    @Bean
    public Tasklet updatePostalCodeParameterTasklet(KycParameterRepository repository){
        return new UpdatePostalCodeParameterTasklet(repository);
    }
}
