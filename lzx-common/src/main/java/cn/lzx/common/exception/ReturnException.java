package cn.lzx.common.exception;

/**
 * 统一自定义异常处理
 *
 * @author lzx
 * @since 2022-06-12
 */
public class ReturnException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public ReturnException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ReturnException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ReturnException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ReturnException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
