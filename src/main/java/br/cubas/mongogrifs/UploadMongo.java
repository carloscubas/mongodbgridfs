package br.cubas.mongogrifs;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;

public class UploadMongo {

	public static void main(String[] args) {
		
		String filePath = "gridfs.jpg";

		System.out.println("Calling upload...");
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
		ObjectId fileId = null;
		
		try {
			MongoDatabase database = mongoClient.getDatabase("filestest");
			GridFSBucket gridBucket = GridFSBuckets.create(database);
			InputStream inputStream = new FileInputStream(new File(filePath));
			// Create some custom options
			GridFSUploadOptions uploadOptions = new GridFSUploadOptions().chunkSizeBytes(1024)
					.metadata(new Document("type", "image").append("upload_date", format.parse("2016-09-01T00:00:00Z"))
							.append("content_type", "image/jpg"));
			fileId = gridBucket.uploadFromStream("gridfs.jpg", inputStream, uploadOptions);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
		
		System.out.println(fileId);

	}

}
