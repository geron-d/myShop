package by.it.academy.dtos.requests.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDecreaseRequest {

    @NotNull
    Long productId;

    int amount;

}
