# 🚀 Local RAG Chatbot

A locally running **Retrieval-Augmented Generation (RAG) Chatbot** built using Java, Spring Boot, Spring AI, and Ollama.

This project demonstrates how to build an AI-powered chatbot that retrieves relevant information from a knowledge base using vector embeddings and generates context-aware responses using a locally hosted Large Language Model (LLM).

The application uses local AI models through Ollama, allowing users to experiment with RAG without depending on paid cloud AI APIs.

---

## 📌 Project Overview

The Local RAG Chatbot follows a Retrieval-Augmented Generation architecture.

Instead of directly generating answers from the LLM, the application first retrieves relevant information from a knowledge base and then provides that context to the language model.

### Key Features

- Document ingestion and processing.
- Text chunking for better retrieval.
- Vector embedding generation using Ollama.
- Semantic similarity search.
- Context-aware AI chatbot responses.
- Local LLM integration using Ollama.
- REST API development using Spring Boot.
- Local execution without paid AI APIs.

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend development |
| Spring Boot | Application framework |
| Spring AI | AI and RAG integration |
| Ollama | Local LLM execution |
| Qwen 3.5 (4B) | Chat model |
| Nomic Embed Text | Embedding model |
| Maven | Dependency management |
| REST APIs | Client-server communication |

---

## 🏗️ Project Structure

```text
local-rag-chatbot/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/rag/local_rag_chatbot/
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── ChatController.java
│   │   │       │   ├── DocumentController.java
│   │   │       │   └── VectorStoreController.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── ChunkingService.java
│   │   │       │   ├── DocumentService.java
│   │   │       │   ├── RagService.java
│   │   │       │   └── VectorStoreService.java
│   │   │       │
│   │   │       └── LocalRagChatbotApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── knowledge.txt
│   │
│   └── test/
│
├── .gitignore
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## 🧠 RAG Architecture

The application follows these steps to generate an answer:

```text
              KNOWLEDGE BASE
                     |
                     v
              Document Loading
                     |
                     v
               Text Chunking
                     |
                     v
             Embedding Model
              (Nomic Embed)
                     |
                     v
                Vector Store
                     |
                     |
              USER QUESTION
                     |
                     v
             Question Embedding
                     |
                     v
              Vector Similarity
                  Search
                     |
                     v
             Relevant Documents
                     |
                     v
             Context + Question
                     |
                     v
                 Ollama LLM
                (Qwen 3.5)
                     |
                     v
              GENERATED ANSWER
```

### How RAG Works

1. **Document Loading:** Reads information from the knowledge base.
2. **Chunking:** Splits large documents into smaller text chunks.
3. **Embedding Generation:** Converts text chunks into numerical vector representations.
4. **Vector Storage:** Stores the embeddings for future retrieval.
5. **Semantic Search:** Converts the user's question into an embedding and searches for similar document chunks.
6. **Context Retrieval:** Retrieves relevant information from the vector store.
7. **LLM Generation:** Sends the retrieved context and user question to the Ollama chat model.
8. **Response:** Returns a context-aware answer to the user.

---

## 🔌 REST API Endpoints

The application exposes three main REST API endpoints.

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/vectors/store` | Stores documents and their embeddings in the vector store. |
| GET | `/vectors/search?query=who developed java` | Performs semantic similarity search. |
| GET | `/chat?question=who created java` | Main RAG chatbot endpoint that retrieves context and generates an answer. |

### 1. Store Documents

**HTTP Method:** POST

**Endpoint:**

```http
http://localhost:8080/vectors/store
```

**Purpose:**

- Processes document content.
- Splits text into smaller chunks.
- Generates embeddings using the embedding model.
- Stores the resulting vectors in the vector store.

### 2. Search Vectors

**HTTP Method:** GET

**Endpoint:**

```http
http://localhost:8080/vectors/search?query=who developed java
```

**Purpose:**

- Accepts a search query.
- Generates a query embedding.
- Performs semantic similarity search.
- Returns relevant document chunks.

**Example Query:**

```text
who developed java
```

### 3. Chat with RAG

**HTTP Method:** GET

**Endpoint:**

```http
http://localhost:8080/chat?question=who created java
```

**Purpose:**

This is the main chatbot endpoint.

It performs the following operations:

1. Accepts the user's question.
2. Converts the question into an embedding.
3. Searches the vector store for relevant information.
4. Retrieves matching document chunks.
5. Sends the retrieved context and question to the Ollama LLM.
6. Generates and returns a context-aware response.

---

## ⚙️ Prerequisites

Make sure the following tools are installed:

- Java (version configured in `pom.xml`).
- Maven.
- Ollama.
- Git.

---

## 🤖 Ollama Setup

Download and install Ollama:

https://ollama.com/

Pull the required models:

### Chat Model

```bash
ollama pull qwen3.5:4b
```

### Embedding Model

```bash
ollama pull nomic-embed-text
```

Verify installed models:

```bash
ollama list
```

Start Ollama:

```bash
ollama serve
```

Ollama normally runs locally at:

```text
http://localhost:11434
```

---

## ⚙️ Application Configuration

The application uses the following configuration in:

`src/main/resources/application.properties`

```properties
spring.application.name=local-rag-chatbot

server.port=8080

spring.ai.ollama.chat.options.model=qwen3.5:4b

spring.ai.ollama.embedding.options.model=nomic-embed-text:latest
```

---

## ▶️ Run the Application

### Step 1: Clone the Repository

```bash
git clone https://github.com/hemamanichandu/local-rag-chatbot.git
```

### Step 2: Navigate to the Project

```bash
cd local-rag-chatbot
```

### Step 3: Download Dependencies and Build

```bash
mvn clean install
```

### Step 4: Start the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or using the Maven wrapper on Windows:

```cmd
mvnw.cmd spring-boot:run
```

Application runs at:

```text
http://localhost:8080
```

Make sure Ollama is running before testing the APIs.

---

## 🧪 Testing the APIs

You can test the REST APIs using Postman or a browser.

| Operation | Method | URL |
|---|---|---|
| Store vectors | POST | `http://localhost:8080/vectors/store` |
| Search vectors | GET | `http://localhost:8080/vectors/search?query=who developed java` |
| Ask chatbot | GET | `http://localhost:8080/chat?question=who created java` |

**Note:** The `/vectors/store` endpoint uses POST. Check your controller implementation for the expected request body or parameters.

---

## 🎯 Learning Outcomes

Through this project, I explored:

- Retrieval-Augmented Generation (RAG).
- Integrating Spring AI with Spring Boot.
- Working with locally hosted LLMs.
- Generating text embeddings.
- Document chunking and processing.
- Vector similarity search.
- Building AI-powered REST APIs.
- Connecting retrieved knowledge with LLM-generated responses.

---

## 🚀 Future Enhancements

- PDF and DOCX document ingestion.
- Conversation memory.
- Chat history management.
- Metadata filtering.
- Source document citations.
- Improved chunking strategies.
- Frontend chatbot interface.
- Multi-document knowledge base support.

---

## 👨‍💻 Author

**Hema Mani Chandu Donka**

Java Backend Developer | Spring Boot | AI & RAG Enthusiast

- GitHub: https://github.com/hemamanichandu

---

⭐ If you find this project interesting, consider giving the repository a star!

