package com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database.repositories;

import com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database.model.Tbaz111Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface Tbaz111_Cliente extends JpaRepository<Tbaz111Model, Long> {
}
