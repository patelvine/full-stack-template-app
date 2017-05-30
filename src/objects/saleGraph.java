package objects;

import java.util.ArrayList;

/**
 * @author Jason Chen <difuchen@gmail.com>
 */
public class saleGraph {
	private ArrayList<Double> x;
	private ArrayList<Integer> y;
	/**
	 * @param x - list of x value (Double)
	 * @param y - list of y value (Integer)
	 */
	public saleGraph(ArrayList<Double> x, ArrayList<Integer> y) {
		this.x = x;
		this.y = y;
	}
	public ArrayList<Double> getX() {
		return x;
	}
	public ArrayList<Integer> getY() {
		return y;
	}
	@Override
	public String toString() {
		return "saleGraph [x=" + x + ", y=" + y + "]";
	}

}
