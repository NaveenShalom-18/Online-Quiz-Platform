import React from "react";
import "./QuizResult.css";

const QuizResult = ({ result, onBackToQuizzes }) => {
  const { score, totalQuestions, percentage } = result;
  
  const getGrade = (percentage) => {
    if (percentage >= 90) return "A+";
    if (percentage >= 80) return "A";
    if (percentage >= 70) return "B";
    if (percentage >= 60) return "C";
    return "F";
  };

  return (
    <div className="quiz-result-container">
      <h2>Quiz Completed!</h2>
      <div className="result-card">
        <div className="score-display">
          <h3>Your Score</h3>
          <div className="score-number">{score}/{totalQuestions}</div>
          <div className="percentage">{percentage}%</div>
          <div className="grade">Grade: {getGrade(percentage)}</div>
        </div>
        <div className="result-details">
          <p>Correct Answers: {score}</p>
          <p>Total Questions: {totalQuestions}</p>
          <p>Accuracy: {percentage}%</p>
        </div>
      </div>
      <button className="back-button" onClick={onBackToQuizzes}>
        Back to Quiz List
      </button>
    </div>
  );
};

export default QuizResult;