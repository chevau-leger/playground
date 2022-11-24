package lvd.json;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: SI-TECH</p>
 *
 * @author lvdong
 * @version 1.0
 * @since 2022/6/23
 */
public class JsonUtil {

    public static void main(String[] args) {

        System.out.println("32-2,32-3,32-5,32-7".replace("-", ":"));

        StringBuilder diskGroupsStr = new StringBuilder();
        List<List> usedDisksGroups = JSONObject.parseArray("[[\"32-6\",\"32-7\"],[\"32-9\",\"32-10\"]]", List.class);
        for (int i = 0; i < usedDisksGroups.size(); i++) {

            List usedDisksGroup = usedDisksGroups.get(i);
            String groupStr = usedDisksGroup.toString().replace("[", "").replace("]", "")
                    .replace("-", ":").replace(" ", "");
            if (i != 0) {

                diskGroupsStr.append(",");
            }
            diskGroupsStr.append(groupStr);
        }
        System.out.println("diskGroupStr = " + diskGroupsStr);
    }
}
