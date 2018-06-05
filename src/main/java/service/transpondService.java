package service;

import pojo.Transpond;
import pojo.User;

import java.util.List;

public interface transpondService {
    public List<Transpond> listMyTranspond(User user);
    public void delete(Transpond transpond);
    public void add(Transpond transpond);
    public Transpond get(int id);
    public void add(int messid,int reslut_meddid);
}
