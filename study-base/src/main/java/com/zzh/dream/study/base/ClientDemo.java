package com.zzh.dream.study.base;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

public class ClientDemo {
    private final static String partnerId = "20007806";
    private final static String partnerSecret = "ad776bc12bc30a58b4ddee404187d30c";
    private final static String request_url = "https://mip-receiver.tengmed.com/testapi/mipuserquery/userQuery/" + partnerId;
    private final static char[] hexArray = "0123456789abcdef".toCharArray();
    private final static String HmacSHA256 = "HmacSHA256";
    private final static String charsetName = "UTF-8";


    private String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0Xff;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private String createSignature(String partnerSecret, String partnerId, String timestamp)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256_HMAC = Mac.getInstance(HmacSHA256);
        SecretKeySpec secret_key = new SecretKeySpec(partnerSecret.getBytes(charsetName), HmacSHA256);
        sha256_HMAC.init(secret_key);
        return bytesToHex(sha256_HMAC.doFinal((partnerId + timestamp).getBytes(charsetName)));
    }

    private String postData(String businessName, String funcName, String partnerId, String data)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        HttpURLConnection conn = null;
        URL url = new URL(request_url);
        conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        String timestamp = Long.toString(System.currentTimeMillis());
        String signature = createSignature(partnerSecret, partnerId, timestamp);
        String requestId = UUID.randomUUID().toString().replaceAll("-", "");
        conn.setRequestProperty("god-portal-timestamp", timestamp);
        conn.setRequestProperty("god-portal-signature", signature);
        conn.setRequestProperty("god-portal-request-id", requestId);
        conn.connect();
        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        os.close();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStream = conn.getInputStream();
        byte[] bytes = new byte[1024];
        int readBytes;
        while ((readBytes = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, readBytes);
        }
        bytes = byteArrayOutputStream.toByteArray();
        inputStream.close();
        conn.disconnect();
        return new String(bytes, charsetName);
    }

    public static void main(String[] args) {
        ClientDemo demo = new ClientDemo();
        try {
            System.out.println(demo.createSignature(partnerSecret, partnerId, new Date().getTime() + ""));
            System.out.println(demo.postData("test", "test2", demo.partnerId, "{\"qrcode\":\"384188081113592029\",\"openid\":\"ob17k0wXB5vKHNRhCZRIvcRuW7Qw\"}"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




