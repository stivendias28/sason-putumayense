package backend.sasonptumayense.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import backend.sasonptumayense.model.ElementosMenu;
import backend.sasonptumayense.model.MenuElemento;
import backend.sasonptumayense.request.ElementosMenuRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.response.DynamicResponseErrors;
import backend.sasonptumayense.service.ElementosMenuService;
import backend.sasonptumayense.service.MenuElementoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ElementosMenuController {
    private final ElementosMenuService elementosMenuService;
    private final FileController fileController;
    private final MenuElementoService menuElementoService;

    public ResponseEntity<ApiResponse> getAllElementosMenu() {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", elementosMenuService.getAll()), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> saveElementosMenu(@ModelAttribute ElementosMenuRequest request, @RequestParam("photo") MultipartFile photo) {
        DynamicResponseErrors obj = new DynamicResponseErrors();

        if(request == null) obj.addError("request", "Request is required");
        
        if(request.getName() == null || request.getName().isEmpty()) obj.addError("name", "Name is required");
        
        if(request.getDescription() == null || request.getDescription().isEmpty()) obj.addError("description", "Description is required");

        if(request.getCategoriasElementosId() == null || request.getCategoriasElementosId().isEmpty()) obj.addError("categoriasElementosId", "CategoriasElementos is required");

        if(request.getPrice() == null) obj.addError("price", "Price is required");

        if(photo == null) obj.addError("photo", "Photo is required");

        if(obj.hasErrors()) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "An error has occurred", obj.getErrors()), HttpStatus.BAD_REQUEST);

        if(elementosMenuService.getByName(request.getName().toLowerCase()) != null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "Name already exists", null), HttpStatus.BAD_REQUEST);

        ResponseEntity<ApiResponse> response = fileController.uploadFile(photo, UUID.randomUUID().toString(), "elements");

        if(response.getStatusCode() != HttpStatus.OK) return response;

        ElementosMenu elementosMenu = elementosMenuService.save(request, response.getBody().getData().toString());

        
        if(elementosMenu != null){
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", elementosMenu), HttpStatus.OK);
        }else{
            fileController.deleteFile(response.getBody().getData().toString());
            return new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error", null), HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    public ResponseEntity<ApiResponse> deleteElementosMenu(Integer id) {
        List<MenuElemento> menuElemento = menuElementoService.findByElementosMenuId(id);

        if(menuElemento.size() > 0) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.CONFLICT, "You cannot delete the item because it is being used in one or more menus", null), HttpStatus.CONFLICT);
        ElementosMenu elementosMenu = elementosMenuService.getById(id);


        if(elementosMenu != null) {
            fileController.deleteFile(elementosMenu.getPhotoElemento());
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Deleted", elementosMenuService.deleteElementosMenu(id)), HttpStatus.OK);
        }else{
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.NOT_FOUND, "ElementosMenu not found", null), HttpStatus.NOT_FOUND);
        }
    }
}
