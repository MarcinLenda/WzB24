package pl.lenda.marcin.wzb.service.upload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Promar on 11.12.2016.
 */
@Service
public class UploadFile {



    public ResponseEntity uploadPhoto(MultipartFile file){

        if (!file.isEmpty()) {
            try {
                String mimeType = file.getContentType();
                String filename = file.getOriginalFilename();
                String path = "";
                byte[] bytes = file.getBytes();
                File serverFile = new File("C:\\Users\\Promar\\Desktop\\WzB\\src\\main\\resources\\static" + "\\"  + filename);

                path = "/images/"+filename;

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                return new ResponseEntity<>("{}", HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("{}", HttpStatus.OK);
        }
    }
}
