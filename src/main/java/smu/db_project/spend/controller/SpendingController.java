// spend/controller/SpendingController.java
package smu.db_project.spend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.db_project.spend.dto.SpendingDto;
import smu.db_project.spend.service.SpendingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spend")
public class SpendingController {

    private final SpendingService spendingService;

    @GetMapping("/spendings")
    public ResponseEntity<List<SpendingDto>> getSpendingsByStudent(@RequestParam Long studentId) {
        return ResponseEntity.ok(spendingService.getSpendingByStudent(studentId));
    }

    @PostMapping("/add")
    public ResponseEntity<SpendingDto> addSpending(@RequestBody SpendingDto dto) {
        return ResponseEntity.ok(spendingService.addSpending(dto));
    }

    @PatchMapping("/fix")
    public ResponseEntity<SpendingDto> updateSpending(@RequestParam Long id,
                                                      @RequestBody SpendingDto dto) {
        return ResponseEntity.ok(spendingService.updateSpending(id, dto));
    }

    @DeleteMapping("/del")
    public ResponseEntity<Void> deleteSpending(@RequestParam Long id) {
        spendingService.deleteSpending(id);
        return ResponseEntity.noContent().build();
    }
}
