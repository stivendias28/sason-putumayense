package backend.sasonptumayense.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.MenuElemento;
import backend.sasonptumayense.request.MenuElementoRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.response.DynamicResponseErrors;
import backend.sasonptumayense.service.ElementosMenuService;
import backend.sasonptumayense.service.MenuElementoService;
import backend.sasonptumayense.service.MenusService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenuElementoController {
    private final MenuElementoService menuElementoService;
    private final MenusService menusService;
    private final ElementosMenuService elementosMenuService;

    public ResponseEntity<ApiResponse> getAllMenuElementos(@RequestParam(name = "menuId", required = false) String menuId, @RequestParam(name = "elementoId", required = false) String elementoId){
        if((menuId != null && !menuId.isEmpty()) && (elementoId != null && !elementoId.isEmpty())){
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", menuElementoService.findByMenusIdAndElementosMenuId(Integer.parseInt(menuId), Integer.parseInt(elementoId)))
                , HttpStatus.OK);
        }

        if(menuId != null && !menuId.isEmpty()){
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", menuElementoService.findByMenusId(Integer.parseInt(menuId)))
                , HttpStatus.OK);
        }

        if(elementoId != null && !elementoId.isEmpty()){
            return new ResponseEntity<ApiResponse>(
                new ApiResponse(HttpStatus.OK, "OK", menuElementoService.findByElementosMenuId(Integer.parseInt(elementoId)))
                , HttpStatus.OK);
        }
        
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", menuElementoService.getAllMenuElementos()), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> createMenuElemento(MenuElementoRequest request){
        DynamicResponseErrors obj = new DynamicResponseErrors();

        if(request == null) obj.addError("request", "Request is required");

        if(request.getMenuId() == null || request.getMenuId().isEmpty()) obj.addError("menuId", "MenuId is required");
        if(request.getElementoMenuId() == null || request.getElementoMenuId().isEmpty()) obj.addError("elementoMenuId", "ElementoMenuId is required");

        if(obj.hasErrors()) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "An error has occurred", obj.getErrors()), HttpStatus.BAD_REQUEST);

        if(menusService.getMenusById(Integer.parseInt(request.getMenuId())) == null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "Menu not found", null), HttpStatus.BAD_REQUEST);
        if(elementosMenuService.getById(Integer.parseInt(request.getElementoMenuId())) == null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "ElementosMenu not found", null), HttpStatus.BAD_REQUEST);

        if(menuElementoService.findByMenusIdAndElementosMenuId(Integer.parseInt(request.getMenuId()), Integer.parseInt(request.getElementoMenuId())) != null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "MenuElemento with this menuId and elementoMenuId already exists", null), HttpStatus.BAD_REQUEST);

        MenuElemento menuElemento = menuElementoService.createMenuElemento(request);
        
        return (menuElemento != null) ? 
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", menuElemento), HttpStatus.OK) 
            : new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer id){
        MenuElemento menuElemento = menuElementoService.findById(id);

        if(menuElemento == null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "MenuElemento not found", null), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Deleted", menuElementoService.deleteById(id)), HttpStatus.OK);
    }

}
