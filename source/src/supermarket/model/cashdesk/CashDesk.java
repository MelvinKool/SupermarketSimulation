package supermarket.model.cashdesk;

public class CashDesk {
	public int x, y;
	boolean isOpen;
	
	public CashDesk(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setOpen(boolean state){
		isOpen = state;
	}
	
	public boolean isOpen(){
		return isOpen;
	}
}
