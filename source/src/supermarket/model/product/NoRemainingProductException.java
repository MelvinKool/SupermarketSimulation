package supermarket.model.product;

public class NoRemainingProductException extends Exception{
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage(){
		return "There is no remaining product";
	}
}
