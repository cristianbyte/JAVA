package com.cristianbyte.beautyhub.infraestructure.abstract_service;

import org.springframework.data.domain.Page;

import com.cristianbyte.beautyhub.utils.enums.SortType;

public interface CRUDService<RQ, RS, ID> {
    public RS create(RQ request);

    public RS get(ID id);

    public RS update(RQ request, ID id);

    public void delete(ID id);

    public Page<RS> getAll(int page, int size, SortType sort);
} 
