import React, { useState, useEffect } from "react";
import { getUserAttempts } from "../utils/api";
import "./QuizHistory.css";

const QuizHistory = ({ userId, onBack }) => {
  const [attempts, setAttempts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    console.log('Fetching attempts for userId:', userId);
    getUserAttempts(userId)
      .then((data) => {
        console.log('Quiz attempts received:', data);
        setAttempts(data);
      })
      .catch((error) => {
        console.error("Failed to load quiz history:", error);
      })
      .finally(() => setLoading(false));
  }, [userId]);

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleString();
  };

  const getGrade = (score, total) => {
    const percentage = (score / total) * 100;
    if (percentage >= 90) return "A+";
    if (percentage >= 80) return "A";
    if (percentage >= 70) return "B";
    if (percentage >= 60) return "C";
    return "F";
  };

  if (loading) return <p>Loading history...</p>;

  return (
    <div className="quiz-history-container">
      <h2>Quiz History</h2>
      {attempts.length === 0 ? (
        <div>
          <p>No quiz attempts found.</p>
          <p>Debug: userId = {userId}</p>
        </div>
      ) : (
        <div className="history-list">
          {attempts.map((attempt) => (
            <div key={attempt.id} className="history-item">
              <h3>{attempt.quizTitle}</h3>
              <div className="attempt-details">
                <span>Score: {attempt.score}/{attempt.totalQuestions}</span>
                <span>Grade: {getGrade(attempt.score, attempt.totalQuestions)}</span>
                <span>Completed: {formatDate(attempt.completedAt)}</span>
              </div>
            </div>
          ))}
        </div>
      )}
      <button className="back-button" onClick={onBack}>
        Back
      </button>
    </div>
  );
};

export default QuizHistory;