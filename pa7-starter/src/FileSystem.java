import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileSystem {

    BST<String, FileData> nameTree;
    BST<String, ArrayList<FileData>> dateTree;
    DateComparator dc;
    
    // TODO
    public FileSystem() {

    }


    // TODO
    public FileSystem(String inputFile) {
    	// Add your code here
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                // Add your code here
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);

        }
    }


    // TODO
    public void add(String name, String dir, String date) {
    	
    }


    // TODO
    public ArrayList<String> findFileNamesByDate(String date) {

    }


    // TODO
    public FileSystem filter(String startDate, String endDate) {
 
    }
    
    
    // TODO
    public FileSystem filter(String wildCard) {

    }
    
    
    // TODO
    public List<String> outputNameTree(){

    }
    
    
    // TODO
    public List<String> outputDateTree(){

    }
    
    

    class DateComparator implements Comparator<String> {
    	
    	//TODO
    	public int compare(String date1, String date2) {

    	}
    }


}

