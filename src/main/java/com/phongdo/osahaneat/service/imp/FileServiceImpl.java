package com.phongdo.osahaneat.service.imp;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service
public class FileServiceImpl implements com.phongdo.osahaneat.service.FileService {

    @Value("${application.bucket.name}")
    private String bucketName;
    @Value("${cloud.aws.endpointUrl}")
    private String endpointUrl;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file){
        String fileUrl = "";
        File fileObj = convertMultiPartFileToFile(file);
        String fileName = System.currentTimeMillis()+"_" + file.getOriginalFilename();
        fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
        s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        fileObj.delete();
        return fileUrl;
    }



    public boolean deleteFile(String fileName){
        boolean isSuccess = false;
        try {
            s3Client.deleteObject(bucketName,fileName);
            isSuccess = true;
        }catch (Exception e){
            log.error("Can not delete file" + e);
        }
        return isSuccess;
    }

    private File convertMultiPartFileToFile(MultipartFile file){
        File convertFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertFile)){
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertFile;
    }


    @Value("${fileUpload.rootPath}")
    private String rootPath;

    private Path root;

    private void init() {
        try {
            root = Paths.get(rootPath);

            if (Files.notExists(root)) {
                Files.createDirectories(root);
            }
        } catch (Exception e) {
            System.out.println("Error create folder root" + e);
        }
    }

    @Override
    public boolean saveFile(MultipartFile file) {
        try {
            init();
            Files.copy(
                    file.getInputStream(),
                    this.root.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            System.out.println("Error save file" + e);
            return false;
        }
    }

    @Override
    public Resource loadFile(String fileName) {
        try {
            init();
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            log.info("load file");
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (Exception e) {
            System.out.println("Error load file" + e);
        }
        return null;
    }
}
