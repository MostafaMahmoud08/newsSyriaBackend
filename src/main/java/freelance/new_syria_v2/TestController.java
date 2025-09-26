package freelance.new_syria_v2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
public class TestController {

	private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/my-ip")
    public String getServerIp() {
        log.info("🚀 hellllllllllllo"); // استخدم Logger بدل println
        try {
            URL url = new URL("https://api.ipify.org");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String ip = br.readLine();
            return "Server public IP: " + ip;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
