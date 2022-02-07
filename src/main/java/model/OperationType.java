package model;

public enum OperationType {
    ADD("C"),
    GET("D");

    private final String type;

    OperationType(String name) {
        this.type = name;
    }

    public String getType() {
        return type;
    }
}
