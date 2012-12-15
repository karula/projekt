package ee.projekt.web;




import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.bcel.internal.generic.Select;

import ee.projekt.entities.PiiriloiguHaldaja;
import ee.projekt.entities.Piiriloik;
import ee.projekt.entities.Piiripunkt;

import ee.projekt.entities.Vahtkond;
import ee.projekt.entities.VahtkondPiiriloigul;

@Controller
public class PiiriloiguHaldajaController {
	/*
	@RequestMapping(value="/vahtkond", method=RequestMethod.GET)
	public String showVahtkond(Model model){
			
		ArrayList<Vahtkond> vahtkondlist = selectAllVahtkonds();		
		model.addAttribute("vklist",vahtkondlist);
		
		return "showvahtkonds";
	}
	*/
	@RequestMapping(value="/piiriloiguhaldaja", params = "id", method=RequestMethod.GET)
	public String modifyVahtKond(@RequestParam
			int id, Model model) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		PiiriloiguHaldaja plhaldaja = new PiiriloiguHaldaja(); 
		try{
			plhaldaja = em.find(PiiriloiguHaldaja.class, id);
		}
		finally{
			em.close();
			emf.close();
		}
		
		model.addAttribute("plhaldaja", plhaldaja);
		
		ArrayList<Piiripunkt> ppunktlist = selectAllPiiripunkts();
		model.addAttribute("ppunktlist",ppunktlist);
		
		ArrayList<Piiriloik> ploiklist = selectAllPiiriloiks();
		model.addAttribute("ploiklist",ploiklist);
	
		return "piiriloiguhaldaja";
	}
	
	@RequestMapping(value="/piiriloiguhaldaja", method=RequestMethod.POST)
	public String modifyVahtkond(@ModelAttribute PiiriloiguHaldaja piiriloiguHaldaja, Model model, 
			HttpServletRequest request) {
	
		
		System.out.println("Vahtkond: " + request.getParameter("drp_piiripunkt").toString());
		System.out.println("Piiriloik: " +request.getParameter("drp_piiriloik").toString());
		
		updatePiiriloiguHaldajaById(piiriloiguHaldaja, Integer.parseInt(request.getParameter("drp_piiripunkt")),Integer.parseInt(request.getParameter("drp_piiriloik")));
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/addpiiriloiguhaldaja", method=RequestMethod.GET)
	public String addPiiriloiguHaldaja( Model model) {

		ArrayList<Piiripunkt> ppunktlist = selectAllPiiripunkts();
		model.addAttribute("ppunktlist",ppunktlist);
		
		ArrayList<Piiriloik> ploiklist = selectAllPiiriloiks();
		model.addAttribute("ploiklist",ploiklist);
		
		return "addpiiriloiguhaldaja";
	}
	
	@RequestMapping(value="/addpiiriloiguhaldaja", method=RequestMethod.POST)
	public String addPiiriloiguHaldaja(@ModelAttribute PiiriloiguHaldaja piiriloiguhaldaja, Model model, 
			HttpServletRequest request) {
		
		//System.out.println("POST Alates: " + vahtkondpiiriloigul.getAlates());
		
		insertPiiriloiguHaldaja(piiriloiguhaldaja, Integer.parseInt(request.getParameter("drp_piiripunkt")),Integer.parseInt(request.getParameter("drp_piiriloik")));
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/cancelpiiriloiguhaldaja", method=RequestMethod.POST)
	public String cancelPiiripunkt(Model model) {

		return "redirect:/";
	}
	
	private ArrayList<Piiriloik> selectAllPiiriloiks() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		ArrayList<Piiriloik> piiriloik = (ArrayList<Piiriloik>) em.createQuery("SELECT p FROM Piiriloik p").getResultList();
		
		em.getTransaction().commit();
		
		return piiriloik;
	}
	
	private ArrayList<Piiripunkt> selectAllPiiripunkts() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		ArrayList<Piiripunkt> piiripunkt = (ArrayList<Piiripunkt>) em.createQuery("SELECT p FROM Piiripunkt p").getResultList();
		
		em.getTransaction().commit();
		
		return piiripunkt;
	}
	/*
	private ArrayList<Vahtkond> selectAllVahtkonds() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		ArrayList<Vahtkond> vahtkond = (ArrayList<Vahtkond>) em.createQuery("SELECT p FROM Vahtkond p").getResultList();
		
		em.getTransaction().commit();
		
		return vahtkond;
	}
	
	private ArrayList<Piiriloik> selectAllPiiriloiks() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		ArrayList<Piiriloik> ploik = (ArrayList<Piiriloik>) em.createQuery("SELECT p FROM Piiriloik p").getResultList();
		
		em.getTransaction().commit();
		
		return ploik;
	}
	*/
	private void updatePiiriloiguHaldajaById (PiiriloiguHaldaja piiriloiguHaldaja, int ppunkt_id, int ploik_id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		
		PiiriloiguHaldaja plhaldaja = em.find(PiiriloiguHaldaja.class, piiriloiguHaldaja.getId());
		
		try{
		      em.getTransaction().begin();
		      plhaldaja.setAlates(piiriloiguHaldaja.getAlates());
		      plhaldaja.setKuni(piiriloiguHaldaja.getKuni());
		      //vkploigul.setId((int)vahtkond.getId());
		      plhaldaja.setPiiripunkt_id(em.find(Piiripunkt.class, ppunkt_id));
		      plhaldaja.setPiiriloik_id(em.find(Piiriloik.class, ploik_id));
		      em.getTransaction().commit();
		    }
		    finally{
		      em.close();
		    }
		}
	
	private void insertPiiriloiguHaldaja(PiiriloiguHaldaja piiriloiguhaldaja, int ppunkt_id, int ploik_id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		try{
		
		em.getTransaction().begin();
		piiriloiguhaldaja.setPiiripunkt_id(em.find(Piiripunkt.class, ppunkt_id));
		piiriloiguhaldaja.setPiiriloik_id(em.find(Piiriloik.class, ploik_id));

		em.persist(piiriloiguhaldaja);
		em.getTransaction().commit();
		}
		finally{
			em.close();
			emf.close();
		}
		
	}
	
	
}	