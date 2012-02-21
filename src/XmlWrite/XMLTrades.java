/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XmlWrite;

import java.util.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.DOMException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


/**
 *
 * @author Olivier
 */

public class XMLTrades{



  
  private Document dom;
  
   private String SystemName;
    private double EntryPrice;
     private long EntryTicks = 0;
     private Date EntryDate;
     private String EntryHours ;
     private String EntryMinutes ;
     private String Operation;
     private double LimitPrice;
     private double StopPrice=0;
     private String Side;
     private String OrderType;
     private int TrailingPrice=1;
     private int TimeZone =0;
     private String DayOfYear;
     private int Multiplier = 1;
     private String MagicNumber ="1121111";
     private String Version = "1.3";
     private String Symbol;
     String DayOfWeek;
     List ArrayedData;
  /**
   * 
   * @param isbn
   * @param title
   * @param authorName
   */
  public XMLTrades(String systemName,String symbol,String operation, double entryPrice,double limitPrice, double stopPrice,String side, String ordertype,String magicNumber ) {
   
      this.EntryPrice=entryPrice;
      this.StopPrice=stopPrice;
      this.LimitPrice=limitPrice;
      this.SystemName= systemName;
      this.Side=side;
      this.OrderType = ordertype;
      this.MagicNumber = magicNumber;
      this.Symbol = symbol;
      this.Operation = operation;
      this.DayOfYear = GetDayOfYear().toString();
      this.EntryHours= GetEntryHour();
      this.EntryMinutes=GetEntryMinute();
      this.DayOfWeek = GetDayOfWeek();
      
  
      
      this.ArrayedData =  new ArrayList();
      this.ArrayedData.add(EntryPrice);
      this.ArrayedData.add(StopPrice);
      this.ArrayedData.add(LimitPrice);   
    
            
      
      
  }

  /**
   * 
 

  /**
   * 
   * @param title
   */

  
  
  public String ConvertDouble(Double thedouble)
  {
      
       NumberFormat nf = NumberFormat.getInstance();
       nf.setMaximumFractionDigits(6);     
       String myNumber = nf.format(thedouble);
      return myNumber;
  }
  
  public void SetSystemName(String systemname) {
    this.SystemName = systemname;
  }
 public void SetSysmbol(String systemname) {
    this.Symbol = systemname;
  }
  public String GetSymbol() {
     
     
      if (this.Symbol.contains("/"))
      {
      String [] temp = this.Symbol.split("/");
      
      String concats = temp[0].concat(temp[1]);
      return concats;
      }
      return this.Symbol;
      
  }
    public String GetSystem() {
    return this.SystemName;
  }
   public void SetOperation(String systemname) {
    this.Operation = systemname;
  }
  public String GetOperation() {
    return this.Operation;
  } 
    
  public String GetSide() {
    return this.Side;
  }
      
    
  public String GetMagicNumber() {
    return this.MagicNumber;
  }
    public void SetMagicNumber(String p) {
     this.MagicNumber = p;
  }
     
  public String GetVersion() {
    return this.Version;
  }
  
   public void SetVersion(String p) {
     this.Version = p;
  }
  
  public void SetSide(String side) {
    this.Side = side;
  }
      
  public String GetOrderType() {
    return this.OrderType;
  }
      
  public void SetOrderType(String side) {
    this.OrderType = side;
  }
    public void SetEntryPrice(double systemname) {
    this.EntryPrice = systemname;
  }
  public void SetStopPrice(double systemname) {
    this.StopPrice = systemname;
  }
  public void SetLimitPrice(double systemname) {
    this.LimitPrice = systemname;
  }
  public double GetEntryPrice() {
    return this.EntryPrice;
  }
    public double GetStopPrice() {
    return this.StopPrice;
  }
    
    public double GetLimitPrice() {
    return this.LimitPrice;
  }    
     public String GetEntryPriceAsString() {
    return ConvertDouble(this.EntryPrice);
  }
      public String GetStopPriceAsString() {
    return ConvertDouble(this.StopPrice);
  }
      
        public String GetLimitPriceAsString() {
    return ConvertDouble(this.LimitPrice);
  }
        
  public String GetEntryHour()
  {   
      
      Calendar rightNow = Calendar.getInstance();
       NumberFormat nf = NumberFormat.getInstance();
       nf.setMaximumFractionDigits(0);     
       String myNumber = nf.format(rightNow.HOUR);
      return myNumber;
      
       
      
  }
  
  
  
     /**
 * Gets the entry minute. 
       */
      public String GetEntryMinute()
  {     
      Calendar rightNow = Calendar.getInstance();
       NumberFormat nf = NumberFormat.getInstance();
       nf.setMaximumFractionDigits(0);     
       String myNumber = nf.format(rightNow.MINUTE);
      return myNumber;
       
  }
      
                               
   /**
 * Gets the day of the week. 
       */
  public String GetDayOfWeek()
  {      
    
      
      
       Calendar rightNow = Calendar.getInstance();
       NumberFormat nf = NumberFormat.getInstance();
       nf.setMaximumFractionDigits(0);     
       String myNumber = nf.format(rightNow.DAY_OF_WEEK);
      return myNumber;
  } 
   /**
 * Gets the day of the year. 
       */
  public String GetDayOfYear()
  {      
       Calendar rightNow = Calendar.getInstance();
       NumberFormat nf = NumberFormat.getInstance();
       nf.setMaximumFractionDigits(0);     
       String myNumber = nf.format(rightNow.DAY_OF_YEAR);
      return myNumber;
  } 
  
  public String GetTimeAsTick()
  {    
       Calendar rightNow = Calendar.getInstance();
       NumberFormat nf = NumberFormat.getInstance();
       nf.setMaximumFractionDigits(0);     
       String myNumber = nf.format(rightNow.getTimeInMillis());
      return myNumber;
      
  }
  
  
  /**
 * creates an element from a Trade object.
   * This element is used to write the Xml file (Serialized object).
 * The url argument must specify an absolute  
                          

 
                          
@param  the serialized trade object.
 *  
                          
@return      the root element with all child's appended.
 *  
                        
 */
   public Element createBookElement2(XMLTrades b){

         try{
            createDocument();
            Element TradeRootElement = dom.createElement("Trade");
            WriteElmements(TradeRootElement);

             
              
              //  (String Name , String value,Element RootElement)
                //Add symbol
                AppendElmementInDoc("Symbol",b.GetSymbol(),TradeRootElement);
                //Add operation
                AppendElmementInDoc("Operation",b.GetOperation(),TradeRootElement);
                //Add System 
                 AppendElmementInDoc("System",b.GetSystem(),TradeRootElement);
                  //Lets add the EntryPrice element.
                AppendElmementInDoc("EntryPrice",b.GetEntryPriceAsString(),TradeRootElement);
                //Lets add the StopPrice element.
                AppendElmementInDoc("StopPrice",b.GetStopPriceAsString(),TradeRootElement);
                //Limit Price
                AppendElmementInDoc("LimitPrice",b.GetLimitPriceAsString(),TradeRootElement);
                //Side
                AppendElmementInDoc("Side",b.GetSide(),TradeRootElement);
                //OrderType		 
                AppendElmementInDoc("OrderType",b.GetOrderType(),TradeRootElement);
                //TrailingStop (inactive for now).
                AppendElmementInDoc("TrailingStop","1",TradeRootElement);
                
                //TimeZone
                AppendElmementInDoc("TimeZone","0",TradeRootElement);
                //OrderType		 
                AppendElmementInDoc("DayOfYear",b.GetDayOfYear(),TradeRootElement);
                 //OrderType		 
                AppendElmementInDoc("DayOfWeek",b.GetDayOfWeek(),TradeRootElement);
                 //OrderType		 
                AppendElmementInDoc("Multiplier","1",TradeRootElement);
                 //OrderType		 
                AppendElmementInDoc("Version",b.GetVersion(),TradeRootElement);
                
                //Lets return the root element.
                return TradeRootElement;
           }
           
           catch(Exception ex){
			//dump it
			System.out.println("Error while trying to instantiate DocumentBuilder "+ex );
			System.exit(1);
		}
 
                
                return null;

	}

    private void WriteElmements(Element TradeRootElement) throws DOMException {
        dom.appendChild(TradeRootElement);

        //No enhanced for
    }
    
   /**
 * A helper method (private) , which appends values and strings to the root element 
  */   
  private void AppendElmementInDoc(String Name , String value,Element RootElement) throws DOMException {
      
                 //create Multiplier element and title text node and attach it to bookElement
		Element Multi = dom.createElement(Name);
		Text MultiText = dom.createTextNode(value);
		Multi.appendChild(MultiText);
		RootElement.appendChild(Multi);
       // Node appendChild = dom.appendChild(RootElement);

       
    }

  
 /**
 * A helper method (private) , which writes the trade object to file                          
@param  the string (wherer to write the xml file).
 *   */ 
public void pTradesToFile(String path){
                        try{
		        OutputFormat format = new OutputFormat(dom);
			format.setIndenting(true);

                       
                        
			//to generate output to console use this serializer
			XMLSerializer serializer = new XMLSerializer(System.out, format);
serializer.serialize(dom);
 

			//to generate a file output use fileoutputstream instead of system.out
                         serializer = new XMLSerializer(
			new FileOutputStream(new File(path)), format);

			serializer.serialize(dom);

		} 
                catch(IOException ie) {
		    ie.printStackTrace();
		}
                  
 
                }
         
  
/**Creates the xml document. */
 private void createDocument() {

		//get an instance of factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
		//get an instance of builder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//create an instance of DOM
		dom = db.newDocument();

                
             
                
		}catch(ParserConfigurationException pce) {
			//dump it
			System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
			System.exit(1);
		}

	}





}

