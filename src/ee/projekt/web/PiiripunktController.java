package ee.projekt.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import ee.projekt.entities.Piiripunkt;




@Controller
public class PiiripunktController {
	
	private Logger log = LoggerFactory.getLogger(PiiripunktController.class);
	
	@RequestMapping(value="/piiripunkt", method=RequestMethod.GET)
	public String showPiiripunkts( Model model) {
		log.debug("Successful piiripunktform request");

		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();

		ArrayList<Piiripunkt> ppunktlist = (ArrayList<Piiripunkt>) selectAllPiiripunkts();
		
		List results = selectAllPiiripunkts();
		
		model.addAttribute("plist",ppunktlist);
		
		return "showpiiripunkts";
	}
	
	
	
	@RequestMapping(value="/piiripunkt", params = "id", method=RequestMethod.GET)
	public String modifyPiiripunkt(@RequestParam
			int id, Model model) {
		log.debug("Successful piiripunktform request");

		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		
		//System.out.println(paramId);
		
		Piiripunkt piiripunkt = findPiiripunktById(id,em);	
		
		em.close();
		emf.close();
		
		model.addAttribute("piiripunktModel", piiripunkt);
		
		return "piiripunkt";
	}
	
	
	@RequestMapping(value="/piiripunkt", method=RequestMethod.POST)
	public String guardForm(@ModelAttribute @Valid Piiripunkt piiriPunkt, Model model, 
			HttpServletRequest request) {
		//log.debug("Adding a new guard. Name: " + guard.getName() + " Age: " + guard.getAge());
		
		System.out.println("Request_Kommentaar: " + request.getParameter("Kommentaar").toString());
		System.out.println("Kommentaar: " + piiriPunkt.getKommentaar().toString());
		
		updatePiiripunktById(piiriPunkt);
		
		return "redirect:/piiripunkt";
		
	}
	




	@RequestMapping(value="/addpiiripunkt", method=RequestMethod.GET)
	public String addPiiriPunkt( Model model) {

		return "addpiiripunkt";
	}
	
	@RequestMapping(value="/addpiiripunkt", method=RequestMethod.POST)
	public String addPiiriPunkt(@ModelAttribute @Valid Piiripunkt piiriPunkt, Model model, 
			HttpServletRequest request) {
		
		ArrayList<Piiripunkt> ppunktlist = (ArrayList<Piiripunkt>) selectAllPiiripunkts();
		
		List results = selectAllPiiripunkts();
		
		model.addAttribute("plist",ppunktlist);
		
		
		insertPiiriPunkt(piiriPunkt);
		
		
		return "redirect:/piiripunkt";
	}
	
	@RequestMapping(value="/cancelpiiripunkt", method=RequestMethod.POST)
	public String cancelPiiripunkt(Model model) {
		log.debug("Successful piiripunktform request");

		return "redirect:/piiripunktForm";
	}
	
	@RequestMapping(value="/deletepiiripunkt", method=RequestMethod.POST)
	public String deletePiiripunkt(@ModelAttribute Piiripunkt piiriPunkt, Model model) {
		log.debug("Successful piiripunktform request");
		System.out.println("Kustatamisele kuuluva punkti id: " + piiriPunkt.getId());
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		
		Piiripunkt punkt = em.find(Piiripunkt.class, piiriPunkt.getId());
		try{	
		em.getTransaction().begin();
		em.remove(punkt);
		//em.remove((Piiripunkt)findPiiripunktById(piiriPunkt.getId(), em));
		em.getTransaction().commit();
		}
		finally{
			em.close();
			emf.close();
		}
		
		return "redirect:/piiripunkt";
	}
	
	 
	private void insertPiiriPunkt(Piiripunkt punkt) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		try{
		
		em.getTransaction().begin();
		
		em.persist(punkt);
		em.getTransaction().commit();
		System.out.println("Insert succeeded1");
		}
		finally{
			em.close();
			emf.close();
			System.out.println("Insert succeeded2");
		}
		
	}
	
	private Piiripunkt findPiiripunktById(int Id, EntityManager em){
		em.getTransaction().begin();
		Piiripunkt piiripunkt = 
				(Piiripunkt) em.createNamedQuery("Piiripunkt.findById").setParameter("id", Id).getSingleResult();
		em.getTransaction().commit();
		return piiripunkt;
	}
	
	private void updatePiiripunktById (Piiripunkt piiripunkt){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		
	
		Piiripunkt punkt = em.find(Piiripunkt.class, piiripunkt.getId());
		
		try{
		      em.getTransaction().begin();
		      punkt.setKommentaar(piiripunkt.getKommentaar().toString());
		      punkt.setKood(piiripunkt.getKood().toString());
		      punkt.setNimetus(piiripunkt.getNimetus().toString());
		      punkt.setId((int)piiripunkt.getId());
		      punkt.setGpslaiuskraad(piiripunkt.getGpslaiuskraad());
		      punkt.setGpspikkuskraad(piiripunkt.getGpspikkuskraad());
		      punkt.setKorgusmerepinnast(piiripunkt.getKorgusmerepinnast());
		      em.getTransaction().commit();
		    }
		    finally{
		      em.close();
		    }
		
	}
	
	private List selectAllPiiripunkts(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		
		//em.refresh("Piiripunkt");
		em.getTransaction().begin();
		
		List piiripunkt = em.createQuery("SELECT p FROM Piiripunkt p").getResultList();
		
		em.getTransaction().commit();
		
		return piiripunkt;
		
	}
	
}
