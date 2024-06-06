package backend.sasonptumayense.response;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DynamicResponseErrors {
    @Builder.Default
    private Map<String, Object> errors = new HashMap<>();

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void addError(String key, String errorMessage) {
        errors.put(key, errorMessage);
    }
}
