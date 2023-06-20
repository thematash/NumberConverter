package com.cflox.challenge.numberconverter.audit;

import com.cflox.challenge.numberconverter.common.enums.DataFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "audit")
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime timestamp;
    private String inputNumber;
    private DataFormat inputFormat;
    private DataFormat outputFormat;
    private String result;
}
