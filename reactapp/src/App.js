import React, { useState } from "react";
import Login from "./components/Login";
import Signup from "./components/Signup";
import QuizList from "./components/QuizList";
import QuizForm from "./components/QuizForm";
import QuestionForm from "./components/QuestionForm";
import QuizAttempt from "./components/QuizAttempt";
import QuizResult from "./components/QuizResult";
import QuizHistory from "./components/QuizHistory";
import './App.css';

const TestConnection = () => {
  const [status, setStatus] = useState('Testing...');
  
  React.useEffect(() => {
    fetch('http://localhost:8081/api/users')
      .then(() => setStatus('Backend Connected'))
      .catch(() => setStatus('Backend Disconnected'));
  }, []);
  
  return <div className="connection-status">{status}</div>;
};

const App = () => {
  const [user, setUser] = useState(null);
  const [showSignup, setShowSignup] = useState(false);
  const [selectedQuizId, setSelectedQuizId] = useState(null);
  const [creatingQuiz, setCreatingQuiz] = useState(false);
  const [takingQuiz, setTakingQuiz] = useState(false);
  const [editingQuiz, setEditingQuiz] = useState(false);
  const [quizResult, setQuizResult] = useState(null);
  const [showHistory, setShowHistory] = useState(false);

  if (!user) {
    return (
      <div className="app-container">
        {!showSignup ? (
          <Login 
            onLogin={(userData) => setUser(userData)}
            onSwitchToSignup={() => setShowSignup(true)}
          />
        ) : (
          <Signup 
            onSignup={(userData) => setUser(userData)}
            onSwitchToLogin={() => setShowSignup(false)}
          />
        )}
      </div>
    );
  }

  return (
    <div className="app-container">
      <div className="app-header">
        <div className="welcome-bar">
          <p className="welcome-text">
            Welcome, {user.name || user.email}!
          </p>
          <button className="logout-btn" onClick={() => setUser(null)}>Logout</button>
        </div>
      </div>

      <div className="main-content">
        {!selectedQuizId && !creatingQuiz && !takingQuiz && !editingQuiz && !quizResult && !showHistory && (
          <>
            <div className="action-buttons">
              {user.role === 'ADMIN' && (
                <button className="action-btn" onClick={() => setCreatingQuiz(true)}>Create New Quiz</button>
              )}
              {user.role === 'STUDENT' && (
                <button className="action-btn" onClick={() => setShowHistory(true)}>View History</button>
              )}
            </div>
            <QuizList
              onSelectQuiz={(id) => {
                setSelectedQuizId(id);
              }}
            />
          </>
        )}

        {selectedQuizId && !takingQuiz && !editingQuiz && !quizResult && (
          <div className="quiz-actions">
            <h3>Quiz Options</h3>
            <div className="action-buttons">
              <button className="action-btn take-quiz" onClick={() => {
                console.log('Take Quiz clicked for quiz ID:', selectedQuizId);
                setTakingQuiz(true);
              }}>Take Quiz</button>
              {user.role === 'ADMIN' && (
                <button className="action-btn edit-quiz" onClick={() => setEditingQuiz(true)}>Edit Quiz</button>
              )}
              <button className="action-btn back-btn" onClick={() => setSelectedQuizId(null)}>Back</button>
            </div>
          </div>
        )}

        {creatingQuiz && !quizResult && (
          <QuizForm
            onQuizCreated={(quiz) => {
              setCreatingQuiz(false);
              setSelectedQuizId(quiz.id);
            }}
            onBack={() => setCreatingQuiz(false)}
          />
        )}

        {editingQuiz && selectedQuizId && !quizResult && (
          <QuestionForm 
            quizId={selectedQuizId} 
            onQuestionAdded={() => {}} 
            onBack={() => {
              setEditingQuiz(false);
              setSelectedQuizId(null);
            }}
          />
        )}

        {takingQuiz && selectedQuizId && !quizResult && (
          <QuizAttempt 
            quizId={selectedQuizId}
            userId={user.id}
            onComplete={(result) => {
              console.log('Quiz attempt completed with result:', result);
              if (result) {
                setQuizResult(result);
              } else {
                setSelectedQuizId(null);
              }
              setTakingQuiz(false);
            }} 
          />
        )}
        
        {quizResult && (
          <QuizResult 
            result={quizResult}
            onBackToQuizzes={() => {
              setQuizResult(null);
              setSelectedQuizId(null);
            }}
          />
        )}
        
        {showHistory && (
          <QuizHistory 
            userId={user.id}
            onBack={() => setShowHistory(false)}
          />
        )}
        
        {takingQuiz && !selectedQuizId && (
          <div>Error: No quiz selected for attempt</div>
        )}
      </div>
    </div>
  );
};

export default App;
