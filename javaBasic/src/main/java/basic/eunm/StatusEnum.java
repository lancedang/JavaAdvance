package basic.eunm;

public enum StatusEnum {

    UP(1,"开启"),
    DOWN(0, "关闭");

    //私有 final 常亮，标记enum实例不可变，否则有违enum枚举语义
    private final int flag;
    private final String desc;

    StatusEnum(int flag, String desc) {
        this.flag = flag;
        this.desc = desc;
    }

    //对枚举实例，只提供getter，不提供setter,否则有违枚举语义
    public int getFlag() {
        return flag;
    }


    public String getDesc() {
        return desc;
    }


    @Override
    public String toString() {
        return "StatusEnum{" +
                "flag=" + flag +
                ", desc='" + desc + '\'' +
                '}';
    }
}
