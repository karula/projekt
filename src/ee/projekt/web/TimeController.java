package ee.projekt.web;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TimeController {

	@RequestMapping("/currentTime")
	@ResponseBody
	public String currentTime(
			@RequestParam(required = false) String timeInMillis, Model model) {
		try {
			Long time = Long.parseLong(timeInMillis);
			return new Date(time).toString();
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}

		return "Paremeetri sisestuses läks midagi valesti";
	}

	@RequestMapping(value = "/timeTillTheEnd", method = RequestMethod.GET)
	public String printTimeTillTheEnd(Model model) {
		model.addAttribute("timeTillTheEnd", new Date(4449546671000L));
		return "time";
	}
}
