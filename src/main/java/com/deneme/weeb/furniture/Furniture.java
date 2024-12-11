package com.deneme.weeb.furniture;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

public record Furniture(int furnitureID, @NotEmpty @Positive int furniturePrice, @NotEmpty @Positive int furnitureQuantity, @NotEmpty String furnitureName, String furnitureType, String furnitureDescription, String furnitureImage) {

}
