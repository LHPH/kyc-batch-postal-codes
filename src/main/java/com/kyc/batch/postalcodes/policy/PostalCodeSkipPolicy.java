package com.kyc.batch.postalcodes.policy;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipPolicy;

@AllArgsConstructor
public class PostalCodeSkipPolicy implements SkipPolicy {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostalCodeSkipPolicy.class);

    private int skipLimit;

    @Override
    public boolean shouldSkip(Throwable throwable, int i) {

        LOGGER.warn("Skipping element {} due {}",i,throwable.getMessage());
        return i<=skipLimit;
    }
}
