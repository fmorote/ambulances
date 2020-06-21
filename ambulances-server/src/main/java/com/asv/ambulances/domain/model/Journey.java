package com.asv.ambulances.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Builder
@ToString
public class Journey {
    @NonNull
    private final String id;
    @NonNull
    private final BigInteger people;
}
