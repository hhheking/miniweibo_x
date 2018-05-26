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
}
