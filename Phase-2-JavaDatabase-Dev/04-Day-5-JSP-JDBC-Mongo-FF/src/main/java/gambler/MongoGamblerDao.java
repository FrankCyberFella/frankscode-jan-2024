package gambler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;

import org.bson.Document;

import exceptions.DataBaseDeleteException;
import exceptions.DataBaseInsertException;
import exceptions.DataBaseUpdateException;

public class MongoGamblerDao implements GamblerDao {

	private static String connectionString = "mongodb+srv://JavaStudent:JavaRules@cluster0.jyeplot.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
	
	private static ServerApi serverApi = null;;
	
	private static MongoClient mongoClient = null;;
	
	private static MongoClientSettings settings = null;
	
	private static MongoDatabase database = null;
	
	private static long lastGamblerId;
	
	CodecProvider pojoCodecProvider;
	
	CodecRegistry pojoCodecRegistry;
	
	public MongoGamblerDao() {
		
		super();    
		lastGamblerId = 1000;
		
		pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
	    pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider)); 
	}


	
	@Override
	public boolean addGambler(Gambler newGambler) throws DataBaseInsertException, Exception {
		
		  connectToMongo();
		
		  MongoCollection<Document> theGamblers = database.getCollection("gambler")	;
		  
	      //Preparing a document
	      Document document = new Document();
	      
	      // Add fields to document
	      document.append("name"         , newGambler.getName());
	      document.append("address"      , newGambler.getAddress());
	      document.append("birthDate"    , newGambler.getBirthDate());
	      document.append("monthlySalary", newGambler.getMonthlySalary());
	      document.append("gamblerId"    , lastGamblerId++);
	      
	      //Inserting the document into the collection
	      theGamblers.insertOne(document);
	      
	      System.out.println("Document inserted successfully");

		
		return false;
	}

	@Override
	public List<Gambler> getAllGamblers() throws SQLException {
		
		System.out.println("Getting all Gamblers");
		
		List<Gambler> allGamblers = new ArrayList();
		
  		connectToMongo();
		
        MongoCollection<Gambler> collection = database.getCollection("gambler",Gambler.class);
        
        FindIterable<Gambler> doc = collection.find();
        
        if (doc != null) {
    	   Iterator it = doc.iterator();
    	      while (it.hasNext()) {
    	    	  Object aGambler = it.next();
    	         System.out.println(aGambler);
    	         allGamblers.add((Gambler) aGambler);
    	      }
        } else {
            System.out.println("No matching documents found.");
        }
    
		return allGamblers;
	}
	            

	@Override
	public Gambler findGamblerById(long id) throws SQLException {
			
		System.out.println("Looking for Gambler ID: " + id);
		
		Gambler requesteGambler = new Gambler();
		
  		connectToMongo();
		
        MongoCollection<Gambler> collection = database.getCollection("gambler",Gambler.class);
        
        Bson gamblerIdFilter = Filters.eq("gamblerId", id);
    	 
        FindIterable<Gambler> aGambler  = collection.find(gamblerIdFilter);
        
        Iterator it = aGambler.iterator();
        
        Object requestedGambler = it.next();
        
       
		return (Gambler) requestedGambler;
	}

	@Override
	public List<Gambler> findGamblerByName(String name) throws SQLException {
		
		List<Gambler> theGamblers = new ArrayList();
		
		System.out.println("Looking for name: " + name);
		
  		connectToMongo();
		
        MongoCollection<Gambler> collection = database.getCollection("gambler",Gambler.class);
        
        Bson filter = Filters.eq("name", name);
    	 
       FindIterable<Gambler> matchingGamblers = collection.find(filter);
        
 	   Iterator it = matchingGamblers.iterator();

	      while (it.hasNext()) { 
	    	 Object aGambler = it.next();
	         System.out.println(aGambler);
	         theGamblers.add((Gambler) aGambler);
	      }
      
        return theGamblers;
		
	}

	@Override
	public void update(Gambler gambler) throws DataBaseUpdateException, DataBaseDeleteException, SQLException {
		connectToMongo();
		
		MongoCollection<Document>collection = database.getCollection("gambler");
		
		Bson searchFilter = Filters.eq("gamblerId", gambler.getGamblerId());
		
		collection.updateOne(searchFilter,
				          	 Updates.combine(
				          			         Updates.set("name"         , gambler.getName()),
				                             Updates.set("address"      , gambler.getAddress()),
				                             Updates.set("birthDate"    , gambler.getBirthDate()),
				                             Updates.set("monthlySalary", gambler.getMonthlySalary()))
				           );
				  
	}

	@Override
	public void delete(long id) throws DataBaseDeleteException, SQLException {
		
		 connectToMongo();
	
		 Bson query = Filters.eq("gamblerId", id);
		 
		 DeleteResult result = null;		 
         try {
             result = database.getCollection("gambler").deleteOne(query);
         }
         catch(Exception exceptionObject) {
        	 System.out.println("Error occured trying to perfor delet operation");
         }
         finally {
        	 try {
             System.out.println("Deleted document count: " + result.getDeletedCount());
        	 }
        	 catch(NullPointerException exceptionObject) {
        		 System.out.println("Unable to delete Gambler id: " + id);
        	 }
         }
	}

	private void connectToMongo() {
		
		   serverApi = ServerApi.builder()
	                .version(ServerApiVersion.V1)
	                .build();

	       settings = MongoClientSettings.builder()
	                .applyConnectionString(new ConnectionString(connectionString))
	                .serverApi(serverApi)
	                .build();

	        // Create a new client and connect to the server
	       mongoClient = MongoClients.create(settings);
	            try {
	                // Send a ping to confirm a successful connection
	            	mongoClient.getDatabase("sample_pojos").withCodecRegistry(pojoCodecRegistry);


	                database = mongoClient.getDatabase("vegasdb").withCodecRegistry(pojoCodecRegistry);


	                database.runCommand(new Document("ping", 1));
	                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
	            } catch (MongoException e) {
	                e.printStackTrace();
	            }
	           
	
	}
}
