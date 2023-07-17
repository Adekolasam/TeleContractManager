
package Dev;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * 
 */
public class Contract {
    protected String clientName;
    protected int packge;
    protected int dataBundle;
    protected String reference;
    protected int period;
    protected boolean includeIntlCalls;
    
    String customerType;
    protected int totalPrice;
    protected int discountedPrice;
    //protected int monthlyRate;
    
    /**
    * @param  name  the of the client.
    * @param  packge the call package type
    * @param  dataB the data bundle plan
    * @param  ref the contract reference
    * @param  period the contract period
    * @param  inclIntlCalls determine if international calls should be included in the contract
    */
    Contract(String name, int packge, int dataB, String ref, int period, boolean inclIntlCalls){
        clientName = name;
        this.packge = packge;
        dataBundle = dataB;
        reference = ref;
        this.period = period;
        includeIntlCalls = inclIntlCalls;
        
        setPrices();
    }
    
    /**
    * @return      int
    */
    public int getDiscountRate(){
        return 0;
    }
    
    /**
    */
    public void setPrices(){
        int monthlyRate = 0;
        switch (packge) {
            case 1 -> {
                switch(dataBundle){
                    case 1: monthlyRate = 500;
                    case 2: monthlyRate = 700;
                    case 3: monthlyRate = 900;
                    default:
                }
            }
            case 2 -> {
                switch(dataBundle){
                    case 1: monthlyRate = 650;
                    case 2: monthlyRate = 850;
                    case 3: monthlyRate = 1050;
                    default:
                }
            }
            case 3 -> {
                switch(dataBundle){
                    case 1: monthlyRate = 850;
                    case 2: monthlyRate = 1050;
                    case 3: monthlyRate = 1250;
                    case 4: monthlyRate = 2000;
                    default:
                }
            }
            default -> {
                ;
            }
        }
        
        int discount = this.getDiscountRate();
//        int discountAmt = (discount > 0 ) ? (monthlyRate*(this.getDiscountRate()/100)) : 0;
        int intcallcharge = (includeIntlCalls == true ) ? (monthlyRate*(15/100)) : 0;
        totalPrice = monthlyRate + intcallcharge;
        
        int discountAmt = (discount > 0 ) ? (totalPrice*(this.getDiscountRate()/100)) : 0;
        discountedPrice = totalPrice - discountAmt;
        
//        return (monthlyRate + intcallcharge - discountAmt);
        
    }
    
    /**
    * @return      String
    */
    public String getPackageSize(){
        switch(packge){
            case 1: return "Small (300)";
            case 2: return "Medium (600)";
            case 3: return "Large (1200)";
            default: return "";
        }
    }
    
    /**
    * @return      string
    */
    public String getDataBundle(){
        switch(dataBundle){
            case 1: return "Low (1GB)";
            case 2: return "Medium (4GB)";
            case 3: return "High (8GB)";
            case 4: return "Unlimited";
            default: return "";
        }
    }
    
    /**
    * @return      int
    */
    public int getContractPeriod(){
        return switch (period) {
            case 1 -> 1;
            case 2 -> 12;
            case 3 -> 18;
            case 4 -> 24;
            default -> 0;
        };
    }
    
    /**
    * @return      String
    */
    public static String getDate()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        return sdf.format(cal.getTime());
    }

    
}
