package com.booking.common;

import com.mastercard.developer.signers.HttpsUrlConnectionSigner;
import com.mastercard.developer.utils.AuthenticationUtils;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import javax.net.ssl.HttpsURLConnection;

public class MastercardConnectionExample {
    public static void main(String[] args) {
        try {
            // 1. Define your Mastercard API credentials
            String consumerKey = "_KAfZng1Sg5B3XJBmNxl4ufozkfWOyI8FSXEVqXh93d7444f!cd5a10f3075843c29a19513469cf315b0000000000000000";
            String pkcs12KeyFilePath = "/Users/akp/Documents/akp/projects/java/booking-platform/catalog-service/src/main/resources/mc_sandbox_key/akp-test-sandbox-signing.p12";
            String keyAlias = "akp--key"; 
            String keyPassword = "akp-pass";

            // 2. Load the private signing key (.p12 file)
            PrivateKey signingKey = AuthenticationUtils.loadSigningKey(
                pkcs12KeyFilePath, 
                keyAlias, 
                keyPassword
            );
            System.out.println("Signing key = " + signingKey);

            // 3. Prepare the HTTP request
            URL url =new URL("https://sandbox.api.mastercard.com");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            
            String payload = "{\"exampleKey\": \"exampleValue\"}";

            // 4. Generate the OAuth 1.0a signature and sign the connection
            HttpsUrlConnectionSigner signer = new HttpsUrlConnectionSigner(
                StandardCharsets.UTF_8, 
                consumerKey, 
                signingKey
            );
            signer.sign(connection, payload);

            // The 'connection' object now contains the valid 'Authorization' header.
            // Proceed to read response...
            int responseCode = connection.getResponseCode();
            //System.out.println("Response Code: " + connection.getInputStream().toString());
            System.out.println("Response Code: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}