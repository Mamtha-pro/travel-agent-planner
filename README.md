# Travel Agent Planner — AI Agents using Spring AI & Thymeleaf

A fully functional, interactive AI Agent system built using **Spring Boot 3.4+ / 4.0**, **Spring AI (2.0.0+)**, and **Thymeleaf**. This project implements an agentic travel planner that can search flights and hotels, fetch weather forecasts, and compose a travel plan within a user budget using function-calling tools powered by Spring AI.

---

## 🌟 Features

- **Travel-focused AI Agent**:
  - Search flights by origin, destination and date.
  - Find hotels within a budget for a given city and date.
  - Retrieve weather forecasts for trip destinations.
- **Function Calling / Agentic Tools**: Uses Spring AI `@Tool` annotated Java methods so the chat model can call backend functions (tools) dynamically.
- **Conversational Memory**: Keeps context across turns using a message window style memory with conversation IDs.
- **Modern UI**: Thymeleaf front-end styled with Tailwind CSS, supports light/dark mode and renders AI responses with Markdown.

---

## 📂 Project Structure

```text
├── README.md                          # Project overview & running instructions
├── .gitignore                         # Git ignore rules
├── docs/                              # Optional docs & prompts
└── travel-agent-backend/              # Spring Boot application
    ├── pom.xml                        # Maven configuration (Spring AI dependencies)
    ├── mvnw / mvnw.cmd                # Maven Wrapper
    ├── src/
    │   ├── main/
    │   │   ├── java/.../backend/
    │   │   │   ├── TravelAgentApplication.java
    │   │   │   ├── config/
    │   │   │   │   └── AiConfig.java   # ChatClient builder & tool registration
    │   │   │   ├── controller/
    │   │   │   │   ├── ChatController.java  # POST Endpoint for chat messages
    │   │   │   │   └── ViewController.java  # Renders the Thymeleaf web interface
    │   │   │   ├── model/                 # Data objects (Flight, Hotel, User)
    │   │   │   ├── service/
    │   │   │   │   └── ChatService.java   # Business logic invoking ChatClient
    │   │   │   └── tools/                 # Custom Agentic Tools
    │   │   │       ├── FlightTools.java
    │   │   │       ├── HotelTools.java
    │   │   │       └── WeatherTools.java
    │   │   └── resources/
    │   │       ├── templates/
    │   │       │   └── chat.html      # Thymeleaf UI template styled with Tailwind CSS
    │   │       ├── application.yml    # Configuration properties (OpenAI settings)
    │   │       └── application.properties
```

---

## 🛠️ Tech Stack

- **Backend**: Spring Boot, Spring Web, Spring AI, Lombok
- **AI Integration**: Spring AI starter for OpenAI (model configurable, e.g., `gpt-4o-mini`)
- **Frontend**: Thymeleaf, Tailwind CSS (Browser runtime), Marked.js (Markdown renderer)
- **Language**: Java 21+

---

## ⚙️ Configuration & Setup

1. **Prerequisites**:
   - Java 21 or higher.
   - Maven (or use the included Maven Wrapper `./mvnw`).
   - An OpenAI API key.

2. **Environment Variables**:
   Create a `.env` file in the project root or export the following variables in your shell:

```bash
export OPENAI_API_KEY="your-openai-api-key"
export CHAT_MODEL="gpt-4o-mini"
```

3. **Application Properties** (`application.yml`):

```yaml
server:
  port: 8081

spring:
  application:
    name: travel-agent-backend
  ai:
    openai:
      api-key: ${OPENAI_API_KEY:default-key}
      chat:
        model: ${CHAT_MODEL:gpt-4o-mini}
```

---

## 🚀 Running the Application

Navigate to the backend directory and start the server:

```bash
cd travel-agent-backend
./mvnw spring-boot:run
```

Open your browser to:

👉 http://localhost:8081/

---

## 🤖 Registered Agent Tools

The agent can call the following Java tools automatically depending on user input:

| Tool Class | Method Name | Description |
| :--- | :--- | :--- |
| `FlightTools` | `searchFlight` | Searches flights by source, destination, and date. |
| `HotelTools` | `searchHotel` | Finds hotels matching a city and max budget per night. |
| `WeatherTools` | `getForecast` | Retrieves weather conditions (temp, status) for a city. |

---

## 💡 Example Prompt for Testing

Try this prompt in the chat UI to see multi-tool reasoning:

> "Plan a trip from Delhi to Goa on 2026-08-21. My total budget for flight + one night hotel is ₹7000. What should I book, and what's the weather going to be like?"

The agent will:
1. Search flights from Delhi to Goa on `2026-08-21`.
2. Check for hotels in Goa.
3. Fetch the weather forecast for Goa on `2026-08-21`.
4. Calculate options that fit within your budget and return a tailored plan.

---

## ✍️ Customizing the README

If you want I can:
- Update names, default ports, or model config to match your project exactly.
- Add usage screenshots or GIFs.
- Add license and contribution guidelines.
