package com.kyc.batch.postalcodes.config.step;

import com.kyc.core.batch.tasklets.CleanFilesTasklet;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.kyc.batch.postalcodes.constants.AppConstants.CLEAN_FILE_TASK;

@Configuration
public class CleanFileStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("${kyc.batch.postal-codes.path}")
    private String filePath;

    @Bean
    public Step cleanFileStep(){

        return stepBuilderFactory.get(CLEAN_FILE_TASK)
                .tasklet(cleanFileTasklet())
                .build();
    }

    @Bean
    public Tasklet cleanFileTasklet(){
        return new CleanFilesTasklet(CLEAN_FILE_TASK,filePath);
    }
}
