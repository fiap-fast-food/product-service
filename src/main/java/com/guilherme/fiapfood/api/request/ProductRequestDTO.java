package com.guilherme.fiapfood.api.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductRequestDTO {

    private String name;
    private Double price;
    private String information;
    private CategoryRequestDTO category;
}
