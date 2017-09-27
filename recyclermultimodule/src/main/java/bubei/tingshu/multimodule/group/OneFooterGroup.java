package bubei.tingshu.multimodule.group;

/**
 * Created by xiaojian.fu on 2017/8/10.
 */

public class OneFooterGroup extends Group {

    public OneFooterGroup(int dataCount, OneFooterGroupChildManager groupChildManager) {
        super(dataCount, groupChildManager);
    }

    @Override
    public int getFooterCount() {
        return 1;
    }
}
