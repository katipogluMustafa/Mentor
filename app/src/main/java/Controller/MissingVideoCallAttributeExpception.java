package Controller;

import java.io.IOException;

public class MissingVideoCallAttributeExpception extends IOException {
    public MissingVideoCallAttributeExpception(String msg){
        super(msg);
    }
}
