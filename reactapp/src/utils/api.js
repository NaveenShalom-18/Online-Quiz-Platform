import axios from "axios";

const API_URL = "http://localhost:8081/api";

axios.defaults.headers.common['Content-Type'] = 'application/json';
axios.defaults.timeout = 10000;

// User APIs
export const createUser = async (user) => {
  try {
    const response = await axios.post(`${API_URL}/users`, user);
    return response.data;
  } catch (error) {
    console.error('Signup API Error:', error);
    throw error;
  }
};

export const loginUser = async (email, password) => {
  try {
    const response = await axios.post("http://localhost:8081/api/users/login", {
      email,
      password
    });
    return response.data;
  } catch (error) {
    console.error("Login API Error:", error);
    throw error;
  }
};



// Quiz APIs
export const fetchQuizzes = async () => {
  try {
    const response = await axios.get(`${API_URL}/quizzes`);
    return response.data;
  } catch (error) {
    console.error('Fetch quizzes error:', error);
    throw error;
  }
};

export const createQuiz = async (quiz) => {
  try {
    const response = await axios.post(`${API_URL}/quizzes`, quiz);
    return response.data;
  } catch (error) {
    console.error('Create quiz error:', error);
    throw error;
  }
};

export const getQuizById = async (id) => {
  const response = await axios.get(`${API_URL}/quizzes/${id}`);
  return response.data;
};

export const addQuestion = async (quizId, question) => {
  const response = await axios.post(`${API_URL}/quizzes/${quizId}/questions`, question);
  return response.data;
};

export const submitQuizAttempt = async (attempt) => {
  const response = await axios.post(`${API_URL}/attempts`, attempt);
  return response.data;
};