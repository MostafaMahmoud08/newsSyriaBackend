package freelance.new_syria_v2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
public class TestController {

    @GetMapping("/my-ip")
    public String getServerIp() {
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
