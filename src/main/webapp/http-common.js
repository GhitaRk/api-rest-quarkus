import axios from 'axios'

export default axios.create({
	baseURL: "http://localhost:8887",
	headers: {
		"Content-Type": "application/json"
	}
});
