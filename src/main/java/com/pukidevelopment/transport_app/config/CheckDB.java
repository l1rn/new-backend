package com.pukidevelopment.transport_app.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class CheckDB {
    private String check(String first_name, String last_name,
                         int user_id, String username){
        MongoClientURI connectionString = new MongoClientURI("mongodb://host:post");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("db_name");
        MongoCollection<Document> collection = database.getCollection("users");
        long found = collection.countDocuments(Document.parse("{id"));
        if(found == 0){
            Document doc = new Document("first_name", first_name)
                    .append("last_name", last_name)
                    .append("id", user_id)
                    .append("username", username);
            collection.insertOne(doc);
            mongoClient.close();
            System.out.println("User not exists in db. Written");
            return "no_exists";
        }else {
            System.out.println("User exists in db");
            mongoClient.close();
            return "exists";
        }
    }
}
