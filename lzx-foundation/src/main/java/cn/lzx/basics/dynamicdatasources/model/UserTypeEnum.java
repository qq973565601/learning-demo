package cn.lzx.basics.dynamicdatasources.model;

public enum UserTypeEnum {
    /**
     * 未成年人用户
     */
    UNDER_AGE(1),
    /**
     * 成年人用户
     */
    ADULT(2),
    /**
     * 老年人用户
     */
    ELDER(3);

    private final int code;

    UserTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
