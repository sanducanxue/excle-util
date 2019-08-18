import com.lkx.util.Excel;

public class Demo {
    @Excel(title = "value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
