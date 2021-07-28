package data;

import java.util.List;

public class Resource_DataResource extends Resource {

    private List<DataResource> data;

    public Resource_DataResource(){
        super();
    }

    public Resource_DataResource(Integer page, Integer per_page, Integer total, Integer total_pages, List<DataResource> data, Support support) {
        super(page, per_page, total, total_pages, support);
        this.data = data;
    }

    public List<DataResource> getData() {
        return data;
    }

    public void setData(List<DataResource> data) {
        this.data = data;
    }
}
