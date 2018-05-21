package action;

import dao.relationDAO;
import pojo.Relation;

import java.util.List;

public class relationAction {
    Relation relation;
    List<Relation> relationList;
    relationDAO relationdao;

    public void setRelationdao(relationDAO relationdao) {
        this.relationdao = relationdao;
    }

    public relationDAO getRelationdao() {
        return relationdao;
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
        relationList=relationdao.list();
        return "listrelation";
    }
}
