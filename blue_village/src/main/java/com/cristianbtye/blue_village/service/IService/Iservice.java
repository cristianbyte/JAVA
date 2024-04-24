package com.cristianbtye.blue_village.service.IService;

import java.util.List;

import com.cristianbtye.blue_village.entity.BluEvent;

public interface Iservice {
    public List<BluEvent> getAll();
    public List<BluEvent> search(String name);

    public BluEvent save(BluEvent bluEvent);
    public BluEvent update(BluEvent bluEvent);
    public boolean delete(Long id);
    
    public BluEvent findById(Long id);
}
