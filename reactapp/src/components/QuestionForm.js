import { Component } from "react";
import axios from "axios";
import "./QuestionForm.css";
export default class QuestionForm extends Component {
  state = {
    questionText: "",
    questionType: "MULTIPLE_CHOICE",
    options: [
      { text: "", isCorrect: false },
      { text: "", isCorrect: false },
    ],
    error: "",
    success: ""
  };

  handleQuestionChange = (e) => {
    this.setState({ questionText: e.target.value });
  };

  handleOptionChange = (index, e) => {
    const options = [...this.state.options];
    if (e.target.type === "checkbox") {
      options[index].isCorrect = e.target.checked;
    } else {
      options[index].text = e.target.value;
    }
    this.setState({ options });
  };

  handleSubmit = async (e) => {
    e.preventDefault();
    const { quizId } = this.props;
    try {
      await axios.post(`http://localhost:8081/api/quizzes/${quizId}/questions`, {
        questionText: this.state.questionText,
        questionType: this.state.questionType,
        options: this.state.options
      });
      this.setState({
        questionText: "",
        options: [
          { text: "", isCorrect: false },
          { text: "", isCorrect: false },
        ],
        success: "Question added successfully",
        error: ""
      });
    } catch {
      this.setState({ error: "Failed to add question", success: "" });
    }
  };

  render() {
    const { questionText, options, error, success } = this.state;
    return (
      <form className="question-form" onSubmit={this.handleSubmit}>
        <h3 className="question-form-title">Add Question</h3>
        <input
          className="question-input"
          type="text"
          placeholder="Question text"
          value={questionText}
          onChange={this.handleQuestionChange}
          required
        />
        {options.map((opt, idx) => (
          <div className="option-row" key={idx}>
            <input
              className="option-input"
              type="text"
              placeholder={`Option ${idx + 1}`}
              value={opt.text}
              onChange={(e) => this.handleOptionChange(idx, e)}
              required
            />
            <label className="option-label">
              Correct
              <input
                type="checkbox"
                checked={opt.isCorrect}
                onChange={(e) => this.handleOptionChange(idx, e)}
              />
            </label>
          </div>
        ))}
        <div className="question-form-buttons">
          <button className="question-button" type="submit">Add Question</button>
          {this.props.onBack && (
            <button className="question-button back-button" type="button" onClick={this.props.onBack}>
              Back to Quiz List
            </button>
          )}
        </div>
        {error && <div className="question-error">{error}</div>}
        {success && <div className="question-success">{success}</div>}
      </form>
    );
  }
}
