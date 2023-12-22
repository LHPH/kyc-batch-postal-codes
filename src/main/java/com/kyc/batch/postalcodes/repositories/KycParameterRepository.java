package com.kyc.batch.postalcodes.repositories;

import com.kyc.batch.postalcodes.entity.KycParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface KycParameterRepository extends JpaRepository<KycParameter,Long> {

    @Modifying
    @Query(name = "KycParameter.updatePostalCodeParameter")
    int updatePostalCodeParameter();
}
