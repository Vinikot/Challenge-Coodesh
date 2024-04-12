package com.example;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.Region;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * A very basic program that demonstrates the use of JUnit tests. The tests include a sample unit test and an
 * integration test.
 */
public class HelloApp {

    static int DEFAULT_TIMES = 3;

    static int EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD = 2;
    static int EXIT_STATUS_HELLO_FAILED = 4;

    /**
     * The main method of this program.
     *
     * @param args Arguments passed to this program.
     */
    public static void main(String[] args) throws IOException {

        int times = DEFAULT_TIMES;
        if (args.length >= 1) {
            try {
                times = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("I don't understand the parameter you passed me. Is it a number? " +
                        "Parameter was: [" + args[0] + "]");
                System.exit(EXIT_STATUS_PARAMETER_NOT_UNDERSTOOD);
            }
        }

        Hello hi = new Hello();
        try {
            hi.setTimes(times);
        } catch (IllegalArgumentException e) {
            System.err.println("Something went wrong: " + e.getMessage());
            System.exit(EXIT_STATUS_HELLO_FAILED);
        }
        hi.sayHello(System.out);

        //TASK1.isPalindrome();
        TASK4.countRecords();

    }
}
