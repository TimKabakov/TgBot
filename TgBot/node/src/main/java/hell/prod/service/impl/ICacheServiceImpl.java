package hell.prod.service.impl;

import com.hazelcast.cache.impl.CacheService;
import com.hazelcast.map.IMap;
import hell.prod.entity.City;
import hell.prod.service.ICacheService;
import org.springframework.stereotype.Service;

@Service
public class ICacheServiceImpl implements ICacheService {




    @Override
    public void addToCache(String name, City city) {


    }

    @Override
    public void removeFromCache(String name) {

    }

    @Override
    public IMap<String, City> getCache() {
        return null;
    }
}
