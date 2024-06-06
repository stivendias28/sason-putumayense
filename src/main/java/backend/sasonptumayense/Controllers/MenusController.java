package backend.sasonptumayense.Controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import backend.sasonptumayense.model.Menus;
import backend.sasonptumayense.request.MenusRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.response.DynamicResponseErrors;
import backend.sasonptumayense.service.MenusService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenusController {
    private final MenusService menusService;
    private final FileController fileController;

    public ResponseEntity<ApiResponse> getAllMenus() {
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", menusService.getAllMenus()), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> getMenusById(Integer id) {
        Menus menus = menusService.getMenusById(id);
        return (menus != null) ?
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", menus), HttpStatus.OK) :
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.NOT_FOUND, "Menus not found", null), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ApiResponse> saveMenus(@ModelAttribute MenusRequest request, @RequestParam("photo") MultipartFile photo) {
        DynamicResponseErrors obj = new DynamicResponseErrors();

        if (request == null) obj.addError("menus", "Menus is required");

        if (request.getName() == null || request.getName().isEmpty()) obj.addError("name", "Name is required");

        if (request.getDescription() == null || request.getDescription().isEmpty()) obj.addError("description", "Description is required");

        if (request.getPhoto() == null || request.getPhoto().isEmpty()) obj.addError("photo", "Photo is required");

        if (obj.hasErrors()) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "An error has occurred", obj.getErrors()), HttpStatus.BAD_REQUEST);

        if(menusService.getMenusByName(request.getName().toLowerCase()) != null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "Name already exists", null), HttpStatus.BAD_REQUEST);

        ResponseEntity<ApiResponse> response = fileController.uploadFile(photo, UUID.randomUUID().toString(), "menus");

        if(response.getStatusCode() != HttpStatus.OK) return response;

        Menus menus = menusService.createMenus(request, response.getBody().getData().toString());

        if(menus != null) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", menus), HttpStatus.OK);
        } else {
            fileController.deleteFile(response.getBody().getData().toString());
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "An error has occurred", null), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ApiResponse> deleteMenus(Integer id) {
        Menus menus = menusService.getMenusById(id);
        if(menus != null) {
            fileController.deleteFile(menus.getPhoto_menu());
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Deleted", menusService.deleteMenus(id)), HttpStatus.OK);
        } else {
            return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.NOT_FOUND, "Menus not found", null), HttpStatus.NOT_FOUND);
        }
    }

}
