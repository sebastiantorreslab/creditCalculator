package com.app.creditCalculator.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.util.Optional;
import java.util.OptionalDouble;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection ="credits")
public class Credit  {

    @MongoId
    @EqualsAndHashCode.Include
    private String id;

    @NotNull
    @NotBlank
    private String creditType;
    private Double interestRate;
    private Double amount;
    private Integer loanTerm;
    private Double payment;
    private Double futureValue;
    private Double interestValue;


}
