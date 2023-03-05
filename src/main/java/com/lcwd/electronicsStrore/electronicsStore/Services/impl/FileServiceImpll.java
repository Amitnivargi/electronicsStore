package com.lcwd.electronicsStrore.electronicsStore.Services.impl;

import com.lcwd.electronicsStrore.electronicsStore.Exceptions.BadApiRequest;
import com.lcwd.electronicsStrore.electronicsStore.Services.FileSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpll implements FileSevice {

    private Logger logger= LoggerFactory.getLogger(FileServiceImpll.class);

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
  // MultipartFile file - containing file
        // path where we can upload the file
        String orginalFilename=file.getOriginalFilename();
        logger.info("FileName:{}",orginalFilename);

        String filename= UUID.randomUUID().toString(); // generate random file name in string

        String extension=orginalFilename.substring(orginalFilename.lastIndexOf("."));

        String filenamewithExtension=filename+extension;

        String fullPathWithFileName=path+filenamewithExtension;

        if(extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {

            File folder=new File(path);
            if(!folder.exists()){
                //create new folder
                folder.mkdirs();
            }
            // upload file
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return filenamewithExtension;
        } else {
            throw new BadApiRequest("file with"+extension+"not allowed!!");
        }

    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
         String fullPath=path+File.separator+name;
         InputStream inputStream=new FileInputStream(fullPath);

         return inputStream;


     }
}
