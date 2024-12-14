package com.guilherme.fiapfood.api.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CategoryRequestDTO {
    private String name;
}
