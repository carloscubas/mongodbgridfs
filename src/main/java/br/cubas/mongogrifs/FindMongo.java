package br.cubas.mongogrifs;

import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import static com.mongodb.client.model.Filters.eq;

public class FindMongo {

	public static void main(String[] args) {

		System.out.println("Calling find...");
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

		try {
			MongoDatabase database = mongoClient.getDatabase("filestest");
			GridFSBucket gridBucket = GridFSBuckets.create(database);

			GridFSFile gridFSFile = gridBucket.find(eq("_id", new ObjectId("5a61d49e2653ff28fec36ec6"))).first();
			System.out.println("File Name:- " + gridFSFile.getFilename());
			System.out.println("Meta Data:- " + gridFSFile.getMetadata());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}
	}

}
