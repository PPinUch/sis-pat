package br.com.navita.commons;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "Mensageria - RESP")
public class Mensagem {
    
    public static final String PADRAO_DATA = "yyyy-MM-dd - HH:mm:ss.SSS";

    private String msg;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Mensagem(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonbTransient
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getTimestampFormat() {
        return timestamp.format(DateTimeFormatter.ofPattern(PADRAO_DATA));
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "["+getTimestampFormat()+"] - MSG:["+msg+"]";
    }

    

}
