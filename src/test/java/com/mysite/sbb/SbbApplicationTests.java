package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SbbApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionService questionService;

//    @Test
//    void testJpa() {
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가요?");
//        q1.setContent("sbb에 대해서 알고 싶습니다.");
//        q1.setCreateDate((LocalDateTime.now()));
//        this.questionRepository.save(q1);   // 첫번째 질문 저장
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setCreateDate((LocalDateTime.now()));
//        this.questionRepository.save(q2);   // 두번째 질문 저장
//    }

    @Test
    void testFindAll() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(1, all.size());    // 테스트에서 에상한 결과와 실제 결과가 동일한지 확인

        Question q1 = all.get(0);
        assertEquals("스프링부트 모델 질문입니다.", q1.getSubject());
    }

    @Test
    void testFindById() {
        Optional<Question> oq = this.questionRepository.findById(1);    // 반환값 Optional 주의
        if(oq.isPresent()) {    // Optional 값이 있는지 확인
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }

    @Test
    void testFindBySubject() {
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
    }

    @Test
    void testFindBySubjectAndContent() {
        Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        assertEquals(1, q.getId());
    }

    @Test
    void testFindBySubjectLike() {
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    void testUpdateEntity() {   // 데이터 수정하기
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    @Test
    void testDeleteData() { // 데이터 삭제하기
        assertEquals(2, this.questionRepository.count());   // count() : 행의 개수 리턴
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);  // 해당 데이터 삭제
        assertEquals(1, this.questionRepository.count());
    }

    @Test
    void testSaveAnswer() { // 답변 저장하기
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);   // 어떤 질문의 답변인지 알기 위해서 Question 객체가 필요
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

    @Test
    void testFindAnswerById() { // 답변 데이터 조회하기
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
    }

    @Test
    void testFindAnswerByQuestionId() { // 질문에 대한 답변 조회하기
        List<Answer> aList = this.answerRepository.findByQuestionId(2);
        Answer a = aList.get(0);
        System.out.println("Number of Answer: " + aList.size());
        assertEquals("네 자동으로 생성됩니다.", a.getContent());
    }

    // question 객체에서 answer 찾기
    @Transactional  // LazyInitializationException 방지
    @Test
    void testFindAnswerByQuestion() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> aList = q.getAnswerList();
        System.out.println("Number of Answer: " + aList.size());
        assertEquals(3, aList.size());
        assertEquals("네 자동으로 생성됩니다.", aList.get(0).getContent());
    }

    // 대용량 데이터 만들기
    @Test
    void testCreateData() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content, null);
        }
    }
}
