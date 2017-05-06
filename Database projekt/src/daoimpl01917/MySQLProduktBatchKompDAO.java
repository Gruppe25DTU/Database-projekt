package daoimpl01917;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connector01917.Connector;
import daointerfaces01917.DALException;
import daointerfaces01917.ProduktBatchKompDAO;
import dto01917.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO {

	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId + " and rb_id = " + rbId);
		try {
			if (!rs.first()) throw new DALException("ProduktBatchKomponentet " + pbId + ", " + rbId + " findes ikke!");
			return new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")); 
		}
		catch (SQLException e) {throw new DALException(e); } 
	}

	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent WHERE pb_id = " + pbId);
		try {
			if (!rs.first()) throw new DALException("Ingen ProduktBatchKomponenter med ID: " + pbId + " findes!");
			rs.beforeFirst();
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
			}
			return list;
		}

		catch (SQLException e) {throw new DALException(e); } 
	}


	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM produktbatchkomponent");
		try {
			if (!rs.first()) throw new DALException("Ingen ProduktBatchKomponenter findes!");
			rs.beforeFirst();
			while (rs.next()) 
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"), rs.getDouble("netto"), rs.getInt("opr_id")));
			}
			return list;
		}

		catch (SQLException e) {throw new DALException(e); } 
	}

	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		Connector.doUpdate(
				"INSERT INTO produktbatchkomponent(pb_id, rb_id, opr_id, tara, netto) VALUES " +
						"(" + produktbatchkomponent.getPbId() + ", '" + produktbatchkomponent.getRbId() + "', '" + produktbatchkomponent.getOprId() + 
						"', '" + produktbatchkomponent.getTara() + "', '" + produktbatchkomponent.getNetto() + "')"
				);
	}


	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		Connector.doUpdate(
				"UPDATE produktbatchkomponent SET  tara = '" + produktbatchkomponent.getTara() +  "', netto = '" + produktbatchkomponent.getNetto()
				+ "', opr_id = "+ produktbatchkomponent.getOprId()+ " WHERE pb_id = " + produktbatchkomponent.getPbId() + " and rb_id =  " + produktbatchkomponent.getRbId());
	}

}
