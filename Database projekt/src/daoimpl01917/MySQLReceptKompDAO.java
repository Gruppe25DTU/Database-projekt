package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ReceptKompDAO;
import dto01917.ReceptKompDTO;

public class MySQLReceptKompDAO implements ReceptKompDAO {

	public MySQLReceptKompDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
		String query = "select * from receptkomponent where recept_id = '%s' and raavare_id = '%s'";
		query = String.format(query, receptId,raavareId);

		try {
			ResultSet rs = Connector.doQuery(query);
			if(!rs.first()) {
				throw new DALException("Kunne ikke hente komponenter");
			}
			//int receptId, int raavareId, double nomNetto, double tolerance
			//recept_id INT, raavare_id INT, nom_netto REAL, tolerance REAL
			return new ReceptKompDTO(rs.getInt("recept_id"),rs.getInt("raavare_id"),rs.getDouble("nom_netto"),rs.getDouble("tolerance"));
		}
		catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
		String query = "select * from receptkomponent where recept_id = %d";
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		query = String.format(query, receptId);

		try{
			ResultSet rs = Connector.doQuery(query);
			while(rs.next()) {
				ReceptKompDTO receptKompDTO = new ReceptKompDTO(rs.getInt("recept_id"),rs.getInt("raavare_id"),rs.getDouble("nom_netto"),rs.getDouble("tolerance"));
				list.add(receptKompDTO);
			}
		}
		catch(SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException {
		String query = "select * from receptkomponent";
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();

		try{
			ResultSet rs = Connector.doQuery(query);
			while(rs.next()) {
				ReceptKompDTO receptKompDTO = new ReceptKompDTO(rs.getInt("recept_id"),rs.getInt("raavare_id"),rs.getDouble("nom_netto"),rs.getDouble("tolerance"));
				list.add(receptKompDTO);
			}
		}
		catch(SQLException e) {
			throw new DALException(e);
		}
		return list;
	}

	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		String query = "insert into receptkomponent values('%d','%d','%d','%d')";
		query = String.format(query, receptkomponent.getReceptId(),receptkomponent.getRaavareId(),receptkomponent.getNomNetto(),receptkomponent.getTolerance());
		try {
			Connector.doUpdate(query);
		}
		catch (DALException e) {
			throw new DALException("Kunne ikke oprette ny recept:" + receptkomponent); 
		}

	}

	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {
		String query = "update receptkomponent set nom_netto = '%d',tolerance = '%d' where recept_id = %d and raavare_id = %s";
		query = String.format(query,receptkomponent.getNomNetto(),receptkomponent.getTolerance(),receptkomponent.getReceptId(),receptkomponent.getRaavareId());
		try {
			Connector.doUpdate(query);
		}
		catch (DALException e) {
			throw new DALException("Kunne ikke opdatere receptkompnent med ID: " + receptkomponent.getReceptId() + " og råvare ID: " + receptkomponent.getRaavareId() );
		}
	}

}
