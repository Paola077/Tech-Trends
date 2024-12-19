package com.example.Tech_Trends.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Tech_Trends.exceptions.TechTrendFailedUploadException;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class CloudinaryProvider {

    private static Dotenv dotenv = Dotenv.load();
    private static Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    private static Map paramsImg = ObjectUtils.asMap(
            "use_filename", true,
            "unique_filename", false,
            "overwrite", true
    );

    public static Map<String, Object> uploadImage(String url) {
        Map<String, Object> uploadResult = Collections.emptyMap();
        try {
            uploadResult = cloudinary.uploader().upload(url, paramsImg);
        } catch (IOException e) {
            throw new TechTrendFailedUploadException(e.getMessage());
        }
        return uploadResult;
    }
}
