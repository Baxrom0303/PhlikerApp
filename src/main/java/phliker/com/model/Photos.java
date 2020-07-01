package phliker.com.model;
import java.util.List;
/**
 * Created by nsarvar on 2/16/18.
 */
public class Photos {

    /*
    modify this class. Think about how it might look. It is depend on JSON response structure
    */

    private Integer page;
    private Integer pages;
    private Integer perpage;
    private String total;
    private List<Photo> photo = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public void setPerpage(Integer perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(List<Photo> photo) {
        this.photo = photo;
    }
}