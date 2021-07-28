package data;

import java.util.List;

public class Resource_DataUser extends Resource {

    private List<DataUser> data;

    public Resource_DataUser(){
        super();
    }

    public Resource_DataUser(Integer page, Integer per_page, Integer total, Integer total_pages, List<DataUser> data, Support support) {
        super(page, per_page, total, total_pages, support);
        this.data = data;
    }

    public List<DataUser> getData() {
        return data;
    }

    public void setData(List<DataUser> data) {
        this.data = data;
    }
}
