package backend.sasonptumayense.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.SaboresPostres;
import backend.sasonptumayense.request.SaboresPostresRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.service.SaboresPostresService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SaboresPostresController {
    private final SaboresPostresService saboresPostresService;

    public List<SaboresPostres> getAllSaboresPostres() {
        return saboresPostresService.getAllSaboresPostres();
    }

    public SaboresPostres getSaboresPostresById(Integer id) {
        SaboresPostres saboresPostres = saboresPostresService.getSaboresPostresById(id);
        return (saboresPostres != null) ? saboresPostres : null;
    }

    public ResponseEntity<ApiResponse> saveSaboresPostres(SaboresPostresRequest request) {
        if(request == null || request.getName() == null || request.getName().isEmpty()) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "Name is required", null), HttpStatus.BAD_REQUEST);

        SaboresPostres validate = saboresPostresService.getSaboresPostresByName(request.getName());
        if(validate != null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "Name already exists", null), HttpStatus.BAD_REQUEST);

        SaboresPostres saboresPostres = saboresPostresService.saveSaboresPostres(request);
        return (saboresPostres != null) ? 
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", saboresPostres), HttpStatus.OK) 
            : new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
