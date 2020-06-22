package app.service;

import app.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

     List<Employee> readFile();

     void storeFile(MultipartFile file);

}
