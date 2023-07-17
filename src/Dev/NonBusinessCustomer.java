
package Dev;

/**
 * NonBusinessCustomer: subclass of Contract
 * 
 */
public class NonBusinessCustomer extends Contract {
    
    /**
    * @param  name  the of the client.
    * @param  packge the call package type
    * @param  dataB the data bundle plan
    * @param  ref the contract reference
    * @param  period the contract period
    * @param  inclIntlCalls determine if international calls should be included in the contract
    */
    NonBusinessCustomer(String name, int packge, int dataB, String ref, int period, boolean inclIntlCalls){
        super(name, packge, dataB, ref, period, inclIntlCalls);
        customerType = "Non-Business";
    }
    
    /**
    * @return  int
    */
    @Override
    public int getDiscountRate() {
        if(period == 2 || period == 3) return 5;
        else if(period == 4) return 10;
        else return 0;
    }
    
}
