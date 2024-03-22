package database;

import java.util.List;

public interface CRUDBook extends CRUD{
    public List<Object> findAll(String idAuthor);
}
