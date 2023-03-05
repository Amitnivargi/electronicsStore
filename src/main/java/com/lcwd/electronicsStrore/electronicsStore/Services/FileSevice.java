package com.lcwd.electronicsStrore.electronicsStore.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileSevice {
     String uploadFile(MultipartFile file,String path) throws IOException;

     InputStream getResource(String path,String name) throws FileNotFoundException;

}
