package smu.db_project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "SPENDING")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"student", "category"})
public class Spending {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spending_seq")
    @SequenceGenerator(name = "spending_seq", sequenceName = "SPENDING_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "S_NUM", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Min(0)
    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @Temporal(TemporalType.DATE)
    @Column(name = "SPEND_DATE", nullable = false)
    private Date spendDate;
}