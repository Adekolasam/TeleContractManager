
package Dev;

/**
 *
 * Contract Manager Interface
 */
public interface IContractManager {
    
    /**
    * @param  name  the of the client.
    * @param  packge the call package type
    * @param  dataB the data bundle plan
    * @param  ref the contract reference
    * @param  period the contract period
    * @param  inclIntlCalls determine if international calls should be included in the contract
    */
    public void createNewContract(String name, int packge, int dataB, String ref, int period, boolean inclIntlCalls);
    
    
    /**
    * @param  contractDate date contract was created. you can use default or specify what to be displayed
    */
    public void displayContractSummary(String contractDate);
    
    /**
    */
    public void writeToFile();
    
    /**
    * @param  opt  file option - main or archive
    * @return      String
    */
    public String getSummaryOfContracts(int opt);
    
    /**
    * @param  opt  file option - main or archive
    * @param  month  selected calendar month
    * @return      String
    */
    public String getSummaryOfContracts(int opt, int month);
    
    /**
    * @param  opt  file option - main or archive
    * @param  search  search keyword, reference or client name
    */
    public void getSummaryOfContracts(int opt, String search);
    
}
