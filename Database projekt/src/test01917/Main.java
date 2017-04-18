package test01917;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daoimpl01917.MySQLProduktBatchDAO;
import daoimpl01917.MySQLProduktBatchKompDAO;
import daoimpl01917.MySQLRaavareBatchDAO;
import daoimpl01917.MySQLRaavareDAO;
import daoimpl01917.MySQLReceptDAO;
import daoimpl01917.MySQLReceptKompDAO;
import daointerfaces01917.DALException;
import daointerfaces01917.ProduktBatchDAO;
import daointerfaces01917.ProduktBatchKompDAO;
import daointerfaces01917.RaavareBatchDAO;
import daointerfaces01917.RaavareDAO;
import daointerfaces01917.ReceptDAO;
import daointerfaces01917.ReceptKompDAO;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;
import dto01917.RaavareDTO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;

public class Main {
	public static void main(String[] args) {
		try { new Connector(); } 
		catch (InstantiationException e) { e.printStackTrace(); }
		catch (IllegalAccessException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
//		System.out.println("Operatoer nummer 3:");
//		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
//		try { System.out.println(opr.getOperatoer(3)); }
//		catch (DALException e) { System.out.println(e.getMessage()); }
//		
//		System.out.println("Indsaettelse af ny operatoer med opr_id =  4");
//		OperatoerDTO oprDTO = new OperatoerDTO(4,"Don Juan","DJ","000000-0000","iloveyou");
//		try { opr.createOperatoer(oprDTO); }
//		catch (DALException e) { System.out.println(e.getMessage()); }	
//		
//		System.out.println("Operatoer nummer 4:");
//		try { System.out.println(opr.getOperatoer(4)); }
//		catch (DALException e) { System.out.println(e.getMessage()); }
//		
//		System.out.println("Opdatering af initialer for operatoer nummer 4");
//		oprDTO.setIni("DoJu");
//		try { opr.updateOperatoer(oprDTO,"000000-0000",4); }
//		catch (DALException e) { System.out.println(e.getMessage()); }
//		
//		System.out.println("Operatoer nummer 4:");
//		try { System.out.println(opr.getOperatoer(4)); }
//		catch (DALException e) { System.out.println(e.getMessage()); }
//		
//		System.out.println("Alle operatoerer:");
//		try { System.out.println(opr.getOperatoerList()); }
//		catch (DALException e) { System.out.println(e.getMessage()); }
//		
//		System.out.println("Operatoer nummer 5:");
//		try { System.out.println(opr.getOperatoer(5)); }
//		catch (DALException e) { System.out.println(e.getMessage()); }		
//		
		ProduktBatchDAO pb = new MySQLProduktBatchDAO();
		ProduktBatchKompDAO pbk = new MySQLProduktBatchKompDAO();
		RaavareBatchDAO rv = new MySQLRaavareBatchDAO();
		RaavareDAO r = new MySQLRaavareDAO();
		ReceptDAO recept = new MySQLReceptDAO();
		ReceptKompDAO rk = new MySQLReceptKompDAO();
		try {
			System.out.println("ProduktBatchList");
			List<ProduktBatchDTO> pbList = pb.getProduktBatchList();
			for(int i = 0;i<pbList.size();i++) {
				System.out.println(pbList.get(i));
			}
			
			System.out.println("ReceptList");
			List<ReceptDTO> Receptlist = recept.getReceptList();
			for(int i = 0;i<Receptlist.size();i++) {
				System.out.println(Receptlist.get(i));
			}
			System.out.println("ProdukTBatchKomp liste");
			pbk.getProduktBatchKompList();
			List<ProduktBatchKompDTO> pbkList = pbk.getProduktBatchKompList();
			for(int i = 0;i<pbkList.size();i++) {
				System.out.println(pbkList.get(i));
			}
			
			System.out.println("Raavare list");
			List<RaavareDTO> raavareList = r.getRaavareList();
			for(int i = 0;i<raavareList.size();i++) {
				System.out.println(raavareList.get(i));
			}
			
			
			System.out.println("Recept komp");
			List<ReceptKompDTO> rkList = rk.getReceptKompList();
			for(int i = 0;i<rkList.size();i++) {
				System.out.println(rkList.get(i));
			}
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
