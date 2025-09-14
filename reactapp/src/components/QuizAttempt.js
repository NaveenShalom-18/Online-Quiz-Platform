import React, { useState, useEffect } from "react";
import { getQuizById, submitQuizAttempt } from "../utils/api";
import "./QuizAttempt.css";

const QuizAttempt = ({ quizId, onComplete }) => {
  const [quiz, setQuiz] = useState(null);
  const [answers, setAnswers] = useState({});
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    getQuizById(quizId)
      .then(setQuiz)
      .catch(() => console.error("Failed to load quiz"))
      .finally(() => setLoading(false));
  }, [quizId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);

    await submitQuizAttempt({
      quizId,
      answers: Object.entries(answers).map(([qId, selected]) => ({
        questionId: parseInt(qId),
        selectedAnswer: selected,
      })),
    });

    onComplete();
    setSubmitting(false);
  };

  if (loading) return <p>Loading quiz...</p>;
  if (!quiz) return <p>Quiz not found</p>;

  return (
    <div className="quiz-attempt-container">
      <h2>{quiz.title || 'Quiz'}</h2>
      <p>{quiz.description || ''}</p>
      
      {(!quiz.questions || quiz.questions.length === 0) ? (
        <div>
          <p>No questions available for this quiz yet.</p>
          <button onClick={onComplete}>Back to Quiz List</button>
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
            <button type="button" onClick={onComplete}>Cancel</button>
          </div>
        </form>
      )}
    </div>
  );
};

export default QuizAttempt;