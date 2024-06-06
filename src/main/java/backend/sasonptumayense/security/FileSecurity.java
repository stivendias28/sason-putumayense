package backend.sasonptumayense.security;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.Controllers.FileController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class FileSecurity {
    private final FileController fileController;

    @GetMapping(value = "/{path}/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String path, @PathVariable String fileName) {
        return fileController.getFile(path, fileName);
    }

}
