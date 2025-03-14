package com.mysite.sbb.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);    // findBySubject 메소드 추가(기본으로 제공되지 않음)

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);

    Page<Question> findAll(Specification<Question> spec, Pageable pageable);

    @Query("SELECT distinct q FROM Question q " +
            "LEFT OUTER JOIN SiteUser u1 ON q.author = u1 " +
            "LEFT OUTER JOIN Answer a ON a.question=q " +
            "LEFT OUTER JOIN SiteUser u2 ON a.author=u2 " +
            "WHERE " +
            "q.subject LIKE %:kw% " +
            "or q.content LIKE %:kw% " +
            "or u1.username LIKE %:kw% " +
            "or a.content LIKE %:kw% " +
            "or u2.username LIKE %:kw%")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
}
