package smu.db_project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "BUDGET_ALERT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"sNum", "categoryName"})

public class BudgetAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_alert_seq")
    @SequenceGenerator(name = "budget_alert_seq", sequenceName = "BUDGET_ALERT_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "S_NUM", nullable = false)
    private Student sNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_NAME", nullable = false)
    private Category categoryName;

    @Column(name = "TOTAL_SPENDING", nullable = false)
    private Integer totalSpending = 0;

    @Column(name = "OVERAMOUNT")
    private Integer overamount = 0;

    @Temporal(TemporalType.DATE)
    @Column(name = "ALERT_DATE", nullable = false)
    private Date alertDate;
}