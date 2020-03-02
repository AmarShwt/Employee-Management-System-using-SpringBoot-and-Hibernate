package in.employeeManagement.awsUtils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.s3.model.PutObjectResult;

import in.employeeManagement.controller.EmployeeController;

public class AwsS3CrudDemo {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	public static void main(String[] args) {
		
		S3Functionalities s3Functionalities = S3Functionalities.getInstance();
		
		String bucketName = "amars3demobucket";
		//String key = "123";
		String fileObjKeyName = "C:\\Users\\DELL LAPY\\Desktop\\Testing.txt";
		String fileName = "test.txt";
		
		//List Buckets
		LOGGER.info("Calling List Bucket");
		s3Functionalities.listBuckets();
		LOGGER.info("Done with Listing Bucket");
		
		//Create Bucket
		LOGGER.info("Calling Create Bucket");
		s3Functionalities.createBucket(bucketName);
		LOGGER.info("Done with Creating Bucket");
		
		//List Buckets
		LOGGER.info("Calling List Bucket");
		s3Functionalities.listBuckets();
		LOGGER.info("Done with Listing Bucket");
		
		
		PutObjectResult object = null;
		//Put Object in Bucket
		LOGGER.info("Calling Put Object in Bucket");
		try {
			object = s3Functionalities.addObjectToBucket(bucketName, fileObjKeyName, fileName);
			LOGGER.info("Done with Putting Object in Bucket");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Get Objects in Bucket
		LOGGER.info("Calling List Objects in Bucket");
		try {
			s3Functionalities.getObjectFromBucket(bucketName, fileName);
			LOGGER.info("Done with Listing Objects in Bucket");
		} catch (IOException e) {
			LOGGER.info("Got Error!");
			e.printStackTrace();
		}
		
		//Delete Bucket
		LOGGER.info("Calling Delete Objects in Bucket");
		s3Functionalities.deleteBucket(bucketName);
		LOGGER.info("Done with Deleting the Bucket");
		
	}

}
