
package Dev;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
* This is the program manager
* handles user input and most output interactions
*/
public class ProgramManager {
    private static Scanner _scanner; // = new Scanner(System.in);
    private static final IContractManager _contractManager = new ContractManager();
    private static int userMenuOption = -1;
    private static String clientName;
    private static int packge;
    private static int dataBundle;
    private static String reference;
    private static String cType;
    private static int period;
    private static boolean includeIntlCalls;
    
//    void MenuManager(){
//        
//    }
    
    /**
    * @return  void
    */
    public static void run(){
        while(userMenuOption != 0){
            setMainUserInput(); 
            evaluateUserInput();
        }
        
        System.out.println("You are now logged out!");
    }
    
    /**
    * @return  void
    */
    private static void displayMainMenu(){
        System.out.println("1.	Enter new Contract\n" +
                "2.	Display Summary of Contracts\n" +
                "3.	Display Summary of Contracts for Selected Month\n" +
                "4.	Find and display Contract\n" +
                "0.	Exit");
    }

    /**
    * @return  void
    */
    private static void setMainUserInput(){
        initScanner();
        try{
            displayMainMenu();
            System.out.print("Enter your menu option: ");
            userMenuOption = _scanner.nextInt();
        }
        catch(InputMismatchException exception){
            userMenuOption = -1;
            System.out.println("Invalid user input. try again!\n\n");
        }
        
    }
    
    /**
    * @return  void
    */
    @SuppressWarnings("empty-statement")
    private static void evaluateUserInput() {
        
        switch(userMenuOption){
            case 1 ->{
                initUserContractInputs();
                getClientName();//while(!GetClientName());
                while(!getPackageOption());
                while(!getDataBundeOption());
                while(!getRefOption());
                while(!getContractMonthOption());
                while(!getIncludeINTCalls());
                _contractManager.createNewContract(clientName, packge, dataBundle, reference, period, includeIntlCalls);
                _contractManager.displayContractSummary(null);
                _contractManager.writeToFile();
            }
            case 2 ->{
                while(!promptDisplaySummaryOption2());
            }
            case 3 ->{
                while(!promptDisplaySummaryOption3());
            }
            case 4 ->{
                while(!promptFindContract());
            }
            default -> {
                return;
            }
        }
    }
    
    
    //Option 1
    /**
    * @return  Boolean
    */
    private static boolean getClientName(){
            initScanner();
            System.out.print("Enter your name: ");
            clientName = _scanner.nextLine();
            return false;
    }
    
    /**
    * @return  Boolean
    */
    private static boolean getPackageOption(){
        try{
            initScanner();
            System.out.print("\nSelect a package (1=Small, 2=Medium and 3=Large): ");
            packge = _scanner.nextInt();
            
            if(!(packge >= 1 && packge <= 3)){
                packge = 0;
                System.out.println("Invalid user input. try again!");
                return false;
            }
            
            return true;
        }
        catch(InputMismatchException exception){
            packge = 0;
            System.out.println("Invalid user input. try again!");
            return false;
        }
    }
    
    /**
    * @return  Boolean
    */
    private static boolean getDataBundeOption(){
        try{
            initScanner();
            System.out.print("\nSelect a data bundle (1=Low, 2=Medium, 3=High and 4=Unlimited): ");
            dataBundle = _scanner.nextInt();
            
            if(!(dataBundle >= 1 && dataBundle <= 4)){
                packge = 0;
                System.out.println("Invalid user input. try again!");
                return false;
            }
            return true;
        }
        catch(InputMismatchException exception){
            dataBundle = 0;
            System.out.println("Invalid user input. try again!");
            return false;
        }
    }
    
    /**
    * @return  Boolean
    */
    private static boolean getRefOption(){
        initScanner();
        System.out.print("\nInput your reference: ");
        reference = _scanner.nextLine().toUpperCase();
        
        if(
            (reference.length() != 6) ||
            !isAllAlpha(reference.substring(0,2)) ||
            !isAllNum(reference.substring(2,5)) ||
            !isAllAlpha(reference.substring(5,6)) ||
            !("B".equals(reference.substring(5,6)) || "N".equals(reference.substring(5,6)))
            //
        ) {
            reference = "";
            System.out.println("Invalid user input. try again!");
            return false;

        }
        cType = reference.substring(5,6);
        return true;
    }
    
    /**
    * @return  Boolean
    */
    private static boolean isAllAlpha(String str) {
        //return str.matches("[a-zA-Z]+");
        return str.chars().allMatch(Character::isLetter);
    }
    
    /**
    * @return  Boolean
    */
    private static boolean isAllNum(String str) {
        //return str.matches("[0-9]+");
        return str.chars().allMatch(Character::isDigit);
    }
    
    /**
    * @return  Boolean
    */
    private static boolean getContractMonthOption(){
        //1, 12, 18 or 24 months.
        try{
            initScanner();
            System.out.print("\nSelect a contract period (1 = 1Month, 2= 12Months, 3=18Months, 4=24Months): ");
            period = _scanner.nextInt();
            
            if(!(period >= 1 && period <= 4)){
                period = 0;
                System.out.println("Invalid user input. try again!");
                return false;
            }
            
            return true;
        }
        catch(InputMismatchException exception){
            period = 0;
            System.out.println("Invalid user input. try again!");
            return false;
        }
    }
    
    /**
    * @return  Boolean
    */
    private static boolean getIncludeINTCalls(){
        try{
            initScanner();
            System.out.print("\nWould you like to include international calls? (0 = No, 1 = Yes): ");
            int ans = _scanner.nextInt();
            
            if(!(ans >= 0 && ans <= 1)){
                System.out.println("Invalid user input. try again!");
                return false;
            }
            
            includeIntlCalls = (ans  != 0);
            return true;
        }
        catch(InputMismatchException exception){
            System.out.println("Invalid user input. try again!");
            return false;
        }
    }
    
    /**
    * @return  void
    */
    private static void initScanner(){
        _scanner = new Scanner(System.in);
    }
    
    /**
    * @return  void
    */
    private static void initUserContractInputs(){
        clientName = "";
        packge = 0;
        dataBundle = 0;
        reference = "";
        period = 0;
        includeIntlCalls = false;
    }
    
    
    
    
    //user input option 2
    /**
    * @return  Boolean
    */
    private static boolean promptDisplaySummaryOption2(){
        try{
            initScanner();
            System.out.print("\nSelect your preferred contacts file (1=main, 2=archive): ");
            int preferredFile = _scanner.nextInt();
            
            if(!(preferredFile >= 1 && preferredFile <= 2)){
                System.out.println("Invalid user input. try again!");
                return false;
            }
            
            System.out.println("");
            System.out.println(_contractManager.getSummaryOfContracts(preferredFile));
            System.out.println("");
            return true;
        }
        catch(InputMismatchException exception){
            System.out.println("Invalid user input. try again!");
            return false;
        }
    }
    
    /**
    * @return  Boolean
    */
    private static boolean promptDisplaySummaryOption3(){
        try{
            initScanner();
            System.out.print("\nSelect your preferred contacts file (1=main, 2=archive): ");
            int preferredFile = _scanner.nextInt();
            
            if(!(preferredFile >= 1 && preferredFile <= 2)){
                System.out.println("Invalid user input. try again!");
                return false;
            }
            
            
            System.out.print("\nSelect a month(1=Jan, 2=Feb, 3=Mar, 4=Apr, 5=May, 6=Jun, 7=Jul, 8=Aug, 9=Sept, 10=Oct, 11=Nov, 12=Dec): ");
            int month = _scanner.nextInt();
            
            if(!(month >= 1 && month <= 12)){
                System.out.println("Invalid user input. try again!");
                return false;
            }
            
            System.out.println("");
            System.out.println(_contractManager.getSummaryOfContracts(preferredFile, month));
            System.out.println("");
            return true;
        }
        catch(InputMismatchException exception){
            System.out.println("Invalid user input. try again!");
            return false;
        }
    }
    
    /**
    * @return  Boolean
    */
    private static boolean promptFindContract(){
        try{
            initScanner();
            System.out.print("\nSelect your preferred contacts file (1=main, 2=archive): ");
            int preferredFile = _scanner.nextInt();
            
            if(!(preferredFile >= 1 && preferredFile <= 2)){
                System.out.println("Invalid user input. try again!");
                return false;
            }
            
            initScanner();
            System.out.print("\nInput a Reference or Name: ");
            String search = _scanner.nextLine();
            
            System.out.println("");
            _contractManager.getSummaryOfContracts(preferredFile, search);
            System.out.println("");
            return true;
        }
        catch(InputMismatchException exception){
            System.out.println("Invalid user input. try again!");
            return false;
        }
    }
    
}
