
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ibrahim
 */
public class Parser {
    public static final String FileName="C:\\Users\\Ibrahim\\OneDrive - Cairo University - Students\\Documents\\NetBeansProjects\\WebApplication1\\src\\java\\newXMLDocument.xml";
    static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    public static void main(String args[]) throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException, IOException{
        Scanner in=new Scanner(System.in);
        boolean x=true;
        String Parse;
        int choice;
        while(x){
            System.out.println("choose which operation you want to do (Enter the number of the operation)");
            System.out.println("1- Insert CD's ");
            System.out.println("2- Search Specific CD title");
            System.out.println("3- Search Specific CD Artist");
            System.out.println("4- Search All CD's");
            System.out.println("5- Exit");
            Parse = in.next();
            choice = Integer.parseInt(Parse);
            if(choice == 1){
                insert();
            }
            else if(choice == 2){
                TitleSearch();
            }
            else if(choice == 3){
                ArtistSearch();
            }
            else if(choice == 4){
                Search();
            }
            else if(choice == 5){
                System.out.println("End");
                break;
            }
            else{
                System.out.println("invalid input");
            }
                    
        }
       
    
}
    public static void insert() throws TransformerConfigurationException, TransformerException, ParserConfigurationException{
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();   
        Scanner in=new Scanner(System.in);
        Element Catalogue = doc.createElement("CATALOGUE");
        doc.appendChild(Catalogue);
        System.out.println("How many CD's You want to enter");
        String x=in.nextLine();
        int l=Integer.parseInt(x);
        for(int i=0;i<l;i++){
            Element CD = doc.createElement("CD");
            Catalogue.appendChild(CD);
            System.out.println("Enter the CD Title");
            String TiTle = in.nextLine();
            Element Title = doc.createElement("Title");
            Title.appendChild(doc.createTextNode(TiTle));
            CD.appendChild(Title);
            System.out.println("Enter the CD Artist");
            String Artist = in.nextLine();
            Element artist = doc.createElement("Artist");
            artist.appendChild(doc.createTextNode(Artist));
            CD.appendChild(artist);
            System.out.println("Enter the CD Country");
            String Country = in.nextLine();
            Element country = doc.createElement("Country");
            country.appendChild(doc.createTextNode(Country));
            CD.appendChild(country);
            System.out.println("Enter the CD Company");
            String Company = in.nextLine();
            Element company = doc.createElement("Company");
            company.appendChild(doc.createTextNode(Company));
            CD.appendChild(company);
            System.out.println("Enter the CD price");
            String Price = in.nextLine();
            Element price = doc.createElement("Price");
            price.appendChild(doc.createTextNode(Price));
            CD.appendChild(price);
            System.out.println("Enter the CD Year");
            String Year = in.nextLine();
            Element year = doc.createElement("Year");
            year.appendChild(doc.createTextNode(Year));
            CD.appendChild(year);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(FileName));
            transformer.transform(domSource, streamResult);
        }
    
    }
    public static void ArtistSearch() throws ParserConfigurationException, SAXException, IOException{
        
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        doc = db.parse(FileName);
        Scanner in = new Scanner(System.in);
        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
        System.out.println("write the Artist you want to search");
        String ArTist = in.nextLine();
         NodeList list = doc.getElementsByTagName("CD");
          if(list.getLength()!=0){
         for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);
              
              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;
                  if(element.getElementsByTagName("Artist").item(0).getTextContent().equals(ArTist)){
                  

                  // get text
                  String TiTle = element.getElementsByTagName("Title").item(0).getTextContent();
                  String Artist = element.getElementsByTagName("Artist").item(0).getTextContent();
                  String Country = element.getElementsByTagName("Country").item(0).getTextContent();
                  String Company = element.getElementsByTagName("Company").item(0).getTextContent();
                  String Price = element.getElementsByTagName("Price").item(0).getTextContent();
                  String  Year = element.getElementsByTagName("Year").item(0).getTextContent();
                  
                  

                  System.out.println("Current Element :" + node.getNodeName());
                
                  System.out.println("Title : " + TiTle);
                  System.out.println("Artist : " + Artist);
                  System.out.println("Country : " + Country);
                  System.out.println("Company : "+ Company);
                  System.out.println("Price : "+ Price);
                  System.out.println("Year : "+ Year);
                  
                  }
                  else{
                      System.out.println("This Artist Doesn't exist in the CD's");
                              
                  }

              }
         }
    }
           else{
                      System.out.println("There Are no CD's in the Catalogue");
                              
                  }
        

    
    
    }
    public static void  Search() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        doc = db.parse(FileName);
        Scanner in = new Scanner(System.in);
        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
         NodeList list = doc.getElementsByTagName("CD");
         if(list.getLength()!=0){
         for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);
              
              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;
                  

                  // get text
                  String TiTle = element.getElementsByTagName("Title").item(0).getTextContent();
                  String Artist = element.getElementsByTagName("Artist").item(0).getTextContent();
                  String Country = element.getElementsByTagName("Country").item(0).getTextContent();
                  String Company = element.getElementsByTagName("Company").item(0).getTextContent();
                  String Price = element.getElementsByTagName("Price").item(0).getTextContent();
                  String  Year = element.getElementsByTagName("Year").item(0).getTextContent();
                  
                  

                  System.out.println("Current Element :" + node.getNodeName());
                
                  System.out.println("Title : " + TiTle);
                  System.out.println("Artist : " + Artist);
                  System.out.println("Country : " + Country);
                  System.out.println("Company : "+ Company);
                  System.out.println("Price : "+ Price);
                  System.out.println("Year : "+ Year);
                  
                  
              }
         }
        }
         else{
                      System.out.println("There Are no CD's in the Catalogue");
                              
                  }
         
    }
    
    
    public static void  TitleSearch() throws ParserConfigurationException, SAXException, IOException {
    
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        doc = db.parse(FileName);
        Scanner in = new Scanner(System.in);
        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
        System.out.println("write the title you want to search");
        String Title = in.nextLine();
         NodeList list = doc.getElementsByTagName("CD");
         if(list.getLength()!=0){
         for (int temp = 0; temp < list.getLength(); temp++) {

              Node node = list.item(temp);
              
              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;
                  if(element.getElementsByTagName("Title").item(0).getTextContent().equals(Title)){
                  

                  // get text
                  String TiTle = element.getElementsByTagName("Title").item(0).getTextContent();
                  String Artist = element.getElementsByTagName("Artist").item(0).getTextContent();
                  String Country = element.getElementsByTagName("Country").item(0).getTextContent();
                  String Company = element.getElementsByTagName("Company").item(0).getTextContent();
                  String Price = element.getElementsByTagName("Price").item(0).getTextContent();
                  String  Year = element.getElementsByTagName("Year").item(0).getTextContent();
                  
                  

                  System.out.println("Current Element :" + node.getNodeName());
               
                  System.out.println("Title : " + TiTle);
                  System.out.println("Artist : " + Artist);
                  System.out.println("Country : " + Country);
                  System.out.println("Company : "+ Company);
                  System.out.println("Price : "+ Price);
                  System.out.println("Year : "+ Year);
                  
                  }
                  else{
                      System.out.println("This Title not Existing in the CD's");
                              
                  }
              }
         }
    }
          else{
                      System.out.println("There Are no CD's in the Catalogue");
                              
                  }
    }
}