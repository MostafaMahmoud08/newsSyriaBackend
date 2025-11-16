package freelance.new_syria_v2.auth.publicendpoints;

import freelance.new_syria_v2.auth.annotaions.IsPublic;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PublicEndpointRegistry {

    private final List<String> publicPatterns;

    public PublicEndpointRegistry(RequestMappingHandlerMapping mapping) {

        this.publicPatterns = mapping.getHandlerMethods().entrySet().stream()
                .filter(entry -> entry.getValue()
                        .getMethod().isAnnotationPresent(IsPublic.class))
                .map(entry -> extractPatterns(entry.getKey()))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println("📌 Public Endpoints Loaded = " + publicPatterns);
    }

    private List<String> extractPatterns(RequestMappingInfo info) {

        // 🟢 Spring Boot 3.2+ — PathPatterns
        if (info.getPathPatternsCondition() != null) {
            return info.getPathPatternsCondition()
                    .getPatterns()
                    .stream()
                    .map(p -> p.getPatternString())
                    .collect(Collectors.toList());
        }

        // 🔵 Fallback (optional)
        if (info.getPatternsCondition() != null) {
            return info.getPatternsCondition()
                    .getPatterns()
                    .stream()
                    .collect(Collectors.toList());
        }

        return List.of();
    }

    public String[] getPublicPatterns() {
        return publicPatterns.toArray(new String[0]);
    }
}
