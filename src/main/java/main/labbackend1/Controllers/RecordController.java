package main.labbackend1.Controllers;

import main.labbackend1.Models.Record;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/record")
public class RecordController {
    private List<Record> records = new ArrayList<>();

    @GetMapping("/{recordId}")
    public Record getRecord(@PathVariable Long recordId) {
        return records.stream()
                .filter(record -> record.getId().equals(recordId))
                .findFirst()
                .orElse(null);
    }

    @DeleteMapping("/{recordId}")
    public void deleteRecord(@PathVariable Long recordId) {
        records.removeIf(record -> record.getId().equals(recordId));
    }

    @PostMapping
    public void createRecord(@RequestBody Record record) {
        records.add(record);
    }

    @GetMapping
    public List<Record> getRecords(@RequestParam(required = false) Long userId,
                                   @RequestParam(required = false) Long categoryId) {
        if (userId == null && categoryId == null) {
            throw new IllegalArgumentException("UserId or CategoryId must be provided");
        }

        return records.stream()
                .filter(record -> (userId == null || record.getUserId().equals(userId)) &&
                        (categoryId == null || record.getCategoryId().equals(categoryId)))
                .collect(Collectors.toList());
    }
}
