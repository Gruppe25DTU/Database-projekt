package test01917;

import java.sql.SQLException;
import java.util.List;

import connector01917.Connector;
import daoimpl01917.MySQLOperatoerDAO;
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
import dto01917.OperatoerDTO;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;
import dto01917.RaavareBatchDTO;
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
		testOperatoer();
			
		
		
	}
	public static void testOperatoer() {
		MySQLOperatoerDAO opr = new MySQLOperatoerDAO();
		System.out.println("GetOperatoer. Number = 3.");
		try { System.out.println(opr.getOperatoer(3)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("CreateOperatoer. Navn = Don Juan, opr_id = 4.");
		OperatoerDTO oprDTO = new OperatoerDTO(4,"Don Juan","DJ","000000-0000","iloveyou");
		try { opr.createOperatoer(oprDTO); }
		catch (DALException e) { System.out.println(e.getMessage()); }	
		
		System.out.println("GetOperatoer. Number = 4. (Don Juan)");
		System.out.println("Operatoer nummer 4:");
		try { System.out.println(opr.getOperatoer(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("UpdateOperatoer. Don Juan ini = DoJu.");
		oprDTO.setIni("DoJu");
		try { opr.updateOperatoer(oprDTO,"000000-0000",4); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("GetOperatoer. Number = 4. (Don Juan)");
		try { System.out.println(opr.getOperatoer(4)); }
		catch (DALException e) { System.out.println(e.getMessage()); }
		
		System.out.println("Get non existing operatoer.");
		System.out.println("Operatoer nummer 5:");
		try { System.out.println(opr.getOperatoer(5)); }
		catch (DALException e) { System.out.println(e.getMessage()); }		
		
		System.out.println("OperatoerList:");
		try {
			List<OperatoerDTO> oprList = opr.getOperatoerList();
			for(int i = 0;i<oprList.size();i++) {
				System.out.println(oprList.get(i));
			}
		}
		catch (DALException e) { System.out.println(e.getMessage()); }

	}
	
	public static void testProduktBatch() throws DALException {
		ProduktBatchDAO pb = new MySQLProduktBatchDAO();
		
		System.out.println("GetProduktBatch. pbId = 1");
		System.out.println(pb.getProduktBatch(1));
		
		System.out.println("createProduktBatch. pb_id = 6, status = 1, recept_id = 3");
		ProduktBatchDTO pbDTO1 = new ProduktBatchDTO(6,1,3);
		pb.createProduktBatch(pbDTO1);
		
		System.out.println("Get new ProduktBatch. id = 6");
		pb.getProduktBatch(6);
		
		System.out.println("Update new ProduktBatch. id = 6, status = 2, recept_id = 3");
		ProduktBatchDTO pbDTO2 = new ProduktBatchDTO(6,2,3);

		pb.updateProduktBatch(pbDTO2);
		
		System.out.println("ProduktBatchList");
		List<ProduktBatchDTO> pbList = pb.getProduktBatchList();
		for(int i = 0;i<pbList.size();i++) {
			System.out.println(pbList.get(i));
		}
	}
	
	public static void testProduktBatchKomp() throws DALException {
		ProduktBatchKompDAO pbk = new MySQLProduktBatchKompDAO();
		
		System.out.println("GetProduktBachKomp. pbId = 1, rbId = 1");
		System.out.println(pbk.getProduktBatchKomp(1, 1));
		
		System.out.println("createProduktBatchKomp. pb_id: 1, rb_id: 8, tara: 0.5, netto: 10.05, opr_id: 2");
		ProduktBatchKompDTO pbkDTO1 = new ProduktBatchKompDTO(1, 8, 0.5, 10.05, 2);
		pbk.createProduktBatchKomp(pbkDTO1);
		
		System.out.println("Get new ProduktBatchKomp");
		pbk.getProduktBatchKomp(1, 8);
		
		System.out.println("Update new ProduktBatchKomp. tara = 0.65, netto = 43.6");
		ProduktBatchKompDTO pbkDTO2 = new ProduktBatchKompDTO(1, 8, 0.65, 43.6, 2);
		pbk.updateProduktBatchKomp(pbkDTO2);
		
		pbk.getProduktBatchKompList(); //done
		System.out.println("GetProduktBatchKompList");
		System.out.println(pbk.getProduktBatchKompList(1));
		
		System.out.println("ProdukTBatchKomp liste");
		List<ProduktBatchKompDTO> pbkList = pbk.getProduktBatchKompList();
		for(int i = 0;i<pbkList.size();i++) {
			System.out.println(pbkList.get(i));
		}
	}
	
	public static void testRaavareBatch() throws DALException  {
		RaavareBatchDAO rb = new MySQLRaavareBatchDAO();
		
		System.out.println("GetRaavareBatch. rbId = 1");
		System.out.println(rb.getRaavareBatch(1));
		
		System.out.println("createRaavareBatch. rb_id = 8, raavare_id = 7, maengde = 2000.0");
		RaavareBatchDTO rb1 = new RaavareBatchDTO(8, 7, 2000.0);
		rb.createRaavareBatch(rb1);
		
		System.out.println("Get new raavareBatch. rb_id = 8");
		System.out.println(rb.getRaavareBatch(8));
		
		System.out.println("updateRaavareBatch, maengde = 300.0");
		rb.updateRaavareBatch(new RaavareBatchDTO(8,7,300.0));

		System.out.println("Get updated RaavareBatch. rb_id = 8");
		System.out.println(rb.getRaavareBatch(8));
		
		System.out.println("GetRaavareBatchList(id). id = 1");
		System.out.println(rb.getRaavareBatchList(1));
		
		System.out.println("RaavareBatch List");
		List<RaavareBatchDTO> rbList = rb.getRaavareBatchList();	
		for(int i = 0;i<rbList.size();i++) {
			System.out.println(rbList.get(i));
		}
	}

	
	public static void testRaavare() throws DALException {
		RaavareDAO r = new MySQLRaavareDAO();
		
		System.out.println("getRaavare. id = 1.");
		System.out.println(r.getRaavare(1));
		
<<<<<<< HEAD
		System.out.println("CreateRaavre. id = 8, navn = pizza, leverandoer = Knoor");
		r.createRaavare(new RaavareDTO(8,"Pizza","Knoor"));
=======
	}
	
	public static void testRecept() throws DALException {
		ReceptDAO recept = new MySQLReceptDAO();
		ReceptDTO rDTO = new ReceptDTO(10,"Agurk");
		
		recept.createRecept(rDTO);
		System.out.println("Inserting: "+rDTO);
		rDTO = recept.getRecept(10);
		System.out.println("Pulling out again: "+rDTO);
		System.out.println("-------------------------------------------------------------");
		List<ReceptDTO> rDTOList = recept.getReceptList();
		System.out.println("Printing all receipts");
		for(ReceptDTO dto : rDTOList)
		{
			System.out.println(dto);
		}
		recept.updateRecept(new ReceptDTO(10, "Lort"));
		System.out.println("Updating "+rDTO);
		rDTO = recept.getRecept(10);
		System.out.println("Is now: "+rDTO);
>>>>>>> branch 'master' of https://github.com/Gruppe25DTU/Database-projekt
		
		System.out.println("get new raavare id = 8.");
		System.out.println(r.getRaavare(8));
		
<<<<<<< HEAD
		System.out.println("Update new raavare id = 8, name = Ananas pizza.");
		r.updateRaavare(new RaavareDTO(8,"Ananas Pizza", "Knoor"));
		System.out.println(r.getRaavare(8));
=======
	}
	
	public static void testReceptKomp() throws DALException {
		ReceptKompDAO rk = new MySQLReceptKompDAO();
		ReceptKompDTO testKomp = new ReceptKompDTO(10,12,24,88);
		System.out.println("Before Insertion: "+testKomp);
		rk.createReceptKomp(testKomp);
		testKomp = rk.getReceptKomp(10, 12);
		System.out.println("After insertion: "+testKomp);
		List<ReceptKompDTO> testList = rk.getReceptKompList();
		for(ReceptKompDTO t : testList)
		{
			System.out.println(t);
		}
		testList = rk.getReceptKompList(10);
		for(ReceptKompDTO t : testList)
		{
			System.out.println(t);
		}
>>>>>>> branch 'master' of https://github.com/Gruppe25DTU/Database-projekt
		
		System.out.println("Raavare list");
		List<RaavareDTO> raavareList = r.getRaavareList();
		for(int i = 0;i<raavareList.size();i++) {
			System.out.println(raavareList.get(i));
		}

		
	}
	
	
	
	
			

	
	
}
