package com.sc.exam.sbb.answer;

import com.sc.exam.sbb.question.Question;
import com.sc.exam.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "answer", schema="sbb")
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;

  @ManyToOne
  private Question question;

  @ManyToOne
  private SiteUser author;
}
