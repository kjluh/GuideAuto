package example.guideauto.controller;

import example.guideauto.entity.Transport;
import example.guideauto.entity.enums.Category;
import example.guideauto.entity.enums.Types;
import example.guideauto.service.TransportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auto")
public class TransportController {
    private TransportService service;

    public TransportController(TransportService service) {
        this.service = service;
    }

    @Operation(
            summary = "Посмотреть весь транспорт",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )
    @GetMapping()
    public ResponseEntity<List<Transport>> findTransport() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Добавление транспорта в базу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "тс с таким гос номеров уже в базе или введен некорректный гос номер"
                    )
            }
    )
    @PostMapping()
    public ResponseEntity save(Transport transport) {
        if (service.saveInBase(transport)) {
            return ResponseEntity.ok(transport);
        }
        return ResponseEntity.status(401).body("тс с таким гос номеров уже в базе или введен некорректный гос номер");
    }

    @Operation(
            summary = "Изменить транспорт",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
    )
    @PatchMapping()
    public ResponseEntity<Transport> patch(Transport transport) {
        return ResponseEntity.ok(service.patchTransport(transport));
    }

    @Operation(
            summary = "найти транспорт по ГОС номеру",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "415",//&&&&&&
                            description = "Транспорт не найден"
                    )
            }
    )
    @GetMapping("/GOSN")
    public ResponseEntity<Transport> findTransportGOSNumber(String gosNumber) {
        return ResponseEntity.ok(service.findTransportGOSNumber(gosNumber));
    }

    @Operation(
            summary = "Поиск транспорта по нескольким параметрам параметрам",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )

    )
    @GetMapping("model&category")
    public ResponseEntity<List<Transport>> findTransportModelAndCategory(@RequestParam(required = false) String model,
                                                                         @RequestParam(required = false) Category category) {
        return ResponseEntity.ok(service.findTransportModelAndCategory(model, category));
    }
@Operation(
        summary = "Поиск транспорта по марке авто",
        responses = @ApiResponse(
                responseCode = "200",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
)
    @GetMapping("mark")
    public ResponseEntity<List<Transport>> findTransportMark(@RequestParam(required = false) String mark) {
        return ResponseEntity.ok(service.findTransportMark(mark));
    }

    @Operation(
            summary = "Поиск транспорта по модели авто",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    )
    @GetMapping("model")
    public ResponseEntity<List<Transport>> findTransportModel(String model) {
        return ResponseEntity.ok(service.findTransportModel(model));
    }

    @Operation(
            summary = "Поиск транспорта по категории",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    )
    @GetMapping("category")
    public ResponseEntity<List<Transport>> findTransportCategory(Category category) {
        return ResponseEntity.ok(service.findTransportCategory(category));
    }

    @Operation(
            summary = "Поиск транспорта по типу авто",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    )
    @GetMapping("type")
    public ResponseEntity<List<Transport>> findTransportType(Types type) {
        return ResponseEntity.ok(service.findTransportType(type));
    }

    @Operation(
            summary = "Поиск транспорта по году производства авто",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    )
    @GetMapping("year")
    public ResponseEntity<List<Transport>> findTransportYear(int year) {
        return ResponseEntity.ok(service.findTransportYear(year));
    }

    @Operation(
            summary = "Поиск транспорта по модели авто",
            responses = @ApiResponse(
                    responseCode = "200",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    )
    @GetMapping("trl")
    public ResponseEntity<List<Transport>> findTransportTrailer(boolean availabilityTrailer) {
        return ResponseEntity.ok(service.findTransportTrailer(availabilityTrailer));
    }

    @Operation(
            summary = "Удаление транспорта по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "транспорт удален"),
                    @ApiResponse(responseCode = "401", description = "такого транспорта нет в базе")
            }
    )
    @DeleteMapping()
    public ResponseEntity delete(Long id) {
        if (service.delete(id)) {
            return ResponseEntity.ok().body("транспорт удален");
        }
        return ResponseEntity.status(401).body("такого транспорта нет в базе");
    }
}
