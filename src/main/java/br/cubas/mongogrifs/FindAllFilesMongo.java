package br.cubas.mongogrifs;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;

public class FindAllFilesMongo {

	public static void main(String[] args) {

		System.out.println("Calling findAll...");
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

		try {
			MongoDatabase database = mongoClient.getDatabase("filestest");
			GridFSBucket gridBucket = GridFSBuckets.create(database);

			gridBucket.find().forEach(new Block<GridFSFile>() {
				@Override
				public void apply(final GridFSFile gridFSFile) {
					System.out.println("File Name:- " + gridFSFile.getFilename());
					System.out.println("Meta Data:- " + gridFSFile.getMetadata());
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

	}

}
