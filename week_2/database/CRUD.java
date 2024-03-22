package database;

import java.util.List;

public interface CRUD {
    // Object it's a generic class for Java Objects.
    public Object create(Object object);
    public Object query(int id);
    public boolean update(Object object);

    public boolean delete(Object object);
    public List<Object> findAll();

    Object findById(int id);
}
