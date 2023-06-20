package com.cflox.challenge.numberconverter.audit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;

import static com.cflox.challenge.numberconverter.common.enums.DataFormat.DECIMAL;
import static com.cflox.challenge.numberconverter.common.enums.DataFormat.ROMAN;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuditServiceTest {

    @Autowired
    private AuditService auditService;

    @Autowired
    private AuditPersistence auditPersistence;

    @Test
    public void testAuditEntitySaved() {
        auditService.saveAuditRecord("100", DECIMAL, ROMAN, "C");

        Iterable<AuditEntity> auditEntityList = auditPersistence.findAll();
        Iterator<AuditEntity> iterator = auditEntityList.iterator();

        assertTrue(iterator.hasNext());

        AuditEntity entity = iterator.next();
        assertNotNull(entity.getId());
        assertEquals(entity.getInputNumber(), "100");
        assertEquals(entity.getInputFormat(), DECIMAL);
        assertEquals(entity.getOutputFormat(), ROMAN);
        assertEquals(entity.getResult(), "C");
    }
}
