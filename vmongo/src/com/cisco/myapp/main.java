package com.cisco.myapp;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
			MongoClient mongoClient = new MongoClient(connectionString);
			Morphia morphia = new Morphia();
			morphia.mapPackage("com.cisco.myapp");
			Datastore datastore = morphia.createDatastore(mongoClient, "test");
			datastore.ensureIndexes();
			
			User user = new User();
			user.setUserName("vinoos");
			user.setFirst("Vinu");
			user.setLast("chandran");
			datastore.save(user);
			
			List<User> users = datastore.createQuery(User.class).asList();
			System.out.println(users);
	}

}
