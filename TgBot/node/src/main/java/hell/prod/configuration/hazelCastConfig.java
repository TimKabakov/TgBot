package hell.prod.configuration;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import hell.prod.entity.City;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class hazelCastConfig {

    @Bean
    public IMap<String, City> citiesMap(@Qualifier("hazelcastInstance") HazelcastInstance hazelcastInstance){
        return hazelcastInstance.getMap("citiesMap");
    }
}
