package backend.sasonptumayense.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.ElementoSabor;
import backend.sasonptumayense.request.ElementoSaborRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.response.DynamicResponseErrors;
import backend.sasonptumayense.service.ElementoSaborService;
import backend.sasonptumayense.service.ElementosMenuService;
import backend.sasonptumayense.service.SaboresPostresService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ElementoSaborController {
    private final ElementoSaborService elementoSaborService;
    private final ElementosMenuService elementosMenuService;
    private final SaboresPostresService saboresPostresService;
    
    public ResponseEntity<ApiResponse> getAllElementoSabor(
        @RequestParam(name = "saborId", required = false) String saborId, 
        @RequestParam(name = "elementoId", required = false) String elementoId){
            
        if((saborId != null && !saborId.isEmpty()) && (elementoId != null && !elementoId.isEmpty())) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", elementoSaborService.findByElementoIdAndSaborId(Integer.parseInt(elementoId), Integer.parseInt(saborId)))
                , HttpStatus.OK);
        }
        
        if(saborId != null && !saborId.isEmpty()) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", elementoSaborService.findBySaborId(Integer.parseInt(saborId)))
                , HttpStatus.OK);
        }
        
        if(elementoId != null && !elementoId.isEmpty()) {
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", elementoSaborService.findByElementoId(Integer.parseInt(elementoId)))
                , HttpStatus.OK);
        }
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", elementoSaborService.getAllElementosSabores()), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> createElementoSabor(ElementoSaborRequest request){
        DynamicResponseErrors obj = new DynamicResponseErrors();
        
        if(request == null) obj.addError("request", "Request is required");
        
        if(request.getElementoId() == null || request.getElementoId().isEmpty()) obj.addError("elementoId", "ElementoId is required");
        
        if(request.getSaborId() == null || request.getSaborId().isEmpty()) obj.addError("saborId", "SaborId is required");

        if(elementosMenuService.getById(Integer.parseInt(request.getElementoId())) == null) obj.addError("elementoId", "ElementoId not found");

        if(saboresPostresService.getSaboresPostresById(Integer.parseInt(request.getSaborId())) == null) obj.addError("saborId", "SaborId not found");
        
        if(obj.hasErrors()) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "An error has occurred", obj.getErrors()), HttpStatus.BAD_REQUEST);

        if(elementoSaborService.findByElementoIdAndSaborId(Integer.parseInt(request.getElementoId()), Integer.parseInt(request.getSaborId())) != null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "ElementoSabor with this elementoId and saborId already exists", null), HttpStatus.BAD_REQUEST);

        ElementoSabor elementoSabor = elementoSaborService.createElementoSabor(request);
        return (elementoSabor != null) ? 
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", elementoSabor), HttpStatus.OK) 
            : new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}
