package service;

import pojo.Userinfo;

import java.util.List;

public interface userinfoService {
    public List<Userinfo> listAll();
    public void add(Userinfo userinfo);
    public void delete(Userinfo userinfo);
}
