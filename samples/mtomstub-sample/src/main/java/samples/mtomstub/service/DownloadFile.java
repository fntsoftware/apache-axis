package samples.mtomstub.service;

import jakarta.jws.WebService;
import jakarta.xml.ws.soap.MTOM;

@WebService
@MTOM
public interface DownloadFile {
    ResponseDownloadFile getFile() throws Exception;
}
