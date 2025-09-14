import React, { useState, useEffect } from "react";
import { getQuizById, submitQuizAttempt } from "../utils/api";
import "./QuizAttempt.css";

const QuizAttempt = ({ quizId, userId, onComplete }) => {
  const [quiz, setQuiz] = useState(null);
  const [answers, setAnswers] = useState({});
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [timeLeft, setTimeLeft] = useState(0);

  useEffect(() => {
    getQuizById(quizId)
      .then((quizData) => {
        setQuiz(quizData);
        setTimeLeft(quizData.timeLimit * 60); // Convert minutes to seconds
      })
      .catch(() => console.error("Failed to load quiz"))
      .finally(() => setLoading(false));
  }, [quizId]);

  useEffect(() => {
    if (timeLeft > 0 && !submitting) {
      const timer = setTimeout(() => setTimeLeft(timeLeft - 1), 1000);
      return () => clearTimeout(timer);
    } else if (timeLeft === 0 && quiz) {
      handleSubmit(new Event('submit'));
    }
  }, [timeLeft, submitting, quiz]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);

    try {
      const answersArray = Object.entries(answers).map(([qId, selectedText]) => {
        const question = quiz.questions.find(q => q.id === parseInt(qId));
        const selectedOption = question?.options?.find(opt => opt.optionText === selectedText);
        return {
          questionId: parseInt(qId),
          selectedOptionId: selectedOption?.id
        };
      });

      const result = await submitQuizAttempt({
        quizId,
        userId,
        studentName: "Student", // Default student name
        answers: answersArray
      });
      
      // Transform result to expected format
      const transformedResult = {
        score: result.score,
        totalQuestions: result.totalQuestions,
        percentage: Math.round((result.score / result.totalQuestions) * 100)
      };
      
      onComplete(transformedResult);
    } catch (error) {
      console.error('Failed to submit quiz:', error);
      alert('Failed to submit quiz. Please try again.');
    } finally {
      setSubmitting(false);
    }
  };

  if (loading) return <p>Loading quiz...</p>;
  if (!quiz) return <p>Quiz not found</p>;

  const formatTime = (seconds) => {
    const mins = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${mins}:${secs.toString().padStart(2, '0')}`;
  };

  return (
    <div className="quiz-attempt-container">
      <div className="quiz-header">
        <h2>{quiz.title || 'Quiz'}</h2>
        <div className={`timer ${timeLeft <= 60 ? 'timer-warning' : ''}`}>
          Time Left: {formatTime(timeLeft)}
        </div>
      </div>
      <p>{quiz.description || ''}</p>
      
      {(!quiz.questions || quiz.questions.length === 0) ? (
        <div>
          <p>No questions available for this quiz yet.</p>
          <button onClick={() => onComplete(null)}>Back to Quiz List</button>
        </div>
      ) : (
        <form onSubmit={handleSubmit}>
          {quiz.questions.map((q) => (
            <div key={q.id} className="question-container">
              <h4>{q.questionText}</h4>
              {q.options && q.options.length > 0 ? (
                q.options.map((opt) => (
                  <label key={opt.id} className="option-label">
                    <input
                      type="radio"
                      name={`q_${q.id}`}
                      value={opt.optionText}
                      checked={answers[q.id] === opt.optionText}
                      onChange={() => setAnswers({ ...answers, [q.id]: opt.optionText })}
                    />
                    {opt.optionText}
                  </label>
                ))
              ) : (
                <p>No options available for this question.</p>
              )}
            </div>
          ))}
          <div className="quiz-buttons">
            <button type="submit" disabled={submitting}>
              {submitting ? "Submitting..." : "Submit Quiz"}
            </button>
            <button type="button" onClick={() => onComplete(null)}>Cancel</button>
          </div>
        </form>
      )}
    </div>
  );
};

export default QuizAttempt;