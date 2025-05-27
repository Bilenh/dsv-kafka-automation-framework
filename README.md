## 📁 Exercise 1 – Kafka Automation Framework

**Project Folder**: `dsv-kafka-automation-framework`  
**Description**:  
A Maven-based Kafka project that:
- Sends a mock message using Mockito to a Kafka producer.
- Starts a consumer to listen and verify messages.
- Includes a runnable Kafka app to listen to a real broker at `localhost:9090`.
- Test reports can be generated using Allure.

### ✅ Prerequisites

- Java 17+
- [Apache Maven](https://maven.apache.org/download.cgi)
- Kafka running locally on port `9090`
- Allure CLI (for reporting):  
  Install via [Allure CLI](https://docs.qameta.io/allure/#_installing_a_commandline)

```bash
npm install -g allure-commandline --save-dev
```

---

### 🚀 How to Run

**1. Start Kafka Broker (if using real listener)**  
Use your Kafka local setup or Docker to start a broker at `localhost:9090`.

**2. Run Kafka Application from VS Code**

```bash
mvn spring-boot:run
```

**3. Run Tests (includes Producer & Consumer verification)**

```bash
mvn clean test
```

**4. Generate Allure Report**

```bash
mvn test allure:serve
```

---
