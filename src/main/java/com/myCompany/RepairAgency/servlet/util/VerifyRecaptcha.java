package com.myCompany.RepairAgency.servlet.util;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ResourceBundle;

public class VerifyRecaptcha {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("recaptcha");

    public static final String url = "https://www.google.com/recaptcha/api/siteverify";
    public static final String secret = resourceBundle.getString("secret");
    private static final Logger logger = LogManager.getLogger(VerifyRecaptcha.class);
    private final static String USER_AGENT = "Mozilla/5.0";

    public static boolean verify(String gRecaptchaResponse) {

        if (gRecaptchaResponse == null || gRecaptchaResponse.isBlank()) {
            logger.debug("[VerifyRecaptcha]  for test purpose, change true to false");
            return true;
            /////// for test purpose, change true to false

        }

        try {
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            // add request header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String postParams = "secret=" + secret + "&response="
                    + gRecaptchaResponse;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            logger.debug("[VerifyRecaptcha] Sending 'POST' request to URL : " + url);
//            logger.debug("[VerifyRecaptcha] Post parameters : " + postParams);
            logger.debug("[VerifyRecaptcha] Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            logger.debug("[VerifyRecaptcha] " + response);

            //parse JSON response and return 'success' value
            JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            return jsonObject.getBoolean("success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}