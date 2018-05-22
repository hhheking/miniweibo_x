package action;

import com.opensymphony.xwork2.ActionContext;
import pojo.Relation;
import pojo.User;
import service.relationService;

import java.util.List;
import java.util.Map;

public class relationAction {
    Relation relation;
    List<Relation> relationList;
    relationService relationservice;
    int idols;
    int fans;

    public int getFans() {
        return fans;
    }

    public int getIdols() {
        return idols;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public void setIdols(int idols) {
        this.idols = idols;
    }

    public relationService getRelationservice() {
        return relationservice;
    }

    public void setRelationservice(relationService relationservice) {
        this.relationservice = relationservice;
    }

    public List<Relation> getRelationList() {
        return relationList;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public void setRelationList(List<Relation> relationList) {
        this.relationList = relationList;
    }
    public String list(){
        relationList=relationservice.list();
        return "listrelation";
    }

    public String per(){
        relationList=relationservice.list();
        //获取登录用户的javabean
        Map<String, Object> session = ActionContext.getContext().getSession();
        User u=(User)session.get("user");
        idols = fans =0;
        for(Relation relation:relationList){
            System.out.println(relation.getUserByUserByid().getUserId());
            System.out.println(u.getUserId());
            System.out.println(u.getUserNikename());
            if(relation.getUserByUserId().getUserId() == u.getUserId()){
                idols++;
            }
            if(relation.getUserByUserByid().getUserId() == u.getUserId()){
                fans++;
            }
        }
        return "home";
    }
}
