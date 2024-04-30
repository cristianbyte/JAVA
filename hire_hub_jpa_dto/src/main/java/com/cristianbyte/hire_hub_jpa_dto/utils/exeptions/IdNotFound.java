package com.cristianbyte.hire_hub_jpa_dto.utils.exeptions;

public class IdNotFound extends RuntimeException  {
    private static final String ERR_MSG = "Id not found.";
    public IdNotFound(String nameEntity){
        super(String.format(ERR_MSG, nameEntity));
    }
}
