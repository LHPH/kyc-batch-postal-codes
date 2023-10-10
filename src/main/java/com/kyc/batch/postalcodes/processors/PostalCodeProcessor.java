package com.kyc.batch.postalcodes.processors;

import com.kyc.batch.postalcodes.entity.KycNeighborhoodData;
import com.kyc.batch.postalcodes.entity.KycPostalCodeMainData;
import com.kyc.batch.postalcodes.model.PostalCodeRawRecord;
import com.kyc.batch.postalcodes.model.PostalCodeWrapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.Set;

public class PostalCodeProcessor implements ItemProcessor<PostalCodeRawRecord, PostalCodeWrapper>{

    private static final Logger LOGGER = LoggerFactory.getLogger(PostalCodeProcessor.class);
    private final Set<Integer> postalCodes = new HashSet<>();

    @Value("#{jobParameters['run.id']}")
    private Long versionExecution;

    @Override
    public PostalCodeWrapper process(PostalCodeRawRecord item) {

        LOGGER.info("{}",item);
        Integer postalCode = NumberUtils.toInt(item.getPostalCode());
        PostalCodeWrapper wrapper = new PostalCodeWrapper();
        if(!postalCodes.contains(postalCode)){

            KycPostalCodeMainData mainData = KycPostalCodeMainData.builder()
                    .postalCode(item.getPostalCode())
                    .idCity(Long.parseLong(item.getIdTown()))
                    .city(item.getTown())
                    .idState(Long.parseLong(item.getIdState()))
                    .state(item.getState())
                    .version(versionExecution)
                    .build();
            wrapper.setMainData(mainData);
            postalCodes.add(postalCode);
        }

        KycNeighborhoodData neighborhoodData = KycNeighborhoodData.builder()
                .postalCode(item.getPostalCode())
                .idNeighborhood(Long.parseLong(item.getIdNeighborhood()))
                .neighborhood(item.getNeighborhood())
                .version(versionExecution)
                .build();
        wrapper.setNeighborhoodData(neighborhoodData);

        return wrapper;
    }
}
