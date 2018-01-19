package br.cubas.mongogrifs;

import java.io.FileOutputStream;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

public class DownloadMongo {

	public static void main(String[] args) {

		System.out.println("Calling download...");

		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

		try {
			MongoDatabase database = mongoClient.getDatabase("filestest");
			GridFSBucket gridBucket = GridFSBuckets.create(database);

			FileOutputStream fileOutputStream = new FileOutputStream("gridfs-download.jpg");
			gridBucket.downloadToStream("gridfs.jpg", fileOutputStream);
			fileOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
	}
	
}
