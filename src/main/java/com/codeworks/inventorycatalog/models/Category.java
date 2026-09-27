package com.codeworks.inventorycatalog.models;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class Category {
    private Integer id;
    private String name;
}
