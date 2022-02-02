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

	private String url = "jdbc:mysql://localhost:3306/?user=root";
	private String userName = "root";
	private String password = "saman";
	private String query = "SELECT * FROM meydaneryaman.userinfo;";
	private ResultSet resultSet;
	private Connection con;
	private Statement statement;

	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		con = DriverManager.getConnection(url, userName, password);
		statement = con.createStatement();
		// statement.executeUpdate(query1);
		// statement.executeQuery(query1);
		resultSet = statement.executeQuery(query);
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
		String query = String.format("INSERT INTO meydaneryaman.userinfo (`tel`, `name`, `lastname`, `block-no`, `carAvailable`, `flat-no`, `capacity`) VALUES ('"+infoDTO.getTel().toString()+"', '"+infoDTO.getName()+"', '"+infoDTO.getLastName()
		+"', '"+infoDTO.getBlock()+"', '"+carAvailableInt.toString()+"', '"+infoDTO.getNo()+"', '"+infoDTO.getCapacity()+"');");
			
		statement.executeUpdate(query);
	}
	
	public void assign(Map<MeydanInfoDTO, List<MeydanInfoDTO>> map) throws SQLException {
		
		String[] passengerPlaceHolder= {"first","second", "third", "fourth"};
		for (MeydanInfoDTO dto: map.keySet()) {
			String query= "SELECT cartel FROM meydaneryaman.assign;";
			 resultSet = statement.executeQuery(query);
			 List<Long> temp = new ArrayList<>();
			 while (resultSet.next()) {
				 temp.add(resultSet.getLong(1));
			 }
			
			 if (!temp.contains(dto.getTel())) {
			 query = "INSERT INTO meydaneryaman.assign (cartel) VALUES ('"+dto.getTel()+"');";
			 statement.executeUpdate(query);
			 }
			 for (int i=0; i<map.get(dto).size();i++) {
				query = "UPDATE meydaneryaman.assign SET "+passengerPlaceHolder[i]+" ='"+map.get(dto).get(i).getTel()+"' WHERE cartel= '"+dto.getTel()+"';";
				statement.executeUpdate(query);
			}
			
		}
		
	}
	
	public void close() throws SQLException {
		con.close();
	}

}
