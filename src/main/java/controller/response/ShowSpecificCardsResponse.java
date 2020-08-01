package controller.response;

import java.util.ArrayList;

public class ShowSpecificCardsResponse extends Response {


    private ArrayList<String> names;
    private String group;
    private String panelName;


    public ShowSpecificCardsResponse(ArrayList<String> names,String group,String panelName) {
        this.names = names;
        this.group=group;
        this.panelName=panelName;
    }


    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }
}
