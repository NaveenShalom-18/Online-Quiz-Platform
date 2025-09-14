import { Component } from "react";
import { fetchQuizzes } from "../utils/api";
import "./QuizList.css";

export default class QuizList extends Component {
  state = { quizzes: [], error: "" };

  componentDidMount() {
    fetchQuizzes()
      .then(data => this.setState({ quizzes: data }))
      .catch(() => this.setState({ error: "Failed to fetch quizzes" }));
  }

  render() {
    const { quizzes, error } = this.state;
    if (error) return <div className="quiz-error">{error}</div>;
    
    if (quizzes.length === 0) {
      return (
        <div className="quiz-list">
          <div className="no-quizzes">
            <h3>No quizzes available</h3>
            <p>Create your first quiz to get started!</p>
          </div>
        </div>
      );
    }
    
    return (
      <div className="quiz-list">
        <h2>Available Quizzes</h2>
        {quizzes.map(quiz => (
          <div 
            className="quiz-card" 
            key={quiz.id}
            onClick={() => this.props.onSelectQuiz(quiz.id)}
            style={{ cursor: 'pointer' }}
          >
            <h3 className="quiz-title">{quiz.title}</h3>
            <p className="quiz-description">{quiz.description}</p>
            <p className="quiz-time">Time: {quiz.timeLimit} minutes</p>
            <div className="quiz-card-footer">
              <span className="click-hint">Click to select this quiz</span>
            </div>
          </div>
        ))}
      </div>
    );
  }
}
