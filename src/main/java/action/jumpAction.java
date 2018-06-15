package action;

public class jumpAction {
    String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String jump(){
        System.out.println(param);

        return "success";
    }
}
