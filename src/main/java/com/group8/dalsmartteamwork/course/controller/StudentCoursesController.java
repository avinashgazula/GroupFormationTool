package com.group8.dalsmartteamwork.course.controller;

import com.group8.dalsmartteamwork.student.dao.StudentDao;
import com.group8.dalsmartteamwork.student.dao.StudentDaoImp;
import com.group8.dalsmartteamwork.student.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class StudentCoursesController {
    @GetMapping(value = "/student")
    public String getStudentCourse(Model model) {
        StudentDao coursePage = new StudentDaoImp();
        ArrayList<Student> courseList = coursePage.displayCourses();
        model.addAttribute("courses", courseList);
        return "student";
    }
}