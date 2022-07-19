package by.it.academy.dtos.requests.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank
    private String category;

    @NotBlank
    private String type;

    @NotBlank
    private String name;

    private String imagePath;

    private LocalDate dateInserting = LocalDate.now();

    @NotBlank
    private String producer;

    private int amount;

    private double price;

}
