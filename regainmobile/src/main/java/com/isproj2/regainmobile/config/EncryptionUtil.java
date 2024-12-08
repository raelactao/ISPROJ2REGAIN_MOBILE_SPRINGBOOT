package com.isproj2.regainmobile.config;

import java.nio.charset.StandardCharsets;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
@Converter
public class EncryptionUtil implements AttributeConverter<String, String> {

    // @Value("${regain.crypto.key}")
    private String KEY = "1234567812345678";

    // @Value("${regain.crypto.iv}")
    private String INIT_V = "1234567812345678";

    // @Value("${regain.crypto.algo}")
    private String ALGORITHM = "AES/CBC/PKCS5PADDING";

    // @Value("${regain.crypto.enc}")
    private String ENC = "AES";

    @Override
    public String convertToDatabaseColumn(String attribute) {
        // encrypting
        String encryptedText = null;
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_V.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ENC);
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            // initialize cipher with key and other parameters like initial vector
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            encryptedText = Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        // returns null if exception happens
        return encryptedText;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        // decrypting
        String decryptedText = null;
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_V.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec key = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ENC);
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key, iv);
            decryptedText = new String(c.doFinal(Base64.getDecoder().decode(dbData)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

}