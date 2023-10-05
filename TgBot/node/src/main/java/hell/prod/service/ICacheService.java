package hell.prod.service;

import com.hazelcast.map.IMap;
import hell.prod.entity.City;

public interface ICacheService {
    void addToCache(String name, City city);
    void removeFromCache(String name);
    IMap<String, City> getCache();
}
