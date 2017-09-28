package client;

public class RequestBody {
    private Object object;
    private DataType type;

    public RequestBody(Object object, DataType type) {
        this.object = object;
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }
}
