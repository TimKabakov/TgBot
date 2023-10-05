package hell.prod.properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integration.weather")
@Data
public class WeatherIntegrationProperties {


    private Integer connectTimeout;
    private Integer readTimeout;
    private Integer writeTimeout;

}
