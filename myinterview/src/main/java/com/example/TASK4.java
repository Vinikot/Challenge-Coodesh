package com.example;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Region;
import com.example.task4.aws.AWSClientConfig;
import com.example.task4.model.EmployeeModel;
import com.example.task4.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Create an implementation of a Rest API client.
 * Prints out how many records exists for each gender and save this file to s3 bucket
 * API endpoint=> https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda
 * AWS s3 bucket => interview-digiage
 */
@RequiredArgsConstructor
public class TASK4 {

    public static ArrayList<EmployeeModel> getEmplyeeFromAPI() throws ClientProtocolException, IOException {

        ArrayList<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();

        HttpGet request = new HttpGet("https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda");

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();

             CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {

                String result = EntityUtils.toString(entity);

                JSONArray payload = new JSONArray(result);

                for (int i = 0; i < payload.length(); ++i) {

                    EmployeeModel employee = new EmployeeModel();

                    JSONObject dataNome = payload.getJSONObject(i);
                    employee.setEmp_no(dataNome.getInt("emp_no"));
                    employee.setFirst_name(dataNome.getString("first_name"));
                    employee.setLast_name(dataNome.getString("last_name"));
                    employee.setGender(dataNome.getString("gender"));

                    employeeList.add(employee);
                }

            }
        }

        return employeeList;
    }

    public static void countRecords() throws IOException {

        int maleEmpNo = 0;
        int femaleEmpNo = 0;

        String path = "myinterview/src/main/java/com/example/task4/records/EmployeesRecordsPerGender.txt";
        Path filePath = Paths.get(path);

        ArrayList<EmployeeModel> employeeList = getEmplyeeFromAPI();

        for (EmployeeModel emp : employeeList) {
            if (emp.getGender().equals("M")) {
                maleEmpNo++;
            } else if (emp.getGender().equals("F")) {
                femaleEmpNo++;
            }
        }

        ArrayList<String> lines = new ArrayList<>();
        lines.add("Male records: " + maleEmpNo);
        lines.add("Female records: " + femaleEmpNo);

        System.out.println(lines.get(0) + "\n" + lines.get(1));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] fileContent = Files.readAllBytes(Paths.get(filePath.toUri()));

        MultipartFile file = new MockMultipartFile("file", String.valueOf(filePath), "text/plain", fileContent);

        String accessKey = "AKIAU7BHLOLBKPZTHAP2";
        String secretAccessKey = "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";
        String bucketName = "interview-digiage";
        final Region region = Region.US_West_2;

        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretAccessKey);

        AmazonS3 amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(region.toString())
                .build();

        File localFile = convertMultipartFileToFile(file);

        try {
            amazonS3.putObject(new PutObjectRequest(bucketName, file.getOriginalFilename(), localFile));
            System.out.println("The file was uploaded successfully!");
        } catch (AmazonS3Exception e) {
            System.err.println("File upload failed: " + e.getMessage());
        }

    }

    public static File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try {
            Files.copy(file.getInputStream(), convertedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convertedFile;
    }

}