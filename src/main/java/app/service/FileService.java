package app.service;

import app.entity.Employee;
import app.exception.EmptyFileException;
import app.exception.StoreFileException;
import app.utility.CsvReader;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FileService {

    private final String fileLocation = System.getProperty("user.dir") + "/files";

    public void storeFile(MultipartFile file) {

        Path path = Paths.get(fileLocation);
        CsvReader.fileName = LocalDateTime.now().toString() + "-" + file.getOriginalFilename();
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            InputStream fileInputStream = file.getInputStream();
            Files.copy(fileInputStream, path.resolve(CsvReader.fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new StoreFileException("Exception occurred in storing file");
        }
    }

    public List<Employee> readFile() {
        File file = new File(fileLocation + "/" + CsvReader.fileName);
        CsvMapper mapper = new CsvMapper();
        List<Employee> employees = null;
        try {
            CsvSchema schema = mapper.schemaFor(Employee.class)
                    .withSkipFirstDataRow(true)
                    .withColumnSeparator(',').withoutQuoteChar();
            MappingIterator<Employee> personIter = mapper.readerFor(Employee.class).with(schema).readValues(file);

            employees = personIter.readAll();

        } catch (IOException ex) {
            throw new EmptyFileException("File with given name not found");
        }

        return employees;
    }

}

