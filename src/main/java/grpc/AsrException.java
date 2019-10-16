package grpc;


import com.ficus.inference.speech.rpcdata.AsrStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AsrException extends  Exception {
    AsrStatus status;
    public AsrException(AsrStatus status) {
        super(status.getCode() + " " + status.getInfo());
        this.status = status;
    }

    public AsrStatus getStatus() {
        return status;
    }

    static public void checkAsrStatusAndThrowException(AsrStatus status) throws AsrException {

        if(status.getCode() != AsrStatus.Code.SUCCESS){
            log.error(status.toString());
            throw new AsrException(status);
        }
    }
}
