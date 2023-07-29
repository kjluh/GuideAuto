package example.guideauto.controller;

import example.guideauto.entity.Transport;
import example.guideauto.entity.enums.Category;
import example.guideauto.entity.enums.Types;
import example.guideauto.service.TransportService;
import example.guideauto.service.impl.TransportServiceIMPL;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auto")
public class TransportController {
    private TransportService service;

    public TransportController(TransportService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<Transport>> findTransport() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping()
    public ResponseEntity save(Transport transport) {
        if (service.saveInBase(transport)) {
            return ResponseEntity.ok(transport);
        }
        return ResponseEntity.status(401).body("тс с таким гос номеров уже в базе или введен некорректный гос номер");
    }

    @PatchMapping()
    public ResponseEntity<Transport> patch(Transport transport) {
        return ResponseEntity.ok(service.patchTransport(transport));
    }

    @GetMapping("/GOSN")
    public ResponseEntity<Transport> findTransportGOSNumber(String gosNumber) {
        return ResponseEntity.ok(service.findTransportGOSNumber(gosNumber));
    }

    @GetMapping("model&category")
    public ResponseEntity<List<Transport>> findTransportModelAndCategory(@RequestParam(required = false) String model,
                                                                         @RequestParam(required = false) Category category) {
        return ResponseEntity.ok(service.findTransportModelAndCategory(model, category));
    }

    @GetMapping("mark")
    public ResponseEntity<List<Transport>> findTransportMark(@RequestParam(required = false) String mark) {
        return ResponseEntity.ok(service.findTransportMark(mark));
    }

    @GetMapping("model")
    public ResponseEntity<List<Transport>> findTransportModel(String model) {
        return ResponseEntity.ok(service.findTransportModel(model));
    }

    @GetMapping("category")
    public ResponseEntity<List<Transport>> findTransportCategory(Category category) {
        return ResponseEntity.ok(service.findTransportCategory(category));
    }

    @GetMapping("type")
    public ResponseEntity<List<Transport>> findTransportType(Types type) {
        return ResponseEntity.ok(service.findTransportType(type));
    }

    @GetMapping("year")
    public ResponseEntity<List<Transport>> findTransportYear(int year) {
        return ResponseEntity.ok(service.findTransportYear(year));
    }

    @GetMapping("trl")
    public ResponseEntity<List<Transport>> findTransportTrailer(boolean availabilityTrailer) {
        return ResponseEntity.ok(service.findTransportTrailer(availabilityTrailer));
    }

    @DeleteMapping()
    public ResponseEntity delete(Long id) {
        if (service.delete(id)) {
            return ResponseEntity.ok().body("транспорт удален");
        }
        return ResponseEntity.status(401).body("такого транспорта нет в базе");
    }
}
