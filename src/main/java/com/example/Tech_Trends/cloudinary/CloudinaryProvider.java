package com.example.Tech_Trends.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Tech_Trends.exceptions.TechTrendFailedUploadException;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class CloudinaryProvider {

    private static Map paramsImg = ObjectUtils.asMap(
            "use_filename", true,
            "unique_filename", false,
            "overwrite", true
    );

    private static Cloudinary getCloudinaryInstance() {
        String cloudinaryUrl;

        try {
            Dotenv dotenv = Dotenv.configure().load();
            cloudinaryUrl = dotenv.get("CLOUDINARY_URL");
        } catch (Exception e) {
            cloudinaryUrl = System.getenv("CLOUDINARY_URL");
        }

        if (cloudinaryUrl == null || cloudinaryUrl.isEmpty()) {
            throw new RuntimeException("CLOUDINARY_URL is not set");
        }

        return  new Cloudinary(cloudinaryUrl);
    }

    public static Map<String, Object> uploadImage(String url) {
        Map<String, Object> uploadResult = Collections.emptyMap();
        try {
            uploadResult = getCloudinaryInstance().uploader().upload(url, paramsImg);
        } catch (IOException e) {
            throw new TechTrendFailedUploadException(e.getMessage());
        }
        return uploadResult;
    }
}
