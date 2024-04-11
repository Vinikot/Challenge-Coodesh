package com.example;

import com.example.task4.model.EmployeeModel;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.json.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Create an implementation of a Rest API client.
 * Prints out how many records exists for each gender and save this file to s3 bucket
 * API endpoint=> https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda 
 * AWS s3 bucket => interview-digiage
 *
 */
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
                    frd.setDate(dataNome.getInt("date"));
                    frd.setName(dataNome.getString("name"));

                    feriado.add(frd);
                }

            }
        }

        return feriado;
    }
}