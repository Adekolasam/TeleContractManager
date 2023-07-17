
package Dev;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.HashMap;
import java.util.Scanner;

/**
* ContractManager implements IContractManager
* Abstract Contract implementation
*/
public class ContractManager implements IContractManager {
    private final String _contractFile = "contracts.txt";
    private final String _archiveFile = "archive.txt";
    
    //private HashMap<Integer,Object> contracts = new HashMap<>();
    //private int pointer;
    Contract newContract;
    
    
    //summary Variables
    private int totalNoOfContracts = 0;
    private int highUnlimitedContracts = 0;
    private int noOfLargePackages = 0;
    private int largePackages = 0;
    private int avgChargeForLargePackages = 0;
    
    private final String months[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
    
//    void ContractManager(){
//        //pointer = -1;
//    }
    
    /**
    * @param  name  the of the client.
    * @param  packge the call package type
    * @param  dataB the data bundle plan
    * @param  ref the contract reference
    * @param  period the contract period
    * @param  inclIntlCalls determine if international calls should be included in the contract
    */
    @Override
    public void createNewContract(String name, int packge, int dataB, String ref, int period, boolean inclIntlCalls){
        
        String ctype = ref.substring(5,6);
        String customerType = "";
        
        if("B".equals(ctype)) customerType = "Business";
        else if("N".equals(ctype)) customerType = "Non-Business";
        else customerType = "Charity";
        
        //pointer += pointer + 1;
        switch(ctype){
            case "B" ->{
                newContract = new BusinessCustomer(name, packge, dataB, ref, period, inclIntlCalls);
                //contracts.put(pointer, businessCustomer);
                //BusinessCustomer businessCustomer = (BusinessCustomer) contracts.get(pointer);
            }
            case "N" ->{
                newContract = new NonBusinessCustomer(name, packge, dataB, ref, period, inclIntlCalls);          
                //contracts.put(pointer, nonBusinessCustomer);
            }
            default ->{
                newContract = new CharityCustomer(name, packge, dataB, ref, period, inclIntlCalls);
                //contracts.put(pointer, charityCustomer);
            }
        }
        
    }
    
    /**
    * @param  contractDate date contract was created. you can use default or specify what to be displayed
    */
    @Override
    public void displayContractSummary(String contractDate){
        int discount = newContract.getDiscountRate();
        String discountTag = (discount > 0) ? "Discounted" : "None" ;
        
        contractDate = contractDate != null ? contractDate : newContract.getDate();
        
        String charge = poundsFormat(newContract.discountedPrice);
        
        String summary =  "\n";
        summary += "+--------------------------------------------+\n" +
            "|                                            |\n" +
            "| Customer: " + newContract.clientName + "                          |\n" +
            "|                                            |\n" +
            "|      Ref: " + newContract.reference + "         Date: " + contractDate + "  |\n" +
            "|  Package: " + newContract.getPackageSize() + "   Data: " + newContract.getDataBundle() + "   |\n" +
            "|   Period: " + newContract.getContractPeriod() + " Months      Type: " + newContract.customerType + "    |\n" +
            "|                                            |\n" +
            "| Discount: " + discount + "%     Intl. Calls: " + (newContract.includeIntlCalls ? "Yes" : "No") + "          |\n" +
            "|                                            |\n" +
            "|      " + discountTag + " Monthly Charge: " + charge + "      |\n" +
            "|                                            |\n" +
            "+--------------------------------------------+";
        summary +=  "\n";
        
        System.out.println(summary);
    }
    
    private String poundsFormat(int amt){
        return String.format("£%.2f ", (float)(amt / 100));
    }
    
    /**
    */
    @Override
    public void writeToFile(){
        try{
            
            String txt = newContract.getDate() + "\t"
                    + newContract.packge + "\t"
                    + newContract.dataBundle + "\t"
                    + newContract.period + "\t"
                    + (newContract.includeIntlCalls ? "Yes" : "No") + "\t"
                    + newContract.reference + "\t"
                    + newContract.totalPrice + "\t"
                    + newContract.clientName + "\t";
            
            FileWriter fw = new FileWriter(_contractFile,true);
            fw.write(txt+"\n");//appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
    
    /**
    * 
    */
    private void initComputeVariables(){
        totalNoOfContracts = 0;
        highUnlimitedContracts = 0;
        noOfLargePackages = 0;
        largePackages = 0;
    }
    
    /**
    * @param  opt  file option - main or archive
    * @return      int[]
    */
    private int[] computeSummaryOfContracts(int opt){
        int months[] = {0,0,0,0,0,0,0,0,0,0,0,0};
        
        try {
            File myObj = new File((opt == 1) ? _contractFile : _archiveFile);
            Scanner myReader = new Scanner(myObj);
            
            initComputeVariables();
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArr;
                int intData;
                
                if(data != ""){
                    dataArr = data.split("\t");
                    totalNoOfContracts++;
                    
                    intData = Integer.parseInt(dataArr[2]);
                    if(intData ==3 || intData == 4) highUnlimitedContracts++;
                    
                    intData = Integer.parseInt(dataArr[1]);
                    if(intData ==3) {
                        noOfLargePackages += 1;
                        largePackages += Integer.parseInt(dataArr[6]);
                    }
                    
                    String month = dataArr[0].split("-")[1];
                    switch (month) {
                        case "Jan" -> months[0]++;
                        case "Feb" -> months[1]++;
                        case "Mar" -> months[2]++;
                        case "Apr" -> months[3]++;
                        case "May" -> months[4]++;
                        case "Jun" -> months[5]++;
                        case "Jul" -> months[6]++;
                        case "Aug" -> months[7]++;
                        case "Sep" -> months[8]++;
                        case "Oct" -> months[9]++;
                        case "Nov" -> months[10]++;
                        case "Dec" -> months[11]++;
                        default -> {
                        }
                    }
                    
                }
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return months;
    }
    
    /**
    * @param  opt  file option - main or archive
    * @return      String
    */
    @Override
    public String getSummaryOfContracts(int opt){
        int months[] = computeSummaryOfContracts(opt);
        
        String outPut = getSummaryOfContracts() +
            "Jan  Feb  Mar  Apr  May  Jun  Jul  Aug  Sep  Oct  Nov  Dec\n";
        
        for(int month: months){
            outPut += month + "  ";
        }

        return outPut;
        
    }
    
    /**
    * @param  opt  file option - main or archive
    * * @param  month  specified month within a calendar year
    * @return      int
    */
    private int computeSummaryOfContracts(int opt, int month){
        int _month = 0;
        
        try {
            File myObj = new File((opt == 1) ? _contractFile : _archiveFile);
            Scanner myReader = new Scanner(myObj);
            
            initComputeVariables();
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArr;
                int intData;
                dataArr = data.split("\t");
                
                if(months[month-1].equals(dataArr[0].split("-")[1])){
                    totalNoOfContracts++;
                    
                    intData = Integer.parseInt(dataArr[2]);
                    if(intData ==3 || intData == 4) highUnlimitedContracts++;
                    
                    intData = Integer.parseInt(dataArr[1]);
                    if(intData ==3) {
                        noOfLargePackages += 1;
                        largePackages += Integer.parseInt(dataArr[6]);
                    }
                    
                    _month++;
                    
                }
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return _month;
    }
    
    /**
    * @param  opt  file option - main or archive
    * @param  month  selected calendar month
    * @return      String
    */
    @Override
    public String getSummaryOfContracts(int opt, int month){
        int _month = computeSummaryOfContracts(opt, month);
        
        if(_month > 0){
            String outPut = getSummaryOfContracts();
            outPut += this.months[month-1] + "\n";
            outPut += _month + "\n";
            return outPut;
        }
        else{
            return "No record found.";
        }
        
    }
    
    /**
    * @param  opt  file option - main or archive
    * @param  search  search keyword, reference or client name
    * @return      Boolean
    */
    private boolean findContracts(int opt, String search){
        boolean found = false;
        try {
            File myObj = new File((opt == 1) ? _contractFile : _archiveFile);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataArr;
                
                dataArr = data.split("\t");
                
                //if reference or name contains search word
                if(dataArr[5].contains(search.toUpperCase()) || dataArr[7].contains(search)){
                    createNewContract(
                            dataArr[7]
                            , Integer.parseInt(dataArr[1])
                            , Integer.parseInt(dataArr[2])
                            , dataArr[5]
                            , Integer.parseInt(dataArr[3])
                            , "Yes".equals(dataArr[4]) ? true : false
                    );
                    displayContractSummary(dataArr[0]);
                    
                    if(!found) found = true;
                }
            }
            myReader.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return found;
    }
    
    /**
    * @param  opt  file option - main or archive
    * @param  search  search keyword, reference or client name
    */
    @Override
    public void getSummaryOfContracts(int opt, String search){
        if(!findContracts(opt, search)){
            System.out.println("No result found.");
        }
    }
    
    /**
    * 
    */
    private String getSummaryOfContracts(){
        try{
            float avgL = (largePackages > 0 || noOfLargePackages > 0) ? (largePackages/noOfLargePackages) : 0;
            String outPut = "Total Number of contracts: " + totalNoOfContracts + "\n" +
            "Contracts with High or Unlimited Data Bundles: " + highUnlimitedContracts + "\n" +
            "Average charge for large packages: " + avgL + "\n" +
            "\n" +
            "Number of contracts per Month:\n" +
            "\n";
                    
            return outPut;
        }
        catch(ArithmeticException e){
            return "Something went wrong.\n";
        }
        
    }
    
    
}
