package databasemanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBSetup {
	
	/**
	 * Sets up a clean database
	 */
	public void setupNewDatabase(){
		Connection con = DBConnection.getConnection();
		List<String> statements = new ArrayList<String>();
		Statement statement = null;
		PreparedStatement prepCashStatement = null;
		PreparedStatement prepStorageStatement = null;
		try{
			statement = con.createStatement();
			//drop old data/tables
			statements.add("DROP TABLE IF EXISTS Aankoop;");
			statements.add("DROP TABLE IF EXISTS Kassa;");
			statements.add("DROP TABLE IF EXISTS Werknemer;");
			statements.add("DROP TABLE IF EXISTS Klant;");
			statements.add("DROP TABLE IF EXISTS Magazijn;");
			statements.add("DROP TABLE IF EXISTS Product_Opgesteld_Afdeling;");
			statements.add("DROP TABLE IF EXISTS Product_Opgesteld_Pad;");
			statements.add("DROP TABLE IF EXISTS Groep_Voorkeur_Product;");
			statements.add("DROP TABLE IF EXISTS Product;");
			statements.add("DROP TABLE IF EXISTS Afdeling;");
			statements.add("DROP TABLE IF EXISTS Pad;");
			statements.add("DROP TABLE IF EXISTS Groep;");
			//create the tables
			statements.add("CREATE TABLE IF NOT EXISTS Groep(groepid INT NOT NULL auto_increment PRIMARY KEY, naam varchar(50));");
			statements.add("CREATE TABLE IF NOT EXISTS Pad(padid INT NOT NULL auto_increment PRIMARY KEY, naam varchar(50));");
			statements.add("CREATE TABLE IF NOT EXISTS Afdeling(afdelingid INT NOT NULL auto_increment PRIMARY KEY, naam varchar(50));");
			statements.add("CREATE TABLE IF NOT EXISTS Product(productid INT NOT NULL auto_increment PRIMARY KEY, naam varchar(100),prijs DECIMAL);");
			statements.add("CREATE TABLE IF NOT EXISTS Groep_Voorkeur_Product(groepid INT NOT NULL, productid INT NOT NULL, FOREIGN KEY(productid) REFERENCES Product(productid), FOREIGN KEY(groepid) REFERENCES Groep(groepid));");
			statements.add("CREATE TABLE IF NOT EXISTS Product_Opgesteld_Pad(productid INT NOT NULL, padid INT NOT NULL, aantal int, FOREIGN KEY(productid) REFERENCES Product(productid), FOREIGN KEY(padid) REFERENCES Pad(padid));");
			statements.add("CREATE TABLE IF NOT EXISTS Product_Opgesteld_Afdeling(productid INT NOT NULL, afdelingid INT NOT NULL, gewicht INT, FOREIGN KEY(productid) REFERENCES Product(productid), FOREIGN KEY(afdelingid) REFERENCES Afdeling(afdelingid));");
			statements.add("CREATE TABLE IF NOT EXISTS Magazijn(productid INT NOT NULL, aantal int, gewicht int, FOREIGN KEY(productid) REFERENCES Product(productid));");
			statements.add("CREATE TABLE IF NOT EXISTS Klant(klantid INT NOT NULL auto_increment PRIMARY KEY, groepid INT NOT NULL, FOREIGN KEY(groepid) REFERENCES Groep(groepid));");
			statements.add("CREATE TABLE IF NOT EXISTS Werknemer(werknemerid INT NOT NULL auto_increment PRIMARY KEY, naam varchar(50));");
			statements.add("CREATE TABLE IF NOT EXISTS Kassa(kassanr INT NOT NULL auto_increment PRIMARY KEY, werknemerid INT, FOREIGN KEY(werknemerid) REFERENCES Werknemer(werknemerid));");
			statements.add("CREATE TABLE IF NOT EXISTS Aankoop(aankoopid INT NOT NULL auto_increment PRIMARY KEY, productid INT NOT NULL, kassanr INT NOT NULL,aantal INT, gewicht INT, FOREIGN KEY(productid) REFERENCES Product(productid), FOREIGN KEY(kassanr) REFERENCES Kassa(kassanr));");
			//add paths
			statements.add("INSERT INTO Pad(naam) VALUES('Conserven');");//1
			statements.add("INSERT INTO Pad(naam) VALUES('Diepvries');");//2
			statements.add("INSERT INTO Pad(naam) VALUES('Non-food');");//3
			statements.add("INSERT INTO Pad(naam) VALUES('Chips');");//4
			statements.add("INSERT INTO Pad(naam) VALUES('Dranken');");//5
			statements.add("INSERT INTO Pad(naam) VALUES('Voordeelstraat');");//6
			//add path products
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Bruine bonen',1.50);");//1
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Worteltjes',2.00);");//2
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Appelmoes',1.05);");//3
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Pizza',3.50);");//4
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Magnum',2.50);");//5
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Wasbenzine',1.25);");//6
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Gootsteenontstopper',1.75);");//7
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Wasverzachter',4.50);");//8
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Ham-Kaas chips',1.50);");//9
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Beertjes chips',2.15);");//10
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Captain Morgan',15.00);");//11
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Jagermeister',12.50);");//12
			statements.add("INSERT INTO Product(naam,prijs) VALUES('Schultenbrau krat',5.00);");//13
			//add departments
			statements.add("INSERT INTO Afdeling(naam) VALUES('Kaas');");//1
			statements.add("INSERT INTO Afdeling(naam) VALUES('Vlees');");//2
			//add department products
			statements.add("INSERT INTO Product(naam,prijs) VALUES ('Old amsterdam 50+',2.50);");//14
			statements.add("INSERT INTO Product(naam,prijs) VALUES ('Spareribs',1.50);");//15
			//add groups
			statements.add("INSERT INTO Groep(naam) VALUES('Student');");//1
			statements.add("INSERT INTO Groep(naam) VALUES('Moeder');");//2
			statements.add("INSERT INTO Groep(naam) VALUES('Alcoholist');");//3
			statements.add("INSERT INTO Groep(naam) VALUES('Dikzak');");//4
			//add employees
			statements.add("INSERT INTO Werknemer(naam) VALUES('Henkie');");//1
			statements.add("INSERT INTO Werknemer(naam) VALUES('Pietertje');");//2
			statements.add("INSERT INTO Werknemer(naam) VALUES('Erik');");//3
			statements.add("INSERT INTO Werknemer(naam) VALUES('Klaas');");//4
			statements.add("INSERT INTO Werknemer(naam) VALUES('Barrie');");//5
			statements.add("INSERT INTO Werknemer(naam) VALUES('Elise');");//6
			//add preference products
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(1,4);");
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(1,9);");
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(1,13);");
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(3,11);");
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(3,12);");
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(3,13);");
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(4,4);");
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(4,5);");
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(4,9);");
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(4,10);");
			statements.add("INSERT INTO Groep_Voorkeur_Product(groepid,productid) VALUES(4,13);");
			//add products to paths: default 10 everywhere
			int defaultcapacity = 10;
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(1,1,"+defaultcapacity+");");//bruine bonen
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(2,1,"+defaultcapacity+");");//worteltjes
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(3,1,"+defaultcapacity+");");//appelmoes
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(4,2,"+defaultcapacity+");");//pizza
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(5,2,"+defaultcapacity+");");//magnum
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(6,3,"+defaultcapacity+");");//wasbenzine
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(7,3,"+defaultcapacity+");");//gootsteenontstopper
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(8,3,"+defaultcapacity+");");//wasverzachter
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(9,4,"+defaultcapacity+");");//ham-kaas chips
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(10,4,"+defaultcapacity+");");//beertjes chips
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(11,5,"+defaultcapacity+");");//captain morgan
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(12,5,"+defaultcapacity+");");//jagermeister
			statements.add("INSERT INTO Product_Opgesteld_Pad(productid,padid,aantal) VALUES(13,5,"+defaultcapacity+");");//schultenbrau bier
			//add cash desk
			statements.add("INSERT INTO Kassa(werknemerid) VALUES(6);");
			//add products to departments
			int defaultWeight = 10000;//weight is 10 000 grams
			statements.add("INSERT INTO Product_Opgesteld_Afdeling(productid,afdelingid,gewicht) VALUES(14,1,"+defaultWeight+");");
			statements.add("INSERT INTO Product_Opgesteld_Afdeling(productid,afdelingid,gewicht) VALUES(15,2,"+defaultWeight+");");
			//execute all statements
			for(String s : statements){
				statement.executeUpdate(s);
			}
			//add empty cash desks
			prepCashStatement = con.prepareStatement("INSERT INTO Kassa(werknemerid) VALUES(?);");
			//add 3 empty cash desks
			for(int i =0; i < 3; i++){
				prepCashStatement.setNull(1, Types.INTEGER);
				prepCashStatement.executeUpdate();
			}
			
			//add storage of products
			int defaultStorageWeight = 100000; // default storage is 100kg
			int defaultStorageAmount = 100; // default storage amount is 100
			
			prepStorageStatement = con.prepareStatement("INSERT INTO Magazijn(productid, aantal, gewicht) VALUES(?,?,?);");
			//path products
			for(int i = 1; i < 14; i++){
				prepStorageStatement.setInt(1, i);
				prepStorageStatement.setInt(2, defaultStorageAmount);
				prepStorageStatement.setNull(3, Types.INTEGER);
				prepStorageStatement.executeUpdate();
			}
			//department products
			for(int i = 14; i < 16; i++){
				prepStorageStatement.setInt(1, i);
				prepStorageStatement.setNull(2, Types.INTEGER);
				prepStorageStatement.setInt(3, defaultStorageWeight);
				prepStorageStatement.executeUpdate();
			}
		}
		catch(SQLException ex){
			System.out.println("Something went wrong with creating the tables !!!");
			ex.printStackTrace();
		}
		finally{
			try{
				statement.close();
				prepCashStatement.close();
				prepStorageStatement.close();
			}
			catch(SQLException ex){
				System.out.println("Could not close statement");
				ex.printStackTrace();
			}
		}
	}
}
