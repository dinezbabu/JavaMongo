/**
 * Created by dbabu on 4/14/19.
 */
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Iterator;
import java.util.logging.Filter;


public class MongoConnection {
    public static void  main(String args[]){
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
        MongoCredential credential = MongoCredential.createCredential("dbabu","myDB","Pa$$w0rd2020".toCharArray());
        System.out.println("Connected to Mongo DB Sucessfully");

//        Create DB
        MongoDatabase mongoDatabase= mongoClient.getDatabase("myDB");
        System.out.println("Credential :: "+credential);

//        Create Collection
        mongoDatabase.createCollection("Collection_1");
        System.out.println("Collection created Successfully");

        MongoCollection<Document> documentMongoCollection = mongoDatabase.getCollection("Collection_1");
        System.out.println("Document Collection : "+documentMongoCollection);

        //Insert Document
        Document document = new Document("title","MongoDB")
                .append("id",1)
                .append("description","Redis")
                .append("likes", 100)
                .append("url", "http://www.tutorialspoint.com/mongodb/")
                .append("by", "tutorials point");
                documentMongoCollection.insertOne(document);
                System.out.println("Data Inserted Sucessfully");

        FindIterable<Document> iterDoc = documentMongoCollection.find();
        int i=1;
        Iterator it = iterDoc.iterator();

        while (it.hasNext())
            System.out.println("Insert : "+it.next());
        i++;

        //Update Document
        documentMongoCollection.updateOne(Filters.eq("id",1), Updates.set("description","DINESH"));
        iterDoc = documentMongoCollection.find();
        i=1;
        it = iterDoc.iterator();

        while (it.hasNext())
            System.out.println("Update : "+it.next());
        i++;

        //Delete Document
        documentMongoCollection.deleteOne(Filters.eq("id",1));
        iterDoc = documentMongoCollection.find();
        i=1;
        it = iterDoc.iterator();

        while (it.hasNext())
            System.out.println("Deleted : "+it.next());
        i++;

        //Listing all collection
        for (String name : mongoDatabase.listCollectionNames()) {
            System.out.println(name);
        }

        // Dropping a Collection
        documentMongoCollection.drop();


    }
}
