package com.mysite.sbb.question;

import com.mysite.sbb.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject (String subject);    // findBySubject 메소드 추가(기본으로 제공되지 않음)
    Question findBySubjectAndContent (String subject, String content);
    List<Question> findBySubjectLike (String subject);
}
