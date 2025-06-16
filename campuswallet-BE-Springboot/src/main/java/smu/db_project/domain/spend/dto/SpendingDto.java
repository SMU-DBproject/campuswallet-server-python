package smu.db_project.domain.spend.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpendingDto {
    private Long id;
    private Long sNum;
    private String categoryName;
    private Integer amount;
    private Date spendDate;
}
