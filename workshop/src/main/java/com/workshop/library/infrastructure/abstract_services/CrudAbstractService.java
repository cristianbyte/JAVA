package com.workshop.library.infrastructure.abstract_services;

public interface CrudAbstractService<RQ, RS, ID> {
    RS getById(ID id);
    RS create(RQ request);
    RS update(ID id, RQ request);
    void delete(ID id);
}