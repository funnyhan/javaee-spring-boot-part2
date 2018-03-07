package funny.springmvc4.domain;

/**
 * @Author:hanchengke
 * @Description:
 * @Date:Created in 9:50 2018/3/5
 */
public class DemoObj {
    private Long id;
    private String name;

    public DemoObj() {  //jackson对对象和json做转换时一定需要此空构造
    }

    public DemoObj(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
