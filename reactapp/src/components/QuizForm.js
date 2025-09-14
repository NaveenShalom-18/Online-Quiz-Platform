import { Component } from "react";
import { createQuiz } from "../utils/api";
import "./QuizForm.css";
export default class QuizForm extends Component {
  state = { title: "", description: "", timeLimit: 10, error: "", success: "" };

  handleChange = (e) => {
    this.setState({ [e.target.name]: e.target.value });
  };

  handleSubmit = async (e) => {
    e.preventDefault();
    const { title, description, timeLimit } = this.state;
    try {
      const quiz = await createQuiz({ title, description, timeLimit });
      this.setState({ success: "Quiz created successfully", error: "", title: "", description: "", timeLimit: 10 });
      if (this.props.onQuizCreated) {
        this.props.onQuizCreated(quiz);
      }
    } catch (error) {
      console.error('Quiz creation error:', error);
      this.setState({ error: "Failed to create quiz", success: "" });
    }
  };

  render() {
    const { title, description, timeLimit, error, success } = this.state;
    return (
      <form className="quiz-form" onSubmit={this.handleSubmit}>
        <h2 className="quiz-form-title">Create New Quiz</h2>
        <input
          className="quiz-input"
          type="text"
          name="title"
          placeholder="Quiz Title"
          value={title}
          onChange={this.handleChange}
          required
        />
        <textarea
          className="quiz-input"
          name="description"
          placeholder="Quiz Description"
          value={description}
          onChange={this.handleChange}
          required
        />
        <input
          className="quiz-input"
          type="number"
          name="timeLimit"
          min="1"
          max="180"
          value={timeLimit}
          onChange={this.handleChange}
          required
        />
        <div className="quiz-form-buttons">
          <button className="quiz-button" type="submit">Create Quiz</button>
          {this.props.onBack && (
            <button className="quiz-button back-button" type="button" onClick={this.props.onBack}>
              Back
            </button>
          )}
        </div>
        {error && <div className="quiz-error">{error}</div>}
        {success && <div className="quiz-success">{success}</div>}
      </form>
    );
  }
}
