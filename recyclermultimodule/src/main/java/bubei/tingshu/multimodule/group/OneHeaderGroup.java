package bubei.tingshu.multimodule.group;

/**
 * Created by xiaojian.fu on 2017/8/10.
 */

public class OneHeaderGroup extends Group {

    public OneHeaderGroup(int dataCount, OneHeaderGroupChildManager groupChildManager) {
        super(dataCount, groupChildManager);
    }

    @Override
    public int getHeaderCount() {
        return 1;
    }
}
