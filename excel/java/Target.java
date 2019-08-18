import com.lkx.util.Excel;

public class Target {
    @Excel(title = "old")
    private String oldValue;
    @Excel(title = "new")
    private String newValue;

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
