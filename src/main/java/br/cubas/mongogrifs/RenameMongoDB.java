package br.cubas.mongogrifs;

import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

public class RenameMongoDB {

	public static void main(String[] args) {
		System.out.println("Calling rename...");
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		try {
			MongoDatabase database = mongoClient.getDatabase("filestest");
			GridFSBucket gridBucket = GridFSBuckets.create(database);
			gridBucket.rename(new ObjectId("5a61d49e2653ff28fec36ec6"), "grifs2.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

	}

}
