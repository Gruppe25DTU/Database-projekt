package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ProduktBatchDAO;
import dto01917.ProduktBatchDTO;

public class MySQLProduktBatchDAO implements ProduktBatchDAO{

	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatch where produktbatch.pb_id = "+pbId);
		try {
			if(!rs.first()) throw new DALException("produkt Batch med id:"+pbId+" findes ikke.");
			ProduktBatchDTO dto = new ProduktBatchDTO(rs.getInt("pb_Id"),rs.getInt("recept_id"),rs.getInt("status"));
			return dto;
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}

	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		ResultSet rs = Connector.doQuery("Select* From produktbatch");
		List<ProduktBatchDTO> dtoList = new ArrayList<ProduktBatchDTO>();
		try 
		{
			while(rs.next())
				dtoList.add(new ProduktBatchDTO(rs.getInt("pb_Id"),rs.getInt("recept_id"),rs.getInt("status")));
			
		} catch (SQLException e) {
			throw new DALException(e);
		}
		return dtoList;

	}

	@Override
	public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		int pb_id = produktbatch.getPbId();
		int recept_id = produktbatch.getReceptId();
		int status = produktbatch.getStatus();
		Connector.doUpdate("Insert into produktbatch(pb_id , recept_id , status) Values("+pb_id+","+recept_id+","+status+")");

	}

	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		int pb_id = produktbatch.getPbId();
		int recept_id = produktbatch.getReceptId();
		int status = produktbatch.getStatus();
		Connector.doUpdate("Update produktbatch Set recept_id = "+recept_id+", status = "+status+" where pb_id = "+pb_id);

	}

}
