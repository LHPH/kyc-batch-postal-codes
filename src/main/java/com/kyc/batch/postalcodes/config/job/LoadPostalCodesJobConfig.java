package com.kyc.batch.postalcodes.config.job;

import com.kyc.core.batch.BatchJobExecutionListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.kyc.batch.postalcodes.constants.AppConstants.JOB_NAME;

@Configuration
public class LoadPostalCodesJobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job loadPostalCodesJob(Step loadPostalCodesStep){
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(loadPostalCodesJobListener())
                .start(loadPostalCodesStep)
                //.next(cleanFileStep)
                .build();
    }

    @Bean
    public BatchJobExecutionListener loadPostalCodesJobListener(){

        return new BatchJobExecutionListener(JOB_NAME);
    }
}
