import org.springframework.boot.SpringBootVersion;

public class demo {
    public static void main(String[] args) {
        String version = SpringBootVersion.getVersion();
        System.out.println("版本号是" + version);

    }
}
