package smu.db_project.domain.spend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.db_project.domain.spend.dto.SpendingDto;
import smu.db_project.domain.spend.service.SpendingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/spend")
public class SpendingController {

    private final SpendingService spendingService;

    @GetMapping("/student/{sNum}")
    public ResponseEntity<List<SpendingDto>> getSpendingsByStudent(@PathVariable Long sNum) {
        return ResponseEntity.ok(spendingService.getSpendingsByStudent(sNum));
    }

    @PostMapping("/add")
    public ResponseEntity<SpendingDto> addSpending(@RequestBody SpendingDto dto) {
        return ResponseEntity.ok(spendingService.addSpending(dto));
    }

    @PatchMapping("/fix")
    public ResponseEntity<SpendingDto> updateSpending(@RequestParam Long id, @RequestBody SpendingDto dto) {
        return ResponseEntity.ok(spendingService.updateSpending(id, dto));
    }

    @DeleteMapping("/del")
    public ResponseEntity<Void> deleteSpending(@RequestParam Long id) {
        spendingService.deleteSpending(id);
        return ResponseEntity.noContent().build();
    }
}
