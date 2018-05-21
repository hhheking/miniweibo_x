package service;

import pojo.Userinfo;

import java.util.List;

public interface userinfoService {
    public List<Userinfo> list();
    public void add(Userinfo userinfo);
    public void delete(Userinfo userinfo);
    public void update(Userinfo userinfo);
    public Userinfo get(int id);
}
