package com.felipestanzani.migrationdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(@NotBlank String name,
                             @NotNull @Positive Double price) {
}
