package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.*;

import java.util.ArrayList;
import java.util.Random;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import data.Data;
import data.EmployeeData;
import play.mvc.Controller;
import play.mvc.Result;
import javax.inject.Inject;
//import views.html.*;  
/**
 *
 * @author myfear
 */
public class HomeController extends Controller {
	
	


    /**
     * An action that renders an HTML page with a welcome message. The
     * configuration in the <code>routes</code> file means that this method will
     * be called when the application receives a <code>GET</code> request with a
     * path of <code>/</code>.
     *
     * @return
     */
	
	/*  public Result index() {
		  return ok("It works fine!"); 
		  }*/
		  
		

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        Config conf = ConfigFactory.load("additional.conf");
        Cluster cluster = null;
        String renderString = "Cannot connect to Cassandra";
        try {
            cluster = Cluster.builder()
                    .addContactPoint("127.0.0.1")
                    .build();
            Session session = cluster.connect();

            ResultSet rs = session.execute("select release_version from system.local");
            Row row = rs.one();
            renderString = "Connection successful to Cassandra version " + row.getString("release_version");
            
        } finally {
            if (cluster != null) cluster.close();
        }

        return ok(views.html.index.render(renderString));
    }
	
	/* public Result upload() {
    JsonNode node = request().body().asJson();
  String renderString = "Printed";
  String name=node.get("name").asText();
String email=node.get("email").asText();
    System.out.println(node.toString())  ;   
	   System.out.println("Name: "+name+ "Email : "+email)  ;  
	// prints the string from the form field
     return ok(views.html.index.render(renderString));
} */
	
	 public Result upload() {
    	   JsonNode node = request().body().asJson();
       Cluster cluster = null;
       Random random = new Random();
       String renderString = "Welcome";
	    String name=node.get("name").asText();
		String email=node.get("email").asText();
		String ph=node.get("phone").asText();
		String job=node.get("job").asText();
		String pass=node.get("pass").asText();
      System.out.println("Name:"+name+"-Email:"+email+"-ph:"+ph+"-job:"+job+"-pass:"+pass);
     EmployeeData  employeeData =new EmployeeData();
        if(!name.equals("") && !email.equals("") && !ph.equals("") && !job.equals("") && !pass.equals(""))
       {
        	int id = random.nextInt(50);
        	try {
                cluster = Cluster.builder()
                       .addContactPoint("127.0.0.1")
                        .build();
                Session session = cluster.connect();
              
             ResultSet rs = session.execute("INSERT INTO demo.employee (id, email, job, name, pass, ph) VALUES ("+id+", '"+email+"','"+job+"' , '"+name+"', '"+pass+"', '"+ph+"')");
               Row row = rs.one();
            //     ArrayList<Data> empData = new ArrayList<Data>();
                // for (Row row : rs) {
                	// empData.add(
                            // new Data(
                                    // row.getInt("id"),
                                    // row.getString("email"),
                                    // row.getString("job"),
                                    // row.getString("name"),
                                    // row.getString("pass"),
                                    // row.getString("ph")
                                    // ));
                // }
              //   employeeData.setEmpData(empData);

              // trackingData.setLocations(empData);
              renderString = "Data save";
                
             } finally {
                if (cluster != null) cluster.close();
             }
       }
        
         return ok(views.html.index.render(renderString));
     }


	 
    
/*  public Result index(String name,String email,String ph,String job,String pass) {
    	Config conf = ConfigFactory.load("additional.conf");
    	Config conf = ConfigFactory.load("additional.conf");
       Cluster cluster = null;
       Random random = new Random();
       String renderString = "Welcome";
        // System.out.println("Name:"+name+"-Email:"+email+"-ph:"+ph+"-job:"+job+"-pass:"+pass);
        // EmployeeData  employeeData =new EmployeeData();
        // if(!name.equals("") && !email.equals("") && !ph.equals("") && !job.equals("") && !pass.equals(""))
        // {
        	// int id = random.nextInt(50);
        	// try {
                // cluster = Cluster.builder()
                        // .addContactPoint("127.0.0.1")
                        // .build();
                // Session session = cluster.connect();
              
                // ResultSet rs = session.execute("INSERT INTO demo.employeeData (id, email, job, name, pass, ph) VALUES ("+id+", '"+email+"','"+job+"' , '"+name+"', '"+pass+"', '"+ph+"')");
               Row row = rs.one();
            //     ArrayList<Data> empData = new ArrayList<Data>();
                // for (Row row : rs) {
                	// empData.add(
                            // new Data(
                                    // row.getInt("id"),
                                    // row.getString("email"),
                                    // row.getString("job"),
                                    // row.getString("name"),
                                    // row.getString("pass"),
                                    // row.getString("ph")
                                    // ));
                // }
                // employeeData.setEmpData(empData);

               // trackingData.setLocations(empData);
               // renderString = "Data save";
                
            // } finally {
                // if (cluster != null) cluster.close();
            // }
        // }
        
        // return ok(views.html.index.render(renderString));
     } */
     
    public Result employee() {
    	//Config conf = ConfigFactory.load("additional.conf");
    	//Config conf = ConfigFactory.load("additional.conf");
        Cluster cluster = null;
        String renderString = "Cannot connect to Cassandra";
        EmployeeData  employeeData =new EmployeeData();
        try {
            cluster = Cluster.builder()
                    .addContactPoint("127.0.0.1")
                    .build();
            Session session = cluster.connect();
          
            ResultSet rs = session.execute("select id,email,job,name,pass,ph from demo.employee");
           // Row row = rs.one();
            ArrayList<Data> empData = new ArrayList<Data>();
            for (Row row : rs) {
            	empData.add(
                        new Data(
                                row.getInt("id"),
                                row.getString("email"),
                                row.getString("job"),
                                row.getString("name"),
                                row.getString("pass"),
                                row.getString("ph")
                                ));
            }
            employeeData.setEmpData(empData);

           // trackingData.setLocations(empData);
            //renderString = "Employee Data: " +  row.getInt("id")+ "-" + row.getString("name") + "-" +row.getString("email") +"-" + row.getString("job")+"-"+row.getString("ph");
            
        } finally {
            if (cluster != null) cluster.close();
        }
        return ok(views.html.employee.render(employeeData));
    }
    
  /*  public Result edit() {
    	//Config conf = ConfigFactory.load("additional.conf");
    	//Config conf = ConfigFactory.load("additional.conf");
        Cluster cluster = null;
        String renderString = "Cannot connect to Cassandra";
        EmployeeData  employeeData =new EmployeeData();
        try {
            cluster = Cluster.builder()
                    .addContactPoint("127.0.0.1")
                    .build();
            Session session = cluster.connect();
          
            ResultSet rs = session.execute("select id,email,job,name,pass,ph from demo.employeeData");
           // Row row = rs.one();
            ArrayList<Data> empData = new ArrayList<Data>();
            for (Row row : rs) {
            	empData.add(
                        new Data(
                                row.getInt("id"),
                                row.getString("email"),
                                row.getString("job"),
                                row.getString("name"),
                                row.getString("pass"),
                                row.getString("ph")
                                ));
            }
            employeeData.setEmpData(empData);

           // trackingData.setLocations(empData);
            //renderString = "Employee Data: " +  row.getInt("id")+ "-" + row.getString("name") + "-" +row.getString("email") +"-" + row.getString("job")+"-"+row.getString("ph");
            
        } finally {
            if (cluster != null) cluster.close();
        }
        return ok(views.html.employee.render(employeeData));
  }
    }*/
	public Result delete() {
		 JsonNode node = request().body().asJson();
    int id=node.get("id").asInt();
        Cluster cluster = null;
        String renderString = "Cannot connect to Cassandra";
        try {
            cluster = Cluster.builder()
                    .addContactPoint("127.0.0.1")
                    .build();
            Session session = cluster.connect();
          String query1 = "DELETE FROM demo.employee WHERE id="+id+";";
           session.execute(query1);
           
            renderString = "Employee Data Deleted";
            
        } finally {
            if (cluster != null) cluster.close();
        }
        return ok(views.html.index.render(renderString));
	}

    /**
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result sayHello() {
        JsonNode json = request().body().asJson();
        String name = json.findPath("name").textValue();
        if (name == null) {
            return badRequest("Missing parameter [name]");
        } else {
            return ok("Hello " + name);
        }
    }

}
