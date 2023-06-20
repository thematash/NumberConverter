package com.cflox.challenge.numberconverter.audit;

import com.cflox.challenge.numberconverter.common.enums.DataFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AuditService {

    private final AuditPersistence auditPersistence;

    public void saveAuditRecord(String inputNum, DataFormat inputFormat, DataFormat outputFormat, String result) {
        AuditEntity auditEntity = new AuditEntity();

        auditEntity.setTimestamp(LocalDateTime.now());
        auditEntity.setInputNumber(inputNum);
        auditEntity.setInputFormat(inputFormat);
        auditEntity.setOutputFormat(outputFormat);
        auditEntity.setResult(result);

        auditPersistence.save(auditEntity);
    }
}
