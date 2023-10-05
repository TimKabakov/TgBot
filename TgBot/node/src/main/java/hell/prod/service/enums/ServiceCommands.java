package hell.prod.service.enums;

public enum ServiceCommands {
    HELP("/help"),
    WEATHER("/weather"),
    MONEY("/money"),
    START("/start");
    private final String cmd;

    ServiceCommands(String cmd) {
        this.cmd= cmd;
    }

    @Override
    public String toString(){
        return cmd;
    }

    public boolean equals(String cmd){
        return this.toString().equals(cmd);
    }
}
