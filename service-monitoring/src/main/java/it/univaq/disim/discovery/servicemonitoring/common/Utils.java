package it.univaq.disim.discovery.servicemonitoring.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Utils {

    public static byte[] zip(List<String> srcFiles) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ZipOutputStream zipOut = new ZipOutputStream(out);
        for (String srcFile : srcFiles) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
            new File(srcFile).delete();
        }
        zipOut.close();
        out.close();
        return out.toByteArray();
    }

    public static List<String> toStrings(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> lines = reader.lines().collect(Collectors.toList());
        reader.close();
        inputStream.close();
        return lines;
    }

    public static <T> void writeObjToFile(File inputFile, T obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String inputJson = mapper.writeValueAsString(obj);
        FileUtils.writeStringToFile(inputFile, inputJson, Charset.defaultCharset());
    }
}
