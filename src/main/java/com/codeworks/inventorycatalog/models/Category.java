package com.codeworks.inventorycatalog.models;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
@Builder
public class Category {
    private Integer id;
    private String name;
}
