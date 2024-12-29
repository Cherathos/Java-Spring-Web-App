package com.deneme.weeb.furniture;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.util.Objects;

public record Furniture(@Id Integer id,
                        @NotEmpty @Positive Integer price,
                        @NotEmpty @Positive Integer quantity,
                        @NotEmpty String furniturename,
                        String furnituretype,
                        String description,
                        String image,
                        @Version
                        Integer version) {

}
