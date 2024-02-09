package es.miw.tfm.backend.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(SystemResource.SYSTEM)
public class SystemResource {
    static final String SYSTEM = "/system";

    static final String APP_INFO = "/app-info";
    static final String VERSION_BADGE = "/version-badge";

    @Value("${application.name}")
    private String applicationName;
    @Value("${build.version}")
    private String buildVersion;
    @Value("${build.timestamp}")
    private String buildTimestamp;

    @GetMapping(value = VERSION_BADGE, produces = {"image/svg+xml"}) // http://localhost:8080/system/version-badge
    public Mono<byte[]> generateBadge() { // http://localhost:8080/system/badge
        return Mono.just(new Badge().generateBadge("Renderrrrr", "v" + buildVersion).getBytes());
    }

    @GetMapping(value = APP_INFO) // http://localhost:8080/system/app-info
    public Mono<String> applicationInfo() {
        return Mono.just("{\"version\":\"" + this.applicationName + "::" +
                this.buildVersion + "::" + this.buildTimestamp + "\"}");
    }
}
