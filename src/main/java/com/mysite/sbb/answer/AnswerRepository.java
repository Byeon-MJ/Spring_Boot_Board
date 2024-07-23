package com.mysite.sbb.answer;

import com.mysite.sbb.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{
    List<Answer> findByQuestionId(Integer questionId);
}
