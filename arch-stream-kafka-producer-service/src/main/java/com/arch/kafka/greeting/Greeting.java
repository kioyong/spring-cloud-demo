package com.arch.kafka.greeting;

import com.arch.kafka.HasId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Greeting implements HasId {
    @Id
    private String id;
    private String name;
    private String type;
    private Double point;
    private BigDecimal rate;
}
