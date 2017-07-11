package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Event;
import model.User;
import runApplication.Main;

/**
 * Utility class for reading and writing XML files that contain
 * Event and User data.
 * 
 * @author Peter Phe
 * @version 1.1
 */
public class XMLHandler
{
    /** Default constructor is private since this is a utility class. */
    private XMLHandler()
    {
        // intentionally empty
    }
    
    /**
     * Write Events.xml file from list.
     * @param theEvents List of Events
     */
    public static void writeEventXML(List<Event> theEvents)
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // build XML document tree
            Element root = doc.createElement("events");
            doc.appendChild(root);
            for (Event e : theEvents)
            {
                addEventChild(doc, root, e);
            }
       
            // prepare document for write
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer t = tFactory.newTransformer();
            t.setOutputProperty(OutputKeys.STANDALONE, "yes");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            t.transform(new DOMSource(doc), new StreamResult(new File("./Events.xml")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Write Users.xml file from list.
     * @param theUsers List of Users
     */
    public static void writeUserXML(List<User> theUsers)
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // build XML document tree
            Element root = doc.createElement("users");
            doc.appendChild(root);
            for (User u : theUsers)
            {
                addUserChild(doc, root, u);
            }
       
            // prepare document for write
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer t = tFactory.newTransformer();
            t.setOutputProperty(OutputKeys.STANDALONE, "yes");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            t.transform(new DOMSource(doc), new StreamResult(new File("./Users.xml")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
 

    /**
     * Read and parse event XML file to simulate an Event database.
     * 
     * @return List<Event> object
     */
    public static List<Event> readEventXML()
    {
        List<Event> events = new ArrayList<>();
        
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = null;
            
            // if file doesn't exist, create from template/sample data
            try
            {
                doc = dBuilder.parse(new File("Events.xml"));
            } 
            catch (FileNotFoundException e)
            {
                doc = dBuilder.parse(Main.class.getResourceAsStream("/SampleEvents.xml"));
            }
            
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("event");

            for (int i = 0; i < nodes.getLength(); i++)
            {
                Node t = nodes.item(i);
                if (t.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element e = (Element) t;
                    Event temp = new Event();
                    temp.setDate(e.getElementsByTagName("date").item(0).getTextContent());
                    temp.setTime(e.getElementsByTagName("time").item(0).getTextContent());
                    temp.setLocation(e.getElementsByTagName("location").item(0).getTextContent());
                    temp.setSponsor(e.getElementsByTagName("sponsor").item(0).getTextContent());
                    temp.setCategory(e.getElementsByTagName("category").item(0).getTextContent());
                    temp.setTitle(e.getElementsByTagName("title").item(0).getTextContent());
                    temp.getInfo().addCategory(e.getElementsByTagName("category").item(0).getTextContent(), 
                            Integer.valueOf(e.getElementsByTagName("qty").item(0).getTextContent()),
                            Integer.valueOf(e.getElementsByTagName("goal").item(0).getTextContent()));
                    events.add(temp);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error with Events.xml file!");
        }

        return events;
    }

    /**
     * Read and parse user XML file to simulate a User database.
     * 
     * @return List<User> object
     */
    public static List<User> readUserXML()
    {
        List<User> users = new ArrayList<>();

        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = null;
            
            // if file doesn't exist, create from template/sample data
            try
            {
                doc = dBuilder.parse(new File("Users.xml"));
            } 
            catch (FileNotFoundException e)
            {
                doc = dBuilder.parse(Main.class.getResourceAsStream("/SampleUsers.xml"));
            }
            
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("user");
            
            for (int i = 0; i < nodes.getLength(); i++)
            {
                Node t = nodes.item(i);
                if (t.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element e = (Element) t;
                    User temp = new User();
                    temp.setAccountID(e.getElementsByTagName("accountid").item(0).getTextContent());
                    temp.setPassword(e.getElementsByTagName("password").item(0).getTextContent());
                    temp.setOrganization(e.getElementsByTagName("organization").item(0).getTextContent());
                    temp.setPhone(e.getElementsByTagName("phone").item(0).getTextContent());
                    temp.setEmail(e.getElementsByTagName("email").item(0).getTextContent());
                    temp.setRole(e.getElementsByTagName("role").item(0).getTextContent());
                    users.add(temp);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error with Users.xml file!");
        }

        return users;
    }
    
    /**
     * Helper method to prepare a single Event node as a subtree of root.
     * 
     * @param theDoc Document object for creating Elements and Nodes
     * @param theRoot reference to the root node
     * @param theEvent Event object for extracting units of information
     */
    private static void addEventChild(Document theDoc, Element theRoot, Event theEvent)
    {
        Element e = theDoc.createElement("event");
        theRoot.appendChild(e);
        e.appendChild(createChildNode(theDoc, "date", theEvent.getDate()));
        e.appendChild(createChildNode(theDoc, "time", theEvent.getTime()));
        e.appendChild(createChildNode(theDoc, "location", theEvent.getLocation()));
        e.appendChild(createChildNode(theDoc, "sponsor", theEvent.getSponsor()));
        e.appendChild(createChildNode(theDoc, "category", theEvent.getCategory()));
        e.appendChild(createChildNode(theDoc, "title", theEvent.getTitle()));
        e.appendChild(createChildNode(theDoc, "qty", 
                String.valueOf(theEvent.getInfo().getCurrent(theEvent.getCategory()))));
        e.appendChild(createChildNode(theDoc, "goal", 
                String.valueOf(theEvent.getInfo().getGoal(theEvent.getCategory()))));
    }
    
    /**
     * Helper method to prepare a single User node as a subtree of root.
     * 
     * @param theDoc Document object for creating Elements and Nodes
     * @param theRoot reference to the root node
     * @param theUser User object for extracting units of information
     */
    private static void addUserChild(Document theDoc, Element theRoot, User theUser)
    {
        Element e = theDoc.createElement("user");
        theRoot.appendChild(e);
        e.appendChild(createChildNode(theDoc, "accountid", theUser.getAccountID()));
        e.appendChild(createChildNode(theDoc, "password", theUser.getPassword()));
        e.appendChild(createChildNode(theDoc, "organization", theUser.getOrganization()));
        e.appendChild(createChildNode(theDoc, "phone", theUser.getPhone()));
        e.appendChild(createChildNode(theDoc, "email", theUser.getEmail()));
        e.appendChild(createChildNode(theDoc, "role", theUser.getRole()));
    }
    
    /**
     * Helper method for creating inner child nodes (elements) that store units of data.
     * 
     * @param theDoc Document object for creating Elements and Nodes
     * @param theElemName name of element (tag) for unit of data
     * @param theElemValue the unit of data to be stored within element
     * @return reference to the resulting node
     */
    private static Node createChildNode(Document theDoc, String theElemName, String theElemValue)
    {
        Element s = theDoc.createElement(theElemName);
        s.appendChild(theDoc.createTextNode(theElemValue));
        return s;
    }
}
