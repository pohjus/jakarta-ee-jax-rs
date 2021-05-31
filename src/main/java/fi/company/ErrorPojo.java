package fi.company;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorPojo {
    private String errorMsg;

    public ErrorPojo(String msg) {
        this.errorMsg = msg;
    }

    public ErrorPojo() {

    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String msg) {
        this.errorMsg = msg;
    }
}
