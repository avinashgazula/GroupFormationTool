package com.group8.dalsmartteamwork.questionmanager.controller;

import com.group8.dalsmartteamwork.questionmanager.dao.DeleteDao;
import com.group8.dalsmartteamwork.questionmanager.dao.DeleteDaoImpl;
import com.group8.dalsmartteamwork.questions.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class DeleteController {

    @GetMapping("/listDeleteQuestions")
    public String listDeleteQuestion(Principal principal, Model model) {
        DeleteDao deleteDaoImpl = new DeleteDaoImpl();
        Question question = new Question();
        List<Question> sList = deleteDaoImpl.displayListOfQuestions(principal.getName());
        model.addAttribute("list", sList);
        model.addAttribute("question", question);
        return "listDeleteQuestions";
    }

    @RequestMapping(value = "/listDeleteQuestions", method = RequestMethod.POST)
    public String deleteQuestion(@ModelAttribute("question") Question question, Principal principal, Model model,
                                 RedirectAttributes redirectAttributes) {
        DeleteDao deleteDaoImpl = new DeleteDaoImpl();
        Boolean status = deleteDaoImpl.deleteQuestion(question.getQuestionID());
        if (status != true) {
            redirectAttributes.addFlashAttribute("message", "Failed to delete the question");
            return "redirect:/questionManager";
        }
        redirectAttributes.addFlashAttribute("message", "Successfully deleted the question!");
        return "redirect:/questionManager";

    }
}