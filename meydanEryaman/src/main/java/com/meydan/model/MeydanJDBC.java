package com.meydan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.meydan.api.MeydanInfoDTO;

@Component
public class MeydanJDBC {

	//private String url = "jdbc:postgresql://udjxsxtjtbydzo:8631780daf31459587fc7c65a67c5799306639f54c70aedcc90003dface71be9@ec2-52-31-219-113.eu-west-1.compute.amazonaws.com:5432/deg9h2re9c641o\r\n"
		//	+ "";
	private String url = "jdbc:postgresql://ec2-52-31-219-113.eu-west-1.compute.amazonaws.com:5432/deg9h2re9c641o?user=udjxsxtjtbydzo&password=8631780daf31459587fc7c65a67c5799306639f54c70aedcc90003dface71be9&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	private String userName = "udjxsxtjtbydzo";
	private String password = "8631780daf31459587fc7c65a67c5799306639f54c70aedcc90003dface71be9";
	private String query = "SELECT * FROM userinfo;";
	private ResultSet resultSet;
	private Connection con;
	private Statement statement;

	public Statement connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");

		con = DriverManager.getConnection(url, userName, password);
		statement = con.createStatement();
		
		// statement.executeUpdate(query1);
		// statement.executeQuery(query1);
		resultSet = statement.executeQuery(query);
		return statement;
	}

	public List<MeydanInfoDTO> allInfoFromDB() throws SQLException{
		List<MeydanInfoDTO> temp = new ArrayList<MeydanInfoDTO>();
		while (resultSet.next()) {
			MeydanInfoDTO dto = new MeydanInfoDTO();
			dto.setTel(Long.parseLong(resultSet.getString(1)));
			dto.setName(resultSet.getString(2));
			dto.setLastName(resultSet.getString(3));
			dto.setBlock(resultSet.getString(4));
			dto.setCarOwn(resultSet.getBoolean(5));
			dto.setCapacity(resultSet.getInt(6));
			dto.setNo(resultSet.getInt(7));
			dto.setParticipate(resultSet.getBoolean(8));
			dto.setHaveRepresentation(resultSet.getBoolean(9));
			dto.setWishRepresented(resultSet.getBoolean(10));
			temp.add(dto);
		}
		
		return temp;
	}

	public void addToDb(MeydanInfoDTO infoDTO) throws SQLException {
		
		Integer carAvailableInt;
		if (infoDTO.isCarOwn()) {
			carAvailableInt = 1;
		}
		else carAvailableInt = 0;
		
		

		String query = String.format("INSERT INTO userinfo (tel, name, lastname, blockno, caravailable, flatno, capacity, participate, hasRepresentation, wishRepresented) VALUES ('"+infoDTO.getTel().toString()+"', '"+infoDTO.getName()+"', '"+infoDTO.getLastName()
		+"', '"+infoDTO.getBlock()+"', '"+carAvailableInt.toString()+"', '"+infoDTO.getNo()+"', '"+infoDTO.getCapacity()+"', '"+infoDTO.isParticipate()+"', '"+infoDTO.isHaveRepresentation()+"', '"+infoDTO.isWishRepresented()+"');");
			
		statement.executeUpdate(query);
	}
	
	public void assign(Map<MeydanInfoDTO, List<MeydanInfoDTO>> map) throws SQLException {
		
		String[] passengerPlaceHolder= {"first","second", "third", "fourth"};
		for (MeydanInfoDTO dto: map.keySet()) {
			String query= "SELECT cartel FROM assign;";
			 resultSet = statement.executeQuery(query);
			 List<Long> temp = new ArrayList<>();
			 while (resultSet.next()) {
				 temp.add(resultSet.getLong(1));
			 }
			
			 if (!temp.contains(dto.getTel())) {
			 query = "INSERT INTO assign (cartel) VALUES ('"+dto.getTel()+"');";
			 statement.executeUpdate(query);
			 }
			 for (int i=0; i<map.get(dto).size();i++) {
				query = "UPDATE assign SET "+passengerPlaceHolder[i]+" ='"+map.get(dto).get(i).getTel()+"' WHERE cartel= '"+dto.getTel()+"';";
				statement.executeUpdate(query);
			}
			
		}
		
	}
	
	
	
	public void close() throws SQLException {
		con.close();
	}

}
