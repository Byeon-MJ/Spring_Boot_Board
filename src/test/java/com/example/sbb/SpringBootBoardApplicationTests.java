package com.example.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringBootBoardApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {
		// Test1. Create Contents
//		Question q1 = new Question();
//		q1.setSubject("What is SBB?");
//		q1.setContent("I want to know SBB");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1); // 첫번째 질문 저장
//
//		Question q2 = new Question();
//		q2.setSubject("The qeustion about SpringBoot Model");
//		q2.setContent("Is the ID generated automatically?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2); // 두번째 질문 저장

		// Test2. findAll
//		List<Question> all = this.questionRepository.findAll();
//		assertEquals(2, all.size());
//
//		Question q = all.get(0);
//		assertEquals("What is SBB?", q.getSubject());

		// Test3. findById
//		Optional<Question> oq = this.questionRepository.findById(1);
//		if(oq.isPresent()){
//			Question q = oq.get();
//			assertEquals("What is SBB?", q.getSubject());
//		}

		// Test4. findBySubject
//		Question q = this.questionRepository.findBySubject("What is SBB?");
//		assertEquals(1, q.getId());

		// Test5. findBySubjectAndContent
		Question q = this.questionRepository.findBySubjectAndContent(
				"What is SBB?", "I want to know SBB");
		assertEquals(1, q.getId());
	}
}
