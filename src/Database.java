import java.sql.*;
import java.util.ArrayList;

public class Database {
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/lightcity";
	static final String USER = "root";
	static final String PASS = "a13811383";

	public static ArrayList<String> availableservers() {
		ArrayList<String> server = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from server");
			while (resultSet.next()) {
				server.add(resultSet.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return server;

	}

	public static boolean login(String username, String password, String server) throws Exception {
		password = EncryptionUtils.encrypt(password);

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from avatar");
			while (resultSet.next()) {
				if (username.equals(resultSet.getString(2)) && password.equals(resultSet.getString(3))
						&& server.equals(resultSet.getString(9))) {
					return true;

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static Avatar findAvatar(String username, String server) {
		Avatar avatar;
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from avatar");
			while (resultSet.next()) {
				if (username.equals(resultSet.getString(2)) && server.equals(resultSet.getString(9))) {
					avatar = new Avatar(username, resultSet.getString(4), resultSet.getFloat(8), resultSet.getFloat(6),
							resultSet.getFloat(7), resultSet.getFloat(5), server,resultSet.getString(10));
					buildingOwn(username,server,avatar);
					return avatar;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void buildingOwn(String username,String server,Avatar avatar){
		ArrayList<Property> properties=new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {
				if (username.equals(resultSet.getString(4)) && server.equals(resultSet.getString(3))) {
					Property property=new Property(resultSet.getString(2),resultSet.getString(3),
							resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),
							resultSet.getInt(7),resultSet.getInt(8),resultSet.getInt(9),
							resultSet.getInt(10),resultSet.getString(11),resultSet.getString(12),resultSet.getInt(13));
					properties.add(property);
					avatar.setProperties(properties);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	public static boolean AddAvatar(String username, String password, String server) throws Exception {
		password = EncryptionUtils.encrypt(password);
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from avatar");
			while (resultSet.next()) {
				if (username.equals(resultSet.getString(2)) && server.equals(resultSet.getString(9))) {
					return false;

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		AddMember(username, password, server, "None","None");
		return true;
	}

	public static boolean AddServer(String username, String password, String server) throws Exception {
		password = EncryptionUtils.encrypt(password);

		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from server");
			while (resultSet.next()) {
				if (server.equals(resultSet.getString(1))) {
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		AddMember(username, password, server, "Mayor","Town Hall");
		AddCity(username, server);
		return true;
	}

	public static void AddMember(String username, String password, String server, String title, String workPlace) {
		try {
			String sql = "insert into avatar (username, password,title,water,food,sleep,money,servername,workplace)"
					+ "values('" + username + "','" + password + "','" + title + "',' 10.0','10.0','10.0','1500','"
					+ server + "','"+workPlace+"')";
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void AddBuilding(String username, String servername,int x,int y) {
		try {
			String sql = "insert into building (name, servername,username,business,income,EXPENSE,salary,locationx,locationy," +
					"situation,workercondition,workernum)"
					+ "values('Field','" + servername + "','" + username + "',' None','0','0','0','"+x+"','"+y+" ','Not Sold','No','0')";
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void AddCity(String username, String server) {
		try {
			String sql = "insert into server (name, mayor)"
					+ "values('" + server + "','" + username + "')";
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void UpdateAvatar(String username,String server,String work, String workPlace,
									float money,float food,float water,float sleep,int choice){
		int id;
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from avatar");
			while (resultSet.next()) {
				if (username.equals(resultSet.getString(2)) && server.equals(resultSet.getString(9))) {
					id=resultSet.getInt(1);
					if(choice==1){
						UpdateByID(id,work,workPlace);
					} else if (choice==2) {
						reduceMoney(id,money,resultSet.getFloat(8));
					} else if (choice==3) {
						increaseMoney(id,resultSet.getFloat(8),money);
					} else if(choice==4){
						changeAvatarState(id,sleep,food,water);
					}

				}
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateByID(int id,String work,String workPlace){
		try {
			String sql = "UPDATE avatar SET title = '"+work+"' WHERE id = '"+id+"'";
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			String sql1 = "UPDATE avatar SET workplace = '"+workPlace+"' WHERE id = '"+id+"'";
			Connection conn1 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt1 = conn1.createStatement();
			stmt1.executeUpdate(sql1);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String FindMayor(String server){
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from server");
			while (resultSet.next()) {
				if (server.equals(resultSet.getString(1))) {
					return resultSet.getString(2);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void ReadyToSell(int x ,int y ,String server,String sell,int price,String owner){
		int id;
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {
				if (x==resultSet.getInt(9) && y==resultSet.getInt(10) && server.equals(resultSet.getString(3))) {
					id=resultSet.getInt(1);
					UpdateSituation(id,sell,price,owner);
				}
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateSituation(int id, String sell,int Price,String owner){
		try {
			String sql = "UPDATE building SET situation = '"+sell+"' WHERE id = '"+id+"'";
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			String sql1 = "UPDATE building SET expense = '"+Price+"' WHERE id = '"+id+"'";
			Connection conn1 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt1 = conn1.createStatement();
			stmt1.executeUpdate(sql1);

			String sql2 = "UPDATE building SET username = '"+owner+"' WHERE id = '"+id+"'";
			Connection conn2 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt2 = conn2.createStatement();
			stmt2.executeUpdate(sql2);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Property> OnSale(String username,String server){
		ArrayList<Property> properties= new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {
				if (resultSet.getString(11).equals("Ready To Sell") && resultSet.getString(3).equals(server) && !(resultSet.getString(4).equals(username))) {
					Property property=new Property(resultSet.getString(2),resultSet.getString(3),
							resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),
							resultSet.getInt(7),resultSet.getInt(8),resultSet.getInt(9),
							resultSet.getInt(10),resultSet.getString(11),resultSet.getString(12),resultSet.getInt(13));
					properties.add(property);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static int GetSalary(String server,String workPlace){
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {
				if (server.equals(resultSet.getString(3)) && workPlace.equals(resultSet.getString(2))) {
					return resultSet.getInt(8);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Property FindBuilding(int x,int y,String server){
		Property property;
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {
				if (server.equals(resultSet.getString(3)) && x==resultSet.getInt(9) && y==resultSet.getInt(10)) {
					property=new Property(resultSet.getString(2),server,resultSet.getString(4),resultSet.getString(5),
							resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8)
							,x,y,resultSet.getString(11),resultSet.getString(12),resultSet.getInt(13));
				return property;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Property FindWorkingPlace(String name,String server){
		Property property;
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {
				if (server.equals(resultSet.getString(3)) && name.equals(resultSet.getString(2)) ) {
					property=new Property(resultSet.getString(2),server,resultSet.getString(4),resultSet.getString(5),
							resultSet.getInt(6),resultSet.getInt(7),resultSet.getInt(8)
							,resultSet.getInt(9),resultSet.getInt(10),resultSet.getString(11)
							,resultSet.getString(12),resultSet.getInt(13));
					return property;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void reduceMoney(int id,float money,float previousMoney){
		try {
			previousMoney-=money;
			String sql = "UPDATE avatar SET money = '"+previousMoney+"' WHERE id = '"+id+"'";
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void increaseMoney(int id,float previousMoney,float money){
		try {
			money+=previousMoney;
			String sql = "UPDATE avatar SET money = '"+money+"' WHERE id = '"+id+"'";
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean changeBusiness(String name,String server, int salary, String business,int x,int y,String worksituation,int worknum){
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {
				if (name.equals(resultSet.getString(2)) && server.equals(resultSet.getString(3))) {
					return false;

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		changeBuilding(name, salary, business,server,x,y,worksituation,worknum);
		return true;

	}

	public static void changeBuilding(String name, int salary, String business,String server,int x, int y,String worksituation,int worknum){
		try {
			int id=0;

			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {

				if (x==resultSet.getInt(9) && y==resultSet.getInt(10) && server.equals(resultSet.getString(3))) {
					id=resultSet.getInt(1);

				}
			}



			String sql3 = "UPDATE building SET name = '"+name+"' WHERE id = '"+id+"'";
			Connection conn3 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt3 = conn3.createStatement();
			stmt3.executeUpdate(sql3);

			String sql1 = "UPDATE building SET salary = '"+salary+"' WHERE id = '"+id+"'";
			Connection conn1 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt1 = conn1.createStatement();
			stmt1.executeUpdate(sql1);

			String sql2 = "UPDATE building SET business = '"+business+"' WHERE id = '"+id+"'";
			Connection conn2 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt2 = conn2.createStatement();
			stmt2.executeUpdate(sql2);

			String sql4 = "UPDATE building SET workercondition = '"+worksituation+"' WHERE id = '"+id+"'";
			Connection conn4 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt4 = conn4.createStatement();
			stmt4.executeUpdate(sql4);

			String sql5 = "UPDATE building SET workernum = '"+worknum+"' WHERE id = '"+id+"'";
			Connection conn5 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt5 = conn5.createStatement();
			stmt5.executeUpdate(sql5);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Property> availableJobs(String server){
		ArrayList<Property> properties= new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {
				if (resultSet.getString(12).equals("Yes") && resultSet.getString(3).equals(server)) {
					Property property=new Property(resultSet.getString(2),resultSet.getString(3),
							resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),
							resultSet.getInt(7),resultSet.getInt(8),resultSet.getInt(9),
							resultSet.getInt(10),resultSet.getString(11),
							resultSet.getString(12),resultSet.getInt(13));
					properties.add(property);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static ArrayList<Avatar> workerList(String server,String workPlace){
		ArrayList<Avatar> avatars= new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from avatar");
			while (resultSet.next()) {
				if (resultSet.getString(10).equals(workPlace) && resultSet.getString(9).equals(server)) {
					Avatar avatar=new Avatar(resultSet.getString(2),resultSet.getString(4),
							resultSet.getFloat(8),resultSet.getFloat(6),resultSet.getFloat(7)
							,resultSet.getFloat(5),server,workPlace);
					avatars.add(avatar);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avatars;
	}

	public static boolean checkWorkingPlace(String server,String username,String building){
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from avatar");
			while (resultSet.next()) {
				if (username.equals(resultSet.getString(2)) && building.equals(resultSet.getString(10))
						&& server.equals(resultSet.getString(9))) {
					return true;

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void changeAvatarState(int id,float sleep,float food, float water){

		try {
			String sql = "UPDATE avatar SET sleep = '"+sleep+"' WHERE id = '"+id+"'";
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			String sql1 = "UPDATE avatar SET food = '"+food+"' WHERE id = '"+id+"'";
			Connection conn1 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt1 = conn1.createStatement();
			stmt1.executeUpdate(sql1);

			String sql2 = "UPDATE avatar SET water = '"+water+"' WHERE id = '"+id+"'";
			Connection conn2 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt2 = conn2.createStatement();
			stmt2.executeUpdate(sql2);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Property> foodShop(String server){
		ArrayList<Property> properties= new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {
				if ((resultSet.getString(5).equals("Restaurant") || resultSet.getString(5).equals("Super Market")) && resultSet.getString(3).equals(server) ) {
					Property property=new Property(resultSet.getString(2),resultSet.getString(3),
							resultSet.getString(4),resultSet.getString(5),resultSet.getInt(6),
							resultSet.getInt(7),resultSet.getInt(8),resultSet.getInt(9),
							resultSet.getInt(10),resultSet.getString(11),resultSet.getString(12),resultSet.getInt(13));
					properties.add(property);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static void buildingIncome(int x, int y, String server,int income){
		try {
			int id=0;

			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from building");
			while (resultSet.next()) {

				if (x==resultSet.getInt(9) && y==resultSet.getInt(10) && server.equals(resultSet.getString(3))) {
					id=resultSet.getInt(1);

				}
			}



			String sql3 = "UPDATE building SET income = '"+income+"' WHERE id = '"+id+"'";
			Connection conn3 = DriverManager.getConnection(DB_URL, USER, PASS);

			Statement stmt3 = conn3.createStatement();
			stmt3.executeUpdate(sql3);



		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
