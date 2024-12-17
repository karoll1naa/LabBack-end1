package main.labbackend1.Controllers;
import main.labbackend1.Models.Record;
import main.labbackend1.Repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordRepository recordRepository;

    @GetMapping("/{recordId}")
    public Record getRecord(@PathVariable Long recordId) {
        Optional<Record> record = recordRepository.findById(recordId);
        if (record.isPresent()) {
            return record.get();
        } else {
            throw new IllegalArgumentException("Record not found");
        }
    }

    @DeleteMapping("/{recordId}")
    public void deleteRecord(@PathVariable Long recordId) {
        recordRepository.deleteById(recordId);
    }

    @PostMapping
    public Record createRecord(@RequestBody Record record) {
        if (record.getAmount() == null || record.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        return recordRepository.save(record);
    }

    @GetMapping
    public List<Record> getRecords(@RequestParam(required = false) Long userId,
                                   @RequestParam(required = false) Long categoryId) {
        return recordRepository.findAll();
    }
}

