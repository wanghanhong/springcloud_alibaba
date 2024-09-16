package wlw.smart.fire.common.domain;

import java.util.HashMap;

/**
 * @author Pano
 */
public class FebsResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public FebsResponse code(int code) {
        this.put("Code", code);
        return this;
    }

    public FebsResponse message(String message) {
        this.put("Message", message);
        return this;
    }

    public FebsResponse data(Object data) {
        this.put("Data", data);
        return this;
    }

    @Override
    public FebsResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
