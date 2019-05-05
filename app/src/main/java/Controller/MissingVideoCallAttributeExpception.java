package Controller;

import java.io.IOException;

public class MissingVideoCallAttributeExpception extends VideoCallException {
    public MissingVideoCallAttributeExpception(String msg){
        super(msg);
    }
}
