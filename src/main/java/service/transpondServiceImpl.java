package service;

import dao.transpondDAO;
import pojo.Transpond;
import pojo.User;

import java.util.ArrayList;
import java.util.List;

public class transpondServiceImpl implements transpondService {
    transpondDAO transponddao;
    @Override
    //得到该用户转发的所有数据
    public List<Transpond> listMyTranspond(User user) {
        List<Transpond> list=new ArrayList<>();
        for(Transpond transpond:transponddao.list()){
            if(transpond.getUserByUserId().getUserId()==user.getUserId()){
                list.add(transpond);
            }
        }
        return list;
    }

    @Override
    //在主页删除自己转发的微博
    public void delete(Transpond transpond) {
        transponddao.delete(transpond);
    }

    @Override
    public void add(Transpond transpond) {
        transponddao.add(transpond);
    }

    @Override
    //得到某个转发的数据
    public Transpond get(int id) {
        return (Transpond)transponddao.get(id);
    }
}
