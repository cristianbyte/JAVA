package com.cristianbyte.learnify.infraestructure.abstract_service;

import org.springframework.data.domain.Page;

public interface CrudAbstractService<RQ, RS, ID> {
    Page<RS> getAll(int page, int size);
    RS getById(ID id);
    RS create(RQ request);
    RS update(ID id, RQ request);
    void delete(ID id);
}
