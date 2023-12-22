package com.kyc.batch.postalcodes.listeners;

import com.kyc.batch.postalcodes.model.PostalCodeRawRecord;
import com.kyc.batch.postalcodes.model.PostalCodeWrapper;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;

import static com.kyc.core.constants.BatchConstants.BATCH_PARTIAL_COMPLETED;

@AllArgsConstructor
public class LoadPostalCodesStepListener extends StepListenerSupport<PostalCodeRawRecord, PostalCodeWrapper> {

    private int skipLimit;

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        if(stepExecution.getSkipCount()==0){
            return ExitStatus.COMPLETED;
        }
        else if(stepExecution.getSkipCount()<=skipLimit){
            return new ExitStatus(BATCH_PARTIAL_COMPLETED);
        }
        return super.afterStep(stepExecution);
    }
}
