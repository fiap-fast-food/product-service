package com.guilherme.fiapfood.api.response;


import com.guilherme.fiapfood.api.request.CategoryRequestDTO;

public record ProductResponseDTO(
                                     String id,
                                     String name,
                                     Double price,
                                     String information,
                                     CategoryRequestDTO category) {
}
