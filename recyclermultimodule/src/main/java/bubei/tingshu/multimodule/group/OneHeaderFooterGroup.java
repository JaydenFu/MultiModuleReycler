package bubei.tingshu.multimodule.group;

/**
 * Created by xiaojian.fu on 2017/8/10.
 */

public  class OneHeaderFooterGroup extends Group {

    public OneHeaderFooterGroup(int dataCount, GroupChildManager groupChildManager) {
        super(dataCount, groupChildManager);
    }

    @Override
    public int getHeaderCount() {
        return 1;
    }

    @Override
    public int getFooterCount() {
        return 1;
    }
}
