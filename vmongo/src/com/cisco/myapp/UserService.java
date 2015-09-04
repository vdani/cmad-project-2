package com.cisco.myapp;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

//import org.glassfish.jersey.media.multipart.MultiPartFeature
//import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("/user")
public class UserService {

	//private Datastore datastore;
	
	
//	@GET	
//	public List<User> getUsers(@QueryParam("ageFrom") Integer ageFrom,
//			@QueryParam("ageTo") Integer ageTo) throws Exception {
////			Session ses = HibernateUtil.currentSession();
//			try {
//				//return ses.createQuery("select u from User u where u.age>"+ageFrom+" and u.age<"+ageTo).list();
//				return ses.createQuery("select u from User u where u.id=1").list();
//			} finally {
//				//ses.close();
//				HibernateUtil.closeSession();
//			}
//	}
	
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User u) throws Exception{
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		Morphia morphia = new Morphia();
		morphia.mapPackage("mongoProject.model");
		Datastore datastore = morphia.createDatastore(mongoClient, "test");
		datastore.ensureIndexes();
		try{
			datastore.save(u);
			
		} catch (Exception e){
		     e.printStackTrace();
		    
		} finally {
			mongoClient.close();
		}
		
		return u;
	}
	
	@POST
	@Path("/validate")
	public User validateUser(User u1) throws Exception{
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		Morphia morphia = new Morphia();
		morphia.mapPackage("mongoProject.model");
		Datastore datastore = morphia.createDatastore(mongoClient, "test");
		datastore.ensureIndexes();
		try{
			String em = u1.getEmailId();
			Query query = datastore.createQuery(User.class).filter("emailId = ", em); 
            User user = (User) query.asList().get(0);		
			if (user != null) {
				return u1;
			} else {
				return null;
			}
		} catch (Exception e){
		    e.printStackTrace();
		}  finally {
			mongoClient.close();
		}	
		return null;
	
	}
	
	@GET
	public List<User> getQuestion() throws Exception{
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		Morphia morphia = new Morphia();
		morphia.getMapper().getOptions().setStoreNulls(true);
		morphia.mapPackage("mongoProject.model");
		Datastore datastore = morphia.createDatastore(mongoClient, "test");
		DBCollection m = datastore.getCollection( User.class );
		datastore.ensureIndexes();
		try{
			List<User> users;
			return users = datastore.find(User.class).field("question").notEqual(null).asList();
		} catch (Exception e){
		    e.printStackTrace();
		}  finally {
			mongoClient.close();
		}	
		return null;
	
	}
	
	@POST
	@Path("/question")
	public User question(User u) throws Exception{
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		Morphia morphia = new Morphia();
		morphia.mapPackage("mongoProject.model");
		Datastore datastore = morphia.createDatastore(mongoClient, "test");
		datastore.ensureIndexes();
		try{
			datastore.save(u);
			
		} catch (Exception e){
		     e.printStackTrace();
		    
		} finally {
			mongoClient.close();
		}
		
		return u;
	}
	
	@POST
	@Path("/responserequest")
	public List<User> responseRequest(String q) throws Exception{
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		Morphia morphia = new Morphia();
		morphia.mapPackage("mongoProject.model");
		Datastore datastore = morphia.createDatastore(mongoClient, "test");
		datastore.ensureIndexes();
		try{
			//String em = u1.getQuestion();
			Query query = datastore.createQuery(User.class).filter("question = ", q); 
			List<User> user = (List<User>) query.asList();
			return user;
		} catch (Exception e){
		    e.printStackTrace();
		}  finally {
			mongoClient.close();
		}	
		return null;
	
	}
	
	@POST
	@Path("/response")
	public User response(User u) throws Exception{
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		MongoClient mongoClient = new MongoClient(connectionString);
		Morphia morphia = new Morphia();
		morphia.mapPackage("mongoProject.model");
		Datastore datastore = morphia.createDatastore(mongoClient, "test");
		datastore.ensureIndexes();
		try{
			datastore.save(u);
			
		} catch (Exception e){
		     e.printStackTrace();
		    
		} finally {
			mongoClient.close();
		}
		
		return u;
	}
}


