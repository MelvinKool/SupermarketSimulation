package supermarket.model;

import java.util.Vector;

import databasemanager.DBStatement;

public class StockDataModel {
	
	public static Vector<Vector> getDepartmentStock(){
		return DBStatement.getDepartmentStock();
	}
	
	public static Vector<Vector> getPathStock(){
		return DBStatement.getPathStock();
	}
}
