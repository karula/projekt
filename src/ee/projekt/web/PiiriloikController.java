package ee.projekt.web;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ee.projekt.entities.Piiriloik;
import ee.projekt.entities.Piiripunkt;

@Controller
public class PiiriloikController {
	
	@RequestMapping(value="/piiriloik", method=RequestMethod.GET)
	public String showPiiriloiks(Model model){
		
		
		ArrayList<Piiriloik> ppunktlist = selectAllPiiriloiks();
		
		//List results = selectAllPiiriloiks();
		
		model.addAttribute("plist",ppunktlist);
		
		return "showpiiriloiks";
	}
	
	@RequestMapping(value="/piiriloik", params = "id", method=RequestMethod.GET)
	public String modifyPiiriloik(@RequestParam
			int id, Model model) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		Piiriloik loik = new Piiriloik(); 
		try{
		loik = em.find(Piiriloik.class, id);
		}
		finally{
			em.close();
			emf.close();
		}
		
		model.addAttribute("piiriloik", loik);
		
		return "piiriloik";
	}
	
	@RequestMapping(value="/piiriloik", method=RequestMethod.POST)
	public String modifyPiiriLoik(@ModelAttribute @Valid Piiriloik piiriloik, Model model, 
			HttpServletRequest request) {

		updatePiiriloikById(piiriloik);
		
		return "redirect:piiriloik";
		
	}
	
	@RequestMapping(value="/addpiiriloik", method=RequestMethod.GET)
	public String addPiiriLoik( Model model) {

		return "addpiiriloik";
	}
	
	@RequestMapping(value="/addpiiriloik", method=RequestMethod.POST)
	public String addPiiriLoik(@ModelAttribute @Valid Piiriloik piiriloik, Model model, 
			HttpServletRequest request) {
		
		insertPiiriLoik(piiriloik);
	
		return "redirect:/piiriloik";
	}
	
	@RequestMapping(value="/deletepiiriloik", method=RequestMethod.POST)
	public String deletePiiriloik(@ModelAttribute Piiriloik piiriloik, Model model) {
		
		System.out.println("Kustatamisele kuuluva lõigu id: " + piiriloik.getId());
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		
		Piiriloik loik = em.find(Piiriloik.class, piiriloik.getId());
		try{	
		em.getTransaction().begin();
		em.remove(loik);
		//em.remove((Piiripunkt)findPiiripunktById(piiriPunkt.getId(), em));
		em.getTransaction().commit();
		}
		finally{
			em.close();
			emf.close();
		}
		
		return "redirect:/piiriloik";
	}
	

	@RequestMapping(value="/cancelpiiriloik", method=RequestMethod.POST)
	public String cancelPiiriLoik(Model model) {

		return "redirect:/piiriloik";
	}
	
	private void insertPiiriLoik(Piiriloik piiriloik) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		try{
		
		em.getTransaction().begin();
		em.persist(piiriloik);
		em.getTransaction().commit();
		}
		finally{
			em.close();
			emf.close();
		}
		
	}

	private ArrayList<Piiriloik> selectAllPiiriloiks() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		ArrayList<Piiriloik> piiriloik = (ArrayList<Piiriloik>) em.createQuery("SELECT p FROM Piiriloik p").getResultList();
		
		em.getTransaction().commit();
		
		return piiriloik;
	}
	
	private void updatePiiriloikById (Piiriloik piiriloik){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		
		Piiriloik loik = em.find(Piiriloik.class, piiriloik.getId());
		
		try{
		      em.getTransaction().begin();
		      loik.setKommentaar(piiriloik.getKommentaar().toString());
		      loik.setKood(piiriloik.getKood().toString());
		      loik.setNimetus(piiriloik.getNimetus().toString());
		      loik.setId((int)piiriloik.getId());
		      loik.setGpskoordinaadid(piiriloik.getGpskoordinaadid());
		      loik.setKorgusmerepinnast(piiriloik.getKorgusmerepinnast());
		      em.getTransaction().commit();
		    }
		    finally{
		      em.close();
		    }
		}
}
