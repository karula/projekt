package ee.projekt.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import ee.projekt.entities.Piiriloik;
import ee.projekt.entities.Vahtkond;
import ee.projekt.entities.VahtkondPiiriloigul;

@Controller
public class VahtkondPiiriLoigulController {
	/*
	@RequestMapping(value="/vahtkond", method=RequestMethod.GET)
	public String showVahtkond(Model model){
			
		ArrayList<Vahtkond> vahtkondlist = selectAllVahtkonds();		
		model.addAttribute("vklist",vahtkondlist);
		
		return "showvahtkonds";
	}
	*/
	@RequestMapping(value="/vahtkondpiiriloigul", params = "id", method=RequestMethod.GET)
	public String modifyVahtKond(@RequestParam
			int id, Model model) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		VahtkondPiiriloigul vkploigul = new VahtkondPiiriloigul(); 
		try{
			vkploigul = em.find(VahtkondPiiriloigul.class, id);
		}
		finally{
			em.close();
			emf.close();
		}
		
		model.addAttribute("vkploigul", vkploigul);
		
		ArrayList<Vahtkond> vkondlist = selectAllVahtkonds();
		model.addAttribute("vkondlist",vkondlist);
		
		ArrayList<Piiriloik> ploiklist = selectAllPiiriloiks();
		model.addAttribute("ploiklist",ploiklist);
	
		return "vahtkondpiiriloigul";
	}
	
	@RequestMapping(value="/vahtkondpiiriloigul", method=RequestMethod.POST)
	public String modifyVahtkond(@ModelAttribute VahtkondPiiriloigul vahtkondpiiriloigul, Model model, 
			HttpServletRequest request) {
	
		
		System.out.println("Vahtkond: " + request.getParameter("drp_vahtkond").toString());
		System.out.println("Piiriloik: " +request.getParameter("drp_piiriloik").toString());
		
		updateVahtkondPiiriloigulById(vahtkondpiiriloigul, Integer.parseInt(request.getParameter("drp_vahtkond")),Integer.parseInt(request.getParameter("drp_piiriloik")));
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/addvahtkondpiiriloigul", method=RequestMethod.GET)
	public String addVahtKondPiiriLoigul( Model model) {

		ArrayList<Vahtkond> vkondlist = selectAllVahtkonds();
		model.addAttribute("vkondlist",vkondlist);
		
		ArrayList<Piiriloik> ploiklist = selectAllPiiriloiks();
		model.addAttribute("ploiklist",ploiklist);
		
		return "addvahtkondpiiriloigul";
	}
	
	@RequestMapping(value="/addvahtkondpiiriloigul", method=RequestMethod.POST)
	public String addVahtKondPiiriLoigul(@ModelAttribute VahtkondPiiriloigul vahtkondpiiriloigul, Model model, 
			HttpServletRequest request) {
		
		//System.out.println("POST Alates: " + vahtkondpiiriloigul.getAlates());
		
		insertVahtkondPiiriLoigul(vahtkondpiiriloigul, Integer.parseInt(request.getParameter("drp_vahtkond")),Integer.parseInt(request.getParameter("drp_piiriloik")));
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/cancelvahtkondpiiriloigul", method=RequestMethod.POST)
	public String cancelPiiripunkt(Model model) {

		return "redirect:/";
	}
	
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
	
	private void updateVahtkondPiiriloigulById (VahtkondPiiriloigul vahtkondpiiriloigul, int vkond_id, int ploik_id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		
		VahtkondPiiriloigul vkploigul = em.find(VahtkondPiiriloigul.class, vahtkondpiiriloigul.getId());
		
		try{
		      em.getTransaction().begin();
		      vkploigul.setAlates(vahtkondpiiriloigul.getAlates());
		      vkploigul.setKuni(vahtkondpiiriloigul.getKuni());
		      //vkploigul.setId((int)vahtkond.getId());
		      vkploigul.setVahtkond_id(em.find(Vahtkond.class, vkond_id));
		      vkploigul.setPiiriloik_id(em.find(Piiriloik.class, ploik_id));
		      em.getTransaction().commit();
		    }
		    finally{
		      em.close();
		    }
		}
	
	private void insertVahtkondPiiriLoigul(VahtkondPiiriloigul vahtkondpiiriloigul, int vkond_id, int ploik_id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projekt");
		EntityManager em = emf.createEntityManager();
		try{
		
		em.getTransaction().begin();
		vahtkondpiiriloigul.setVahtkond_id(em.find(Vahtkond.class, vkond_id));
		vahtkondpiiriloigul.setPiiriloik_id(em.find(Piiriloik.class, ploik_id));
		
		//TODO date picker
		/*
		Date date = new Date();
		System.out.println("Aeg: " + date);
		vahtkondpiiriloigul.setAlates(date);
		vahtkondpiiriloigul.setKuni(date);
		*/
		em.persist(vahtkondpiiriloigul);
		em.getTransaction().commit();
		}
		finally{
			em.close();
			emf.close();
		}
		
	}
	
	
}	