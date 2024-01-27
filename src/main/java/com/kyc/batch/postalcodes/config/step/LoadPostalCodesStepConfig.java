package com.kyc.batch.postalcodes.config.step;

import com.kyc.batch.postalcodes.constants.AppConstants;
import com.kyc.batch.postalcodes.listeners.LoadPostalCodesStepListener;
import com.kyc.batch.postalcodes.mappers.PostalCodeFieldSetMapper;
import com.kyc.batch.postalcodes.model.PostalCodeRawRecord;
import com.kyc.batch.postalcodes.model.PostalCodeWrapper;
import com.kyc.batch.postalcodes.policy.PostalCodeSkipPolicy;
import com.kyc.batch.postalcodes.processors.PostalCodeProcessor;
import com.kyc.batch.postalcodes.writers.AdapterJpaItemWriter;
import com.kyc.core.batch.BatchStepListener;
import com.kyc.core.exception.handlers.KycBatchExceptionHandler;
import com.kyc.core.properties.KycMessages;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.listener.CompositeStepExecutionListener;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

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
import static com.kyc.batch.postalcodes.constants.AppConstants.LOADING_DATA_STEP;

@Configuration
public class LoadPostalCodesStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntityManagerFactory emf;

    @Value("${kyc.batch.postal-codes.path}")
    private String filePath;

    @Value("${kyc.batch.postal-codes.skip-limit}")
    private int skipLimit;

    @Value("${kyc.batch.postal-codes.chunk}")
    private int chunkSize;

    @Autowired
    private KycBatchExceptionHandler exceptionHandler;

    @Autowired
    private KycMessages kycMessages;

    @Bean
    public Step loadPostalCodesStep(){
        return stepBuilderFactory
                .get(LOADING_DATA_STEP)
                .listener(compositeStepExecutionListener())
                .<PostalCodeRawRecord, PostalCodeWrapper>chunk(chunkSize)
                .faultTolerant()
                .skipPolicy(new PostalCodeSkipPolicy(skipLimit))
                .reader(filePostalCodesItemReader())
                .processor(postalCodeProcessor())
                .writer(compositeLoadPostalCodesItemWriter())
                .exceptionHandler(exceptionHandler)
                .build();
    }

    @Bean
    public FlatFileItemReader<PostalCodeRawRecord> filePostalCodesItemReader(){

        return new FlatFileItemReaderBuilder<PostalCodeRawRecord>()
                .name("loadPostalCodesStepItemReader")
                .resource(new FileSystemResource(filePath))
                .strict(false)
                .delimited()
                .delimiter("|")
                .names(
                        D_CODIGO,D_ASENTA,D_TIPO_ASENTA,D_MNPIO,D_ESTADO,D_CIUDAD,D_CP,
                        C_ESTADO,C_OFICINA,C_CP,C_TIPO_ASENTA,C_MNPIO,ID_ASENTA_CPCONS,
                        D_ZONA,C_CVE_CIUDAD
                )
                .linesToSkip(2)
                .fieldSetMapper(new PostalCodeFieldSetMapper())
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<PostalCodeRawRecord, PostalCodeWrapper> postalCodeProcessor(){
        return new PostalCodeProcessor();
    }

   @Bean
   public ItemWriter<PostalCodeWrapper> postalCodesMainItemWriter(){
        return new AdapterJpaItemWriter<>(PostalCodeWrapper::getMainData,emf);
    }

    @Bean
    public ItemWriter<PostalCodeWrapper> postalCodesNeighborhoodItemWriter(){
        return new AdapterJpaItemWriter<>(PostalCodeWrapper::getNeighborhoodData,emf);
    }

    @Bean
    public ItemWriter<PostalCodeWrapper> compositeLoadPostalCodesItemWriter(){

        CompositeItemWriter<PostalCodeWrapper> compositeItemWriter = new CompositeItemWriter<>();
        compositeItemWriter.setDelegates(Arrays.asList(postalCodesMainItemWriter(),postalCodesNeighborhoodItemWriter()));
        return compositeItemWriter;
    }

    @Bean
    public CompositeStepExecutionListener compositeStepExecutionListener(){
        CompositeStepExecutionListener listener = new CompositeStepExecutionListener();
        listener.setListeners(new StepExecutionListener[]{
                //For afterStep is the inverse order to apply
                executiveBatchStepListener(),
                promotionListener(),
                new LoadPostalCodesStepListener(skipLimit),
        });
        return listener;
    }

    @Bean
    public BatchStepListener<PostalCodeRawRecord, PostalCodeWrapper> executiveBatchStepListener(){
        return new BatchStepListener<>(LOADING_DATA_STEP);
    }

    @Bean
    public ExecutionContextPromotionListener promotionListener() {

        ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
        listener.setKeys(new String[] {AppConstants.WRITTEN_ITEMS});
        return listener;
    }
}
