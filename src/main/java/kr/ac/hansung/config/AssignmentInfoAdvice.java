package kr.ac.hansung.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AssignmentInfoAdvice {

    @Value("${assignment.student-id:2020XXXXX}")
    private String studentId;

    @Value("${assignment.student-name:홍길동}")
    private String studentName;

    @ModelAttribute
    public void addAssignmentInfo(Model model) {
        model.addAttribute("studentId", studentId);
        model.addAttribute("studentName", studentName);
    }
}
