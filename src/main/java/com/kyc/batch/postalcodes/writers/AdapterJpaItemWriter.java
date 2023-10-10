package com.kyc.batch.postalcodes.writers;

import com.kyc.batch.postalcodes.model.PostalCodeWrapper;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdapterJpaItemWriter<T> implements ItemWriter<PostalCodeWrapper> {

    private final JpaItemWriter<T> jpaItemWriter;
    private final Function<PostalCodeWrapper,T> getter;

    public AdapterJpaItemWriter(Function<PostalCodeWrapper,T> getter, EntityManagerFactory emf){
        jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(emf);
        this.getter = getter;
    }

    @Override
    public void write(List<? extends PostalCodeWrapper> items) throws Exception {

        List<T> listMainData = items.stream()
                .map(this.getter)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        jpaItemWriter.write(listMainData);
    }
}
