
package Dev;

/**
 * BusinessCustomer : subclass of Contract
 * 
 */
public class BusinessCustomer extends Contract {
    
    /**
    * @param  name  the of the client.
    * @param  packge the call package type
    * @param  dataB the data bundle plan
    * @param  ref the contract reference
    * @param  period the contract period
    * @param  inclIntlCalls determine if international calls should be included in the contract
    */
    BusinessCustomer(String name, int packge, int dataB, String ref, int period, boolean inclIntlCalls){
        super(name, packge, dataB, ref, period, inclIntlCalls);
        customerType = "Business";
    }
    
    /**
    * @return  int
    */
    @Override
    public int getDiscountRate() {
        return (period > 1 && period <= 4) ? 10 : 0;
    }
    
}
