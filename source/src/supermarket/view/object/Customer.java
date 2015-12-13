package supermarket.view.object;

public class Customer extends PaintableObject{

	@Override
	public void paintObject(int x, int y, String text) {
		if(text != null){
			//draw the text
		}
	}

	@Override
	public void paintObject(int x, int y) {
		paintObject(x,y,null);
	}

}
