package com.kyc.batch.postalcodes.listeners;

import com.kyc.batch.postalcodes.constants.AppConstants;
import com.kyc.batch.postalcodes.model.PostalCodeRawRecord;
import com.kyc.batch.postalcodes.model.PostalCodeWrapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;
import org.springframework.batch.item.ExecutionContext;

import static com.kyc.core.constants.BatchConstants.BATCH_PARTIAL_COMPLETED;

@AllArgsConstructor
public class LoadPostalCodesStepListener extends StepListenerSupport<PostalCodeRawRecord, PostalCodeWrapper> {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoadPostalCodesStepListener.class);

    private int skipLimit;

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        LOGGER.info("Checking status to determine the final step status");
        if(stepExecution.getSkipCount()==0){

           if(stepExecution.getWriteCount()>0){
                ExecutionContext stepContext = stepExecution.getExecutionContext();
                stepContext.put(AppConstants.WRITTEN_ITEMS,true);
            }
            return ExitStatus.COMPLETED;
        }
        else if(stepExecution.getSkipCount()<=skipLimit){
            return new ExitStatus(BATCH_PARTIAL_COMPLETED);
        }
        return super.afterStep(stepExecution);
    }
}
