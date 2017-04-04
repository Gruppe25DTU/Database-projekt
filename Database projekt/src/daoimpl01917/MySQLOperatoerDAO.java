package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import connector01917.Connector;

import daointerfaces01917.DALException;
import daointerfaces01917.OperatoerDAO;
import dto01917.OperatoerDTO;

public class MySQLOperatoerDAO implements OperatoerDAO {
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer_view WHERE opr_id = " + oprId);
		try {
			if (!rs.first()) throw new DALException("Operatoeren " + oprId + " findes ikke");
			return new OperatoerDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
		}
		catch (SQLException e) {throw new DALException(e); }

	}

	public void createOperatoer(OperatoerDTO opr) throws DALException {
		String operatoer_info = "INSERT INTO operatoer_info(cpr, opr_navn,ini) VALUES ('%s','%s','%s');";
		operatoer_info = String.format(operatoer_info, opr.getCpr(),opr.getOprNavn(),opr.getIni());
		Connector.doUpdate(operatoer_info);
		Connector.doUpdate(
				"INSERT INTO operatoer(opr_id, cpr, password) VALUES " +
						"(" + opr.getOprId() + ", '" + opr.getCpr() + "', '" + opr.getPassword() + "')"
				);

	}


	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		String operatoer_info = "UPDATE operatoer_info SET opr_navn = '%s', ini = '%s', cpr = '%s'";
		String.format(operatoer_info, opr.getOprNavn(),opr.getIni(),opr.getCpr());
		Connector.doUpdate(operatoer_info);
		
		String operatoer = "UPDATE operatoer SET cpr = '%s', password = '%s', opr_id = '%s'";
		String.format(operatoer, opr.getCpr(),opr.getPassword(),opr.getOprId());
		Connector.doUpdate(operatoer);
		
		
	}


	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer_view");
		try
		{
			while (rs.next()) 
			{
				list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}


}

