package smu.db_project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "DISCOUNT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"category"})
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_seq")
    @SequenceGenerator(name = "discount_seq", sequenceName = "DISCOUNT_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_NAME", nullable = false)
    private Category category;

    @Column(name = "DISCOUNT_NAME", length = 100, nullable = false)
    private String discountName;

    @Min(0)
    @Max(100)
    @Column(name = "RATE", nullable = false)
    private Integer rate;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;
}
