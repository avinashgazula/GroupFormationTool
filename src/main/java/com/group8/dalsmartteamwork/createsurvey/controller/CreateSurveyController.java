package com.group8.dalsmartteamwork.createsurvey.controller;

import com.group8.dalsmartteamwork.course.Course;
import com.group8.dalsmartteamwork.createsurvey.dao.ICreateSurveyDao;
import com.group8.dalsmartteamwork.createsurvey.dao.CreateSurveyDaoImpl;
import com.group8.dalsmartteamwork.createsurvey.model.CreateSurvey;
import com.group8.dalsmartteamwork.createsurvey.model.CreateSurveyImpl;
import com.group8.dalsmartteamwork.questions.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateSurveyController {
    public Integer courseID;
    public List<Question> questions = new ArrayList<Question>();

    @GetMapping("/createSurveyPage")
    public String listCourses(Principal principal, Model model) {
        ICreateSurveyDao iCreateSurveyDao = new CreateSurveyDaoImpl();
        CreateSurvey createSurvey = new CreateSurveyImpl(iCreateSurveyDao);
        model.addAttribute(new Course());
        List<Course> courses = createSurvey.displayListOfCourses(principal.getName());
        model.addAttribute("list", courses);
        return "createSurveyPage";
    }

    @RequestMapping(value = "/createSurveyPage", method = RequestMethod.POST)
    public String deleteQuestion(@ModelAttribute("course") Course course, Principal principal, Model model,
            RedirectAttributes redirectAttributes) {
        ICreateSurveyDao iCreateSurveyDao = new CreateSurveyDaoImpl();
        CreateSurvey createSurvey = new CreateSurveyImpl(iCreateSurveyDao);
        courseID = course.getCourseID();
        Boolean status = createSurvey.checkIfSurveyCreated(course.getCourseID());
        if (status != true) {
            redirectAttributes.addFlashAttribute("message", "Survey already exists");
            return "redirect:/instructor";
        }
        return "redirect:/surveyPage";
    }

    @GetMapping("/surveyPage")
    public String displaySurveyPage(Principal principal, Model model) {
        ICreateSurveyDao iCreateSurveyDao = new CreateSurveyDaoImpl();
        CreateSurvey createSurvey = new CreateSurveyImpl(iCreateSurveyDao);
        Question question = new Question();
        questions = createSurvey.displayQuestions(principal.getName(), courseID);
        model.addAttribute("list", questions);
        model.addAttribute("question", question);
        return "surveyPage";
    }

    @RequestMapping(value = "/surveyPage", method = RequestMethod.POST, params="action=save")
    public String saveQuestion(@RequestParam("question") List<Integer> values, Principal principal, Model model,
            RedirectAttributes redirectAttributes) {
        ICreateSurveyDao iCreateSurveyDao = new CreateSurveyDaoImpl();
        CreateSurvey createSurvey = new CreateSurveyImpl(iCreateSurveyDao);
        Boolean status = createSurvey.saveQuestions(courseID, values);
        if (status != true) {
            redirectAttributes.addFlashAttribute("message", "Questions couldn't be added");
            return "redirect:/instructor";
        }
        redirectAttributes.addFlashAttribute("message", "Questions saved");
        return "redirect:/instructor";
    }

    @RequestMapping(value = "/surveyPage", method = RequestMethod.POST, params="action=publish")
    public String publishSurvey(Principal principal, Model model,
            RedirectAttributes redirectAttributes) {
        ICreateSurveyDao iCreateSurveyDao = new CreateSurveyDaoImpl();
        CreateSurvey createSurvey = new CreateSurveyImpl(iCreateSurveyDao);
        Boolean status = createSurvey.publishSurvey(courseID);
        if (status != true) {
            redirectAttributes.addFlashAttribute("message", "Add questions to publish");
            return "redirect:/surveyPage";
        }
        redirectAttributes.addFlashAttribute("message", "Survey published");
        return "redirect:/instructor";
    }

}