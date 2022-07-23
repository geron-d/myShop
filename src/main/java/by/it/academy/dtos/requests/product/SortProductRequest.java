package by.it.academy.dtos.requests.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SortProductRequest {

    List<String> categories;

    List<String> types;

    List<String> producers;

}
