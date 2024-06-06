package backend.sasonptumayense.request;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementosMenuRequest {
    String name;
    String description;
    String categoriasElementosId;
    BigDecimal price;
    MultipartFile photo;
}
