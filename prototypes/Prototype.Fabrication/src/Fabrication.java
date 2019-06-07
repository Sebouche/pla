
import java.util.Iterator;
import java.util.Set;


public class Fabrication  {
	
	
	
	
	public static void main(String[] args) {
		  
		Resource res=new Resource();
		
		 
		    /* ****Test**** */

	    System.out.printf("Affichage :");

	    Set<Integer> keys = res.Quantity();
	 
	    Iterator<Integer> itr = keys.iterator();
        Integer key=0;
	    while (itr.hasNext()) { 
	     
	       key = (Integer)itr.next();

	       System.out.println("Key: "+key+" & Value: "+res.ressource.get(key));
	    }
	     int tmp = 6;
	     int cle []= {1,2};
	     int value[]= {2,11};
	     for(int i =0; i<2; i++) {
	    	System.out.println("donne cle "+cle[i]+" donne valeur  "+value[i]); 
	  
	    	if ( res.Exist(cle[i], value[i]) ) {
              System.out.printf("you can use it to build \n");
	    }
	    else 
          System.out.printf("you can't use it to build \n");
}
	     res.construct(tmp, cle, value);
	    
	     
	}
}