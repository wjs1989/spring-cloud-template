package com.wjs.produce.minio;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class MClient {
    public static void main(String[] args) throws InvalidPortException, InvalidEndpointException {
        String endpoint = "http://10.204.125.33:9000";
        String accessKey = "minioadmin";
        String secretKey = "minioadmin";
        String bucketName = "dev";

        MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);

        //listBucketscreateBucket(minioClient,"dev");
       // listBuckets(minioClient);
        statObject(minioClient,"dev","js/test.js");
    }


    /**
     * 创建桶
     *
     * @param minioClient
     * @param bucketName
     */
    public static void createBucket(MinioClient minioClient, String bucketName) {
        try {
            // 如存储桶不存在，创建之。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                System.out.println("bucket already exists");
            } else {
                // 创建名为'my-bucketname'的存储桶。
                minioClient.makeBucket(bucketName);
                System.out.println("bucket is created successfully");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

    /**
     * 列出所有桶
     *
     * @param minioClient
     */
    public static void listBuckets(MinioClient minioClient) {
        try {
            // 列出所有存储桶
            List<Bucket> bucketList = minioClient.listBuckets();
            for (Bucket bucket : bucketList) {
                System.out.println(bucket.creationDate() + ", " + bucket.name());
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

    /**
     * 判断桶是否存在
     *
     * @param minioClient
     * @param bucketName
     */
    public static void bucketExists(MinioClient minioClient, String bucketName) {
        try {
            // 检查'my-bucketname'是否存在。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                System.out.println("bucket exists");
            } else {
                System.out.println("bucket does not exist");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

    /**
     * 删除桶
     *
     * @param minioClient
     * @param bucketName
     */
    public static void removeBucket(MinioClient minioClient, String bucketName) {
        try {
            // 删除之前先检查`my-bucket`是否存在。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                // 删除`my-bucketname`存储桶，注意，只有存储桶为空时才能删除成功。
                minioClient.removeBucket(bucketName);
                System.out.println("mybucket is removed successfully");
            } else {
                System.out.println("mybucket does not exist");
            }
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出某个存储桶中的所有对象
     *
     * @param minioClient
     * @param bucketName  存储桶名称。
     * @param prefix      对象名称的前缀
     * @param recursive   是否递归查找，如果是false,就模拟文件夹结构查找
     * @param useVersion  如果是true, 使用版本1 REST API
     */
    public static void listObjects(MinioClient minioClient, String bucketName,
                                   String prefix, boolean recursive, boolean useVersion) {
        try {
            // 检查'mybucket'是否存在。
            boolean found = minioClient.bucketExists(bucketName);
            if (found) {
                // 列出'my-bucketname'里的对象
                Iterable<Result<Item>> myObjects = minioClient.listObjects(bucketName);
                for (Result<Item> result : myObjects) {
                    Item item = result.get();
                    System.out.println(item.lastModified() + ", " + item.size() + ", " + item.objectName());
                }
            } else {
                System.out.println("mybucket does not exist");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }


    /**
     * 以流的形式下载一个对象
     *
     * @param bucketName
     * @param objectName
     * @param offset
     * @return
     */
    public static InputStream getObject(MinioClient minioClient, String bucketName, String objectName, long offset) {
        try {
            // 调用statObject()来判断对象是否存在。
            // 如果不存在, statObject()抛出异常,
            // 否则则代表对象存在。
            minioClient.statObject(bucketName, objectName);
            // 获取"myobject"的输入流。
            InputStream stream = minioClient.getObject(bucketName, objectName);
//            // 读取输入流直到EOF并打印到控制台。
//            byte[] buf = new byte[16384];
//            int bytesRead;
//            while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
//                System.out.println(new String(buf, 0, bytesRead));
//            }
//            // 关闭流，此处为示例，流关闭最好放在finally块。
//            stream.close();

            return stream;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return  null;

    }
    public static void statObject(MinioClient minioClient,String bucketName, String objectName){
        try {
            // 获得对象的元数据。
            ObjectStat objectStat = minioClient.statObject(bucketName, objectName);
            System.out.println(objectStat);
        } catch(Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

}

