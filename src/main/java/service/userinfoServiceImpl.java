package service;

import dao.userinfoDAO;
import pojo.Userinfo;

import java.util.List;

public class userinfoServiceImpl implements userinfoService {
    userinfoDAO userinfodao;

    public userinfoDAO getUserinfodao() {
        return userinfodao;
    }

    public void setUserinfodao(userinfoDAO userinfodao) {
        this.userinfodao = userinfodao;
    }

    @Override
    public List<Userinfo> listAll() {
        return userinfodao.list();
    }

    @Override
    public void add(Userinfo userinfo) {
        userinfodao.add(userinfo);
    }

    @Override
    public void delete(Userinfo userinfo) {
        userinfodao.delete(userinfo);
    }
}
