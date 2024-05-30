package com.cristianbyte.learnify.api.mapper;

import java.util.List;

import org.springframework.data.domain.Page;

public interface GenericMapper<RQ, RS, EN> {
    EN requestToEntity(RQ request);
    RS entityToResponse(EN entity);
    List<RS> entityToResponse(List<EN> entities);
    default Page<RS> entityToResponse(Page<EN> entitiesPage){return entitiesPage.map(data -> entityToResponse(data));};
}
