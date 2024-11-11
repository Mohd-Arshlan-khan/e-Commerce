package Com.Package.model;

public class Cart extends Product {
     private int quantity;
     
     public Cart() {}
     
     public int getQuantity() {
    	 return quantity;
     }
     
     public void stQuantity(int quantity) {
    	 this.quantity = quantity;
     }
      
}
