package dao;

import pojo.Transpond;

import java.util.List;

public interface transpondDAO {
    public List<Transpond> list();
    public void add(Transpond transpond);
    public void delete(Transpond transpond);
    public Transpond get(int id);
    public Transpond findTranspondFrom(int result_id);
}
