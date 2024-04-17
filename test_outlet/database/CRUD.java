package database;

import java.util.List;

public interface CRUD {
    public Object create(Object object);
    default List read() {
        return null;
    }
    public boolean update(Object object);
    public boolean delete(Object object);
    public Object findById(int id);
}
