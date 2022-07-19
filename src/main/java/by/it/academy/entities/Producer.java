package by.it.academy.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The {@code Producer} class represents the producer of product.
 *
 * @author Maxim Zhevnov
 * @since 1.0
 */
@Entity
@Table(name = "producer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producer {

    /**
     * An identifier of {@code Producer}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * A name of the producer of product.
     */
    @Column(name = "producer")
    private String producer;

    /**
     * Initializes a newly created {@code Producer} object so that it represents
     * the name of producer
     *
     * @param producer A {@code String}
     */
    public Producer(String producer) {
        this.producer = producer;
    }
}
