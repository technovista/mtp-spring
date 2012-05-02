package com.array.testprep.model;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rubyuser
 */
@Entity
@XmlRootElement
@Table(name = "questionanswerrelationship")
@NamedQueries({@NamedQuery(name = "Questionanswerrelationship.findAll", query = "SELECT q FROM Questionanswerrelationship q"), @NamedQuery(name = "Questionanswerrelationship.findByQuestionAnswerId", query = "SELECT q FROM Questionanswerrelationship q WHERE q.questionAnswerId = :questionAnswerId"), @NamedQuery(name = "Questionanswerrelationship.findByChoiceDisplayOrder", query = "SELECT q FROM Questionanswerrelationship q WHERE q.choiceDisplayOrder = :choiceDisplayOrder"), @NamedQuery(name = "Questionanswerrelationship.findByIsCorrectAnswer", query = "SELECT q FROM Questionanswerrelationship q WHERE q.isCorrectAnswer = :isCorrectAnswer"), @NamedQuery(name = "Questionanswerrelationship.findByCorrectAnswerDisplayOrder", query = "SELECT q FROM Questionanswerrelationship q WHERE q.correctAnswerDisplayOrder = :correctAnswerDisplayOrder")})
public class QuestionAnswerRelationship implements ServiceDO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "questionAnswerId")
    private Integer questionAnswerId;
    @Basic(optional = false)
    @Column(name = "choiceDisplayOrder")
    private int choiceDisplayOrder;
    @Basic(optional = false)
    @Column(name = "isCorrectAnswer")
    private String isCorrectAnswer;
    @Basic(optional = false)
    @Column(name = "correctAnswerDisplayOrder")
    private int correctAnswerDisplayOrder;
    @JoinColumn(name = "questionId", referencedColumnName = "questionId")
    @ManyToOne(optional = false)
    private Integer questionId;
    @JoinColumn(name = "answerId", referencedColumnName = "answerId")
    @ManyToOne(optional = false)
    private Integer answerId;
    @JoinColumn(name = "testObjectiveId", referencedColumnName = "testObjectiveId")
    @ManyToOne(optional = false)
    private Integer testObjectiveId;
    private QuestionBank questionBank;
    private TestObjective testObjective;
    
    
    public TestObjective getTestObjective() {
		return testObjective;
	}

	public void setTestObjective(TestObjective testObjective) {
		this.testObjective = testObjective;
	}

	public QuestionBank getQuestionBank() {
		return questionBank;
	}

	public void setQuestionBank(QuestionBank questionBank) {
		this.questionBank = questionBank;
	}

	public AnswerBank getAnswerBank() {
		return answerBank;
	}

	public void setAnswerBank(AnswerBank answerBank) {
		this.answerBank = answerBank;
	}

	private AnswerBank answerBank;

    public QuestionAnswerRelationship() {
    }

    public QuestionAnswerRelationship(Integer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    public QuestionAnswerRelationship(Integer questionAnswerId, int choiceDisplayOrder, String isCorrectAnswer, int correctAnswerDisplayOrder) {
        this.questionAnswerId = questionAnswerId;
        this.choiceDisplayOrder = choiceDisplayOrder;
        this.isCorrectAnswer = isCorrectAnswer;
        this.correctAnswerDisplayOrder = correctAnswerDisplayOrder;
    }

    public Integer getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Integer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    public int getChoiceDisplayOrder() {
        return choiceDisplayOrder;
    }

    public void setChoiceDisplayOrder(int choiceDisplayOrder) {
        this.choiceDisplayOrder = choiceDisplayOrder;
    }

    public String getIsCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setIsCorrectAnswer(String isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public int getCorrectAnswerDisplayOrder() {
        return correctAnswerDisplayOrder;
    }

    public void setCorrectAnswerDisplayOrder(int correctAnswerDisplayOrder) {
        this.correctAnswerDisplayOrder = correctAnswerDisplayOrder;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getTestObjectiveId() {
        return testObjectiveId;
    }

    public void setTestObjectiveId(Integer testObjectiveId) {
        this.testObjectiveId = testObjectiveId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionAnswerId != null ? questionAnswerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionAnswerRelationship)) {
            return false;
        }
        QuestionAnswerRelationship other = (QuestionAnswerRelationship) object;
        if ((this.questionAnswerId == null && other.questionAnswerId != null) || (this.questionAnswerId != null && !this.questionAnswerId.equals(other.questionAnswerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "converter.Questionanswerrelationship[questionAnswerId=" + questionAnswerId + "]";
    }

}
