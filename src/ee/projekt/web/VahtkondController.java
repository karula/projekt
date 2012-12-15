package ee.projekt.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

import ee.projekt.entities.Piiriloik;
import ee.projekt.entities.Piiripunkt;
import ee.projekt.entities.Vaeosa;
import ee.projekt.entities.Vahtkond;

@Controller
public class VahtkondController {
	
	@RequestMapping(value="/vahtkond", method=RequestMethod.GET)
	public String showVahtkond(Model model){
			
		ArrayList<Vahtkond> vahtkondlist = selectAllVahtkonds();		
		model.addAttribute("vklist",vahtkondlist);
		
		return "showvahtkonds";
	}
	
	@RequestMapping(value="/vahtkond", params = "id", method=RequestMethod.GET)
	public String modifyVahtKond(@RequestParam
			int id, Model model) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		Vahtkond kond = new Vahtkond(); 
		try{
		kond = em.find(Vahtkond.class, id);
		}
		finally{
			em.close();
			emf.close();
		}
		
		model.addAttribute("vahtkond", kond);
		
		ArrayList<Piiripunkt> ppunktlist = selectAllPiiripunkts();
		model.addAttribute("ppunktlist",ppunktlist);
		
		ArrayList<Vaeosa> vosalist = selectAllVaeosas();
		model.addAttribute("vosalist",vosalist);
	
		return "vahtkond";
	}
	
	@RequestMapping(value="/vahtkond", method=RequestMethod.POST)
	public String modifyVahtkond(@ModelAttribute Vahtkond vahtkond, Model model, 
			HttpServletRequest request) {
	
		System.out.println("Vaeosa: " + request.getParameter("drp_vaeosa").toString());
		System.out.println("Piiripunkti: " +request.getParameter("drp_piiripunkt").toString());

		updateVahtkondById(vahtkond, Integer.parseInt(request.getParameter("drp_vaeosa")),Integer.parseInt(request.getParameter("drp_piiripunkt")));
		
		return "redirect:/vahtkond";
	}
	
	@RequestMapping(value="/addvahtkond", method=RequestMethod.GET)
	public String addVahtKond( Model model) {

		ArrayList<Piiripunkt> ppunktlist = selectAllPiiripunkts();
		model.addAttribute("ppunktlist",ppunktlist);
		
		ArrayList<Vaeosa> vosalist = selectAllVaeosas();
		model.addAttribute("vosalist",vosalist);
		
		return "addvahtkond";
	}
	
	@RequestMapping(value="/addvahtkond", method=RequestMethod.POST)
	public String addVahtKond(@ModelAttribute Vahtkond vahtkond, Model model, 
			HttpServletRequest request) {
		
		
		insertVahtkond(vahtkond, Integer.parseInt(request.getParameter("drp_vaeosa")),Integer.parseInt(request.getParameter("drp_piiripunkt")));
		
		
		return "redirect:/piiripunktForm";
	}
	
	@RequestMapping(value="/cancelvahtkond", method=RequestMethod.POST)
	public String cancelPiiripunkt(Model model) {

		return "redirect:/vahtkond";
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
	
	private ArrayList<Vahtkond> selectAllVahtkonds() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		ArrayList<Vahtkond> vahtkond = (ArrayList<Vahtkond>) em.createQuery("SELECT p FROM Vahtkond p").getResultList();
		
		em.getTransaction().commit();
		
		return vahtkond;
	}
	
	private ArrayList<Vaeosa> selectAllVaeosas() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		ArrayList<Vaeosa> vaeosa = (ArrayList<Vaeosa>) em.createQuery("SELECT p FROM Vaeosa p").getResultList();
		
		em.getTransaction().commit();
		
		return vaeosa;
	}
	
	private void updateVahtkondById (Vahtkond vahtkond, int vosa_id, int ppunkt_id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		
		Vahtkond vkond = em.find(Vahtkond.class, vahtkond.getId());
		
		try{
		      em.getTransaction().begin();
		      vkond.setKommentaar(vahtkond.getKommentaar().toString());
		      vkond.setKood(vahtkond.getKood().toString());
		      vkond.setNimetus(vahtkond.getNimetus().toString());
		      vkond.setId((int)vahtkond.getId());
		      vkond.setPiiripunkt(em.find(Piiripunkt.class, ppunkt_id));
		      vkond.setVaeosa(em.find(Vaeosa.class, vosa_id));
		      em.getTransaction().commit();
		    }
		    finally{
		      em.close();
		    }
		}
	
	private void insertVahtkond(Vahtkond vahtkond, int vosa_id, int ppunkt_id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		try{
		
		em.getTransaction().begin();
		vahtkond.setPiiripunkt(em.find(Piiripunkt.class, ppunkt_id));
	    vahtkond.setVaeosa(em.find(Vaeosa.class, vosa_id));
		
		em.persist(vahtkond);
		em.getTransaction().commit();
		}
		finally{
			em.close();
			emf.close();
		}
		
	}
	
}	