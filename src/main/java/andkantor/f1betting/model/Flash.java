package andkantor.f1betting.model;

public class Flash {

    private String message = "";

    public String popMessage() {
        String returnMessage = message;
        message = "";
        return returnMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean hasMessage() {
        return message.length() > 0;
    }
}
