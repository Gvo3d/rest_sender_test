package template;

public class DataHolder {
    private Object data;
    private DataType type;
    private Object response;

    public DataHolder(Object data, DataType type) {
        this.data = data;
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
