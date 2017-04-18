package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ReceptDAO;
import dto01917.ReceptDTO;


public class MySQLReceptDAO implements ReceptDAO {

	public MySQLReceptDAO() {

	}

	@Override
	public ReceptDTO getRecept(int receptId) throws DALException {
		String query = "select * from recept where recept_id = %d";
		query = String.format(query, receptId);

		ResultSet rs = Connector.doQuery(query);
		try {
			if(!rs.first()) {
				throw new DALException("Recept: " + receptId + " findes ikke.");
			}
			return new ReceptDTO(rs.getInt("recept_id"),rs.getString("recept_navn"));
		}
		catch (SQLException e) {throw new DALException(e); }
	}

	@Override
	public List<ReceptDTO> getReceptList() throws DALException {
		String query = "select * from recept;";
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		ResultSet rs = Connector.doQuery(query);
		try {
			while(rs.next()) {
				ReceptDTO recept = new ReceptDTO(rs.getInt("recept_id"),rs.getString("recept_navn"));
				list.add(recept);
			}
		}

		catch (SQLException e) {
			throw new DALException("Kunne ikke hente liste."); 
		}			

		return list;
	}

	@Override
	public void createRecept(ReceptDTO recept) throws DALException {
		String query = "insert into recept values('%d','%s')";
		query = String.format(query, recept.getReceptId(),recept.getReceptNavn());
		try {
			Connector.doUpdate(query);
		}
		catch (DALException e) {
			throw new DALException("Kunne ikke oprette ny recept:" + recept); 
		}

	}

	@Override
	public void updateRecept(ReceptDTO recept) throws DALException {
		String query = "update recept set recept_navn = '%s' where recept_id = %d";
		query = String.format(query, recept.getReceptNavn(),recept.getReceptId());
		try {
			Connector.doUpdate(query);
		}
		catch (DALException e) {
			throw new DALException("Kunne ikke opdatere recept med ID: " + recept.getReceptId());
		}
	}



}
