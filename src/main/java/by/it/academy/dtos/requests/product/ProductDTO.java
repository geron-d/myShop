package by.it.academy.dtos.requests.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * The {@code ProductDTO} dto represents the {@code Product} class.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    /**
     * A name of category of product.
     */
    @NotBlank
    private String category;

    /**
     * A name of type of product.
     */
    @NotBlank
    private String type;

    /**
     * A name of product.
     */
    @NotBlank
    private String name;

    /**
     * A path where stored an image of product.
     */
    private String imagePath;

    /**
     * A date where product inserted.
     */
    private LocalDate dateInserting = LocalDate.now();

    /**
     * A name of producer of product.
     */
    @NotBlank
    private String producer;

    /**
     * Amount of product.
     */
    private int amount;

    /**
     * Price of product.
     */
    private double price;

}
