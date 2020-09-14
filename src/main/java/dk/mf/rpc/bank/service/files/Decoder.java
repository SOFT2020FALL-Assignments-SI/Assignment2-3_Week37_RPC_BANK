package dk.mf.rpc.bank.service.files;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;

public class Decoder implements Serializable {
    //Original Code Source: https://devqa.io/encode-decode-json-byte-array/
    public byte[] decodeJSONFileFromString(String jsonFile){
        return DatatypeConverter.parseBase64Binary(jsonFile);
    }
    public String decodeJSONFile(String filename){
        return new String(decodeJSONFileFromString(filename));
    }
}
