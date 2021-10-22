package lf05;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mango {

    private static String mongoUser;
    private static String mongoPassword;
    private static String connectionURL= "mongodb+srv://" + mongoUser + ":" + mongoPassword + "@mangodonnerbank.8ohfq.mongodb.net/Krautundrueben?retryWrites=true&w=majority";
    private static ConnectionString connectionString;
    private static MongoClientSettings settings;
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static boolean mangoConnected = false;

    private static Logger lgr = Logger.getLogger(Mango.class.getName());
    private static Properties props = new Properties();
    private static String fileName =  "LF05Web/src/db.properties";
    private static String url;
    private static String user;
    private static String password;
    private static Connection sqlconnection;
    private static boolean mySQLConnected = false;
    private static HashMap<String, HashMap<String,ArrayList<String>>> mySmarties;

    public static void loadMySmarties()
    {
        if(!mySQLConnected)return;
        ArrayList<String> tables = new ArrayList<>();
        try {
            DatabaseMetaData md = sqlconnection.getMetaData();
            ResultSet rs = md.getTables(null, null, "%", null);
            while (rs.next()) {
                System.out.println(rs.getString(3));
                tables.add(rs.getString(3));
            }
            for(String s : tables)
            {
                HashMap<String, ArrayList<String>> map = new HashMap<>();
                Statement st = sqlconnection.createStatement();
                ResultSet result = st.executeQuery("SELECT * FROM " + s);
                ResultSetMetaData rsmd = result.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                while(result.next())
                {
                    ArrayList<String> list = new ArrayList<>();
                    for(int i =0; i < columnsNumber; i++)
                    {
                        list.add(result.getString(i));
                    }
                    map.put(list.get(0),list);
                }
                mySmarties.put(s,map);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(mySmarties);
    }

    public static void mySquirrlConnect() {
        try (FileInputStream fis = new FileInputStream(fileName))
        {
            props.load(fis);
        }
        catch (IOException ex)
        {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            mySQLConnected = false;
            System.out.println("Could not Connect to mySQL Server");
            return;
        }
        url = props.getProperty("db.url");
        user = props.getProperty("db.user");
        password = props.getProperty("db.password");

        try
        {
            sqlconnection = DriverManager.getConnection(url, user, password);
            mySQLConnected = true;
            System.out.println("Established MySmartiers Connection");
        }
            catch (Exception ex)
        {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            mySQLConnected = false;
            System.out.println("Could not Connect to mySQL Server");
            return;
        }
    }

    public static void mangoConnect()
    {
        try{
            Properties mongoProps = new Properties();
            String fileName = "LF05Web/src/dbMango.properties";
            try (FileInputStream fis = new FileInputStream(fileName)) {
                mongoProps.load(fis);
            } catch (IOException ex) {
                mangoConnected = false;
                ex.printStackTrace();
                System.out.println("Could not connect to MangoDonnerbank.");
                return;

            }
            mongoUser = props.getProperty("db.user");
            mongoPassword = props.getProperty("db.password");
            connectionString = new ConnectionString(connectionURL);
            settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase("Krautundrueben");
            mangoConnected = true;
            System.out.println("Connected to MangoDonnerbank.");
        } catch (Exception e) {
            mangoConnected = false;
            e.printStackTrace();
            System.out.println("Could not connect to MangoDonnerbank.");
        }
    }

    public static void clearCollections()
    {
        for (String s :database.listCollectionNames())
        {
            database.getCollection(s).drop();
        }
    }

    public static void createCollections(ArrayList<String> list)
    {
        for(String s : list)
        {
            database.createCollection(s);
        }
    }

    public static void updateEntry(MongoCollection collection, Document searchEntry, Bson updatedEntry)
    {
        Document found = (Document) collection.find(searchEntry).first();
        if(found != null)
        {
            Bson updateoperation = new Document("$set", updatedEntry);
            collection.updateOne(found,updateoperation);
        }
    }

    public static void main(String[] args)
    {
        if(!mangoConnected) mangoConnect();
        if(!mySQLConnected) mySquirrlConnect();
        File file = new File(".");
        for(String fileNames : file.list()) System.out.println(fileNames);
        MongoCollection collection = database.getCollection("Krautundrueben");
        loadMySmarties();

        //updateEntry(collection,new Document("mango","döner"), new Document("mango", "Mangoparty"));
/*
 Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM kunde"))
        {
            mySQLConnected = true;
            while (rs.next())
            {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
 */

        /*
        Document document = new Document("name", "maxi");
        document.append("aaa","value");
        document.append("info1", "something");
        collection.insertOne(document);

        document = new Document("name", "maxi2");
        document.append("mango","döner");
        document.append("tee", "döner");
        collection.insertOne(document);
         */
        //collection.drop();

    }
}
