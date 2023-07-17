
package Dev;

/**
 * Charity Customer : subclass of Contract
 * 
 */
public class CharityCustomer extends Contract {
    
    /**
    * @param  name  the of the client.
    * @param  packge the call package type
    * @param  dataB the data bundle plan
    * @param  ref the contract reference
    * @param  period the contract period
    * @param  inclIntlCalls determine if international calls should be included in the contract
    */
    CharityCustomer(String name, int packge, int dataB, String ref, int period, boolean inclIntlCalls){
        super(name, packge, dataB, ref, period, inclIntlCalls);
        customerType = "Charity";
    }
    
    /**
    * @return  int
    */
    @Override
    public int getDiscountRate() {
        return 30;
    }
    
}
