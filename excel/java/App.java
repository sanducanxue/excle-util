import com.lkx.util.ExcelUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    private static final double RATE = 0.65;//匹配率
    private static Map<String, List<String>> map = new HashMap<String, List<String>>();

    public static void main(String[] args) throws Exception {
        init();
        outPut();
    }

    private static void outPut() throws Exception {
        ArrayList<Target> list = new ArrayList<Target>();

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            Target target = new Target();
            target.setOldValue(key);

            String newV = "";
            for (String s : value) {
                newV = newV + s +"---";
            }
            target.setNewValue(newV);
            list.add(target);
        }
        ExcelUtil.exportExcel("D://target.xlsx",list,Target.class);
    }

    private static void init() throws Exception {
        List<Demo> list1 = ExcelUtil.readXls("D://old.xlsx", Demo.class);
        List<Demo> list2 = ExcelUtil.readXls("D://new.xlsx", Demo.class);

        for (Demo oldDemo : list1) {
            String oldDemoValue = oldDemo.getValue();
            for (Demo newDemo : list2) {
                String newDemoValue = newDemo.getValue();
                boolean match = match(oldDemoValue, newDemoValue);
                if (match) {
                    //匹配上了
                    if (map.containsKey(oldDemoValue)) {
                        List<String> list = map.get(oldDemoValue);
                        list.add(newDemoValue);
                    } else {
                        ArrayList<String> list = new ArrayList<String>();
                        list.add(newDemoValue);
                        map.put(oldDemoValue, list);
                    }
                }
            }
        }
    }

    //匹配
    private static boolean match(String value1, String value2) {
        double num = 0;
        char[] oldChar = value1.toCharArray();
        char[] newChar = value2.toCharArray();
        String str = "关于印发的指导意见通知规定办法";
        char[] chars = str.toCharArray();
        ArrayList<String> list = new ArrayList<String>();
        for (char aChar : chars) {
            list.add(aChar+ "");
        }

        for (char oldc : oldChar) {
            if (list.contains(oldc + "")){
                continue;
            }
            for (int i = 0; i < newChar.length; i++) {
                if (newChar[i] != '0' && oldc == newChar[i]) {
                    num++;
                    newChar[i] = '0';
                }
            }
        }
        double rate = num / newChar.length;
        if (rate > RATE) {
            return true;
        } else {
            return false;
        }

    }
}

