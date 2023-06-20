package com.cflox.challenge.numberconverter.audit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditPersistence extends CrudRepository<AuditEntity, Long> {
}
