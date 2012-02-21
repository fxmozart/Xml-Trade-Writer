/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package XmlWrite;

import com.dukascopy.api.IMessage;
import com.dukascopy.api.IOrder;
import java.text.NumberFormat;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.DOMException;

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
public class XMLTrades {

    public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";
    private Document dom;
    private String SystemName;
    private double EntryPrice;
    private long EntryTicks = 0;
    private Calendar EntryDate;
    private String EntryHours;
    private String EntryMinutes;
    private String Operation;
    private double LimitPrice;
    private double StopPrice = 0;
    private String Side;
    private String OrderType;
    private int TrailingPrice = 1;
    private int TimeZone = 0;
    private String DayOfYear;
    private int Multiplier = 1;
    private String MagicNumber = "1121111";
    private String Version = "1.3";
    private String Symbol;
    String DayOfWeek;
    String FileLocation = "c:\\TradingSystems\\Tests\\Trades1.xml";

    /**
     * 
     * @param isbn
     * @param title
     * @param authorName
     */
    public XMLTrades(String systemName, String symbol, String operation, double entryPrice, double limitPrice, double stopPrice, String side, String ordertype, String magicNumber, IMessage message) {

        this.EntryPrice = entryPrice;
        this.StopPrice = stopPrice;
        this.LimitPrice = limitPrice;
        this.SystemName = systemName;
        this.Side = side;
        this.OrderType = ordertype;
        this.MagicNumber = magicNumber;
        this.Symbol = symbol;
        this.Operation = operation;
        this.DayOfYear = GetDayOfYear().toString();
        this.EntryHours = GetEntryHour();
        this.EntryMinutes = GetEntryMinute();
        this.DayOfWeek = GetDayOfWeek();

        this.EntryDate = Calendar.getInstance();

        this.ParseMessageToTradeXML(message, Operation);


    }

    /**
     * 
    
    
    /**
     * 
     * @param title
     */
    public String ConvertDouble(Double thedouble) {

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


        if (this.Symbol.contains("/")) {
            String[] temp = this.Symbol.split("/");

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

    public String GetEntryDate() {


        return (EntryDate.get(Calendar.YEAR) + "-" + EntryDate.get(Calendar.MONTH) + "-" + EntryDate.get(Calendar.DAY_OF_MONTH));


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

    public String GetEntryHour() {
        Calendar rightNow = Calendar.getInstance();
        return String.valueOf(rightNow.get(Calendar.HOUR_OF_DAY));


    }

    /**
     * Gets the entry minute. 
     */
    public String GetEntryMinute() {
        Calendar rightNow = Calendar.getInstance();
        return String.valueOf(rightNow.get(Calendar.MINUTE));

    }

    /**
     * Gets the day of the week. 
     */
    public String GetDayOfWeek() {

        Calendar rightNow = Calendar.getInstance();
        return String.valueOf(rightNow.get(Calendar.DAY_OF_WEEK));
    }

    /**
     * Get the current day of the year (when trade was opened).               
    @param  None.
     *  
    
    @return     a string with day of the year (int)..
     *                
     */
    public String GetDayOfYear() {
        Calendar rightNow = Calendar.getInstance();
        return String.valueOf(rightNow.get(Calendar.DAY_OF_YEAR));


    }

    /**
     * Get the current time (of the trade) as milliseconds).                 
    @param  None.
     *  
    
    @return     a string with time trade time as long (milliseconds).
     *                
     */
    public String GetTimeAsTick() {
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
    public Element createBookElement2(XMLTrades b) {

        try {
            //get an instance of factory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
                //get an instance of builder
                DocumentBuilder db = dbf.newDocumentBuilder();

                //create an instance of DOM
                dom = db.newDocument();




            } catch (ParserConfigurationException pce) {
                //dump it
                System.out.println("Error while trying to instantiate DocumentBuilder " + pce);
                System.exit(1);
            }




            Element TradeRootElement = dom.createElement("Trade");
            dom.appendChild(TradeRootElement);
            //  (String Name , String value,Element RootElement)
            //Add symbol
            AppendElmementInDoc("Symbol", b.GetSymbol(), TradeRootElement);
            //Add operation
            AppendElmementInDoc("Operation", b.GetOperation(), TradeRootElement);
            //Add System 
            AppendElmementInDoc("System", b.GetSystem(), TradeRootElement);
            //Lets add the EntryPrice element.
            AppendElmementInDoc("EntryPrice", b.GetEntryPriceAsString(), TradeRootElement);
            //Lets add the StopPrice element.
            AppendElmementInDoc("StopPrice", b.GetStopPriceAsString(), TradeRootElement);
            //Limit Price
            AppendElmementInDoc("LimitPrice", b.GetLimitPriceAsString(), TradeRootElement);
            //Side
            AppendElmementInDoc("Side", b.GetSide(), TradeRootElement);
            //OrderType		 
            AppendElmementInDoc("OrderType", b.GetOrderType(), TradeRootElement);
            //TrailingStop (inactive for now).
            AppendElmementInDoc("TrailingStop", "1", TradeRootElement);

            //TimeZone
            AppendElmementInDoc("TimeZone", "0", TradeRootElement);
            //OrderType		 
            AppendElmementInDoc("DayOfYear", b.GetDayOfYear(), TradeRootElement);
            //OrderType		 
            AppendElmementInDoc("DayOfWeek", b.GetDayOfWeek(), TradeRootElement);
            //OrderType		 
            AppendElmementInDoc("Multiplier", "1", TradeRootElement);

            //OrderType		 
            AppendElmementInDoc("MagicNumber", b.GetMagicNumber(), TradeRootElement);

            //EntryHour		 
            AppendElmementInDoc("EntryHours", b.GetEntryHour(), TradeRootElement);
            //Entry Minutes
            AppendElmementInDoc("EntryMinutes", b.GetEntryMinute(), TradeRootElement);

            //Entry Time as Ticks
            AppendElmementInDoc("Ticks", b.GetTimeAsTick(), TradeRootElement);

            //Entry Minutes
            AppendElmementInDoc("EntryDate", b.GetEntryDate(), TradeRootElement);



            //OrderType		 
            AppendElmementInDoc("Version", b.GetVersion(), TradeRootElement);

            //Lets return the root element.
            return TradeRootElement;
        } catch (Exception ex) {
            //dump it
            System.out.println("Error while trying to instantiate DocumentBuilder " + ex);
            System.exit(1);
        }


        return null;

    }

    /**
     * A helper method (private) , which appends values and strings to the root element 
     */
    private void AppendElmementInDoc(String Name, String value, Element RootElement) throws DOMException {

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
    private void ParseMessageToTradeXML(IMessage message, String Operation) {
        //Lets get the various stuff we need from the order..
        String curSide;
        if (message.getOrder().isLong()) {
            curSide = "Buy";
        } else {
            curSide = "Sell";
        }
        String systemName = this.SystemName;

        String symbol = message.getOrder().getInstrument().toString();
        String CurrentOperation = Operation;
        String OrderType = "Market";
        String Magic = this.MagicNumber;

        double entryPrice = message.getOrder().getOpenPrice();
        double StopPrice = message.getOrder().getStopLossPrice();
        double LimitPrice = message.getOrder().getTakeProfitPrice();
        // (String systemName,String symbol,String operation, double entryPrice,double limitPrice, double stopPrice,String side, String ordertype,String magicNumber ) {


        if (Operation.equals("Close")) {


            //Create the document.
            Element FullTrade = createBookElement2(this);
            //Write the message to file..
            pTradesToFile(this.FileLocation);
        }

        if (Operation.equals("Open")) {
            //Parse message to XmlTrade class.


            //Create the document.
            createBookElement2(this);
            //Write the message to file..
            pTradesToFile(this.FileLocation);
        }

    }

    public XMLTrades(String magicNumber, String SystemName, IMessage message) {




        IOrder lastOne = message.getOrder();

        this.EntryPrice = lastOne.getOpenPrice();
        this.StopPrice = lastOne.getStopLossPrice();
        this.LimitPrice = lastOne.getTakeProfitPrice();
        this.SystemName = SystemName;
        this.DayOfYear = GetDayOfYear().toString();
        this.EntryHours = GetEntryHour();
        this.EntryMinutes = GetEntryMinute();
        this.DayOfWeek = GetDayOfWeek();

        this.EntryDate = Calendar.getInstance();
        String curSide;
        if (message.getOrder().isLong()) {
            curSide = "Buy";
        } else {
            curSide = "Sell";
        }


        this.Side = curSide;
        this.OrderType = "Market";
        this.MagicNumber = magicNumber;
        this.Symbol = message.getOrder().getInstrument().toString();
        System.out.println("Received order before parsing it......");


        if (message.getType().equals(IMessage.Type.ORDER_CLOSE_OK)) {

            String Op = "Close";
            this.Operation = Op;
            this.ParseMessageToTradeXML(message, Operation);
            return;
        }



        //We can change SL only for FILLED and OPENED orders
        if (lastOne.getClosePrice() != 0) {


            String Op = "Close";
            this.Operation = Op;
            this.ParseMessageToTradeXML(message, Operation);
            return;
        }
        if (message.getType().equals(IMessage.Type.ORDER_FILL_OK)) {



            String Op = "Open";
            this.Operation = Op;
            this.ParseMessageToTradeXML(message, Operation);
            return;
        }



    }

    private void pTradesToFile(String path) {
        try {
            OutputFormat format = new OutputFormat(dom);
            format.setIndenting(true);


            //to generate output to console use this serializer
            XMLSerializer serializer = new XMLSerializer(System.out, format);
            serializer.serialize(dom);



            //to generate a file output use fileoutputstream instead of system.out
            serializer = new XMLSerializer(
                    new FileOutputStream(new File(path)), format);

            serializer.serialize(dom);
            dom = null;

        } catch (IOException ie) {
            ie.printStackTrace();
        }


    }
}
