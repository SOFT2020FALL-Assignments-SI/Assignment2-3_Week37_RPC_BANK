package dk.mf.rpc.bank.service.files;

import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class Encoder implements Serializable {
    //Original Code Source: https://devqa.io/encode-decode-json-byte-array/
    public byte[] encodeJSONFileToByteArray(String filename) throws IOException {
        File file = new File(filename);
        byte[] bytesArray = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray);
        fis.close();
        return bytesArray;
    }

    public String encodeJSONFile(String filename) throws IOException {
        return DatatypeConverter.printBase64Binary(encodeJSONFileToByteArray(filename));
    }
}
