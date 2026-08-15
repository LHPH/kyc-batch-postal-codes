package com.kyc.batch.postalcodes.writers;

import com.kyc.batch.postalcodes.model.PostalCodeWrapper;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.database.JpaItemWriter;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdapterJpaItemWriter<T> implements ItemWriter<PostalCodeWrapper> {

    private final JpaItemWriter<T> jpaItemWriter;
    private final Function<PostalCodeWrapper,T> getter;

    public AdapterJpaItemWriter(Function<PostalCodeWrapper,T> getter, EntityManagerFactory emf){
        jpaItemWriter = new JpaItemWriter<>(emf);
        this.getter = getter;
    }

    @Override
    public void write(Chunk<? extends PostalCodeWrapper> chunk) throws Exception {

        List<? extends PostalCodeWrapper> items = chunk.getItems();
        List<T> listMainData = items.stream()
                .map(this.getter)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        jpaItemWriter.write(new Chunk<>(listMainData));
    }
}
