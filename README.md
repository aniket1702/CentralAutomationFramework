# 🚀 Automation Framework from Scratch

Welcome to the **Java-Maven-TestNG Selenium Automation Framework** built from the ground up!
This project is designed for scalable, maintainable, and robust web test automation, integrating modern tools and reporting.

---

## 📆 Tech Stack

| Tool / Library        | Purpose                       |
| --------------------- | ----------------------------- |
| ☕ Java 18             | Programming language          |
| 🧱 Maven              | Build & dependency management |
| 🧪 TestNG             | Test framework                |
| 🌐 Selenium WebDriver | Web UI automation             |
| 🧾 Apache POI         | Excel data handling           |
| 📊 Allure             | Test reporting                |
| 📦 GitHub Packages    | Dependency hosting            |
| ⚙️ Log4j              | Logging                       |
| 🧙 Lombok             | Code reduction                |
| 🔐 Owner              | Config management             |

---

## 🏗️ Project Setup

Follow these steps to clone and run the framework locally.

### 1. 🔀 Clone the Repository

```bash
git clone https://github.com/aniket1702/CentralAutomationFramework.git
cd CentralAutomationFramework
```

### 2. 🚰 Build the Maven Project

```bash
mvn clean install
```

### 3. 📅 Download Dependencies

All required libraries are defined in `pom.xml`. Maven will download them automatically when you build the project.

#### 📚 Dependencies Included:

```xml
<maven.build.timestamp.format>yyyyMMdd-HHmmss</maven.build.timestamp.format>
<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
<maven.compiler.source>18</maven.compiler.source>
<maven.compiler.target>18</maven.compiler.target>
<maven.compiler.version>3.10.1</maven.compiler.version>
<maven.surefire.version>3.0.0-M5</maven.surefire.version>
<selenium.version>4.16.1</selenium.version>
<testng.version>7.8.0</testng.version>
<owner.version>1.0.12</owner.version>
<lombok.version>1.18.30</lombok.version>
<log4j.version>2.20.0</log4j.version>
<allure.version>2.24.0</allure.version>
<allure.writer.version>1.0.0</allure.writer.version>
<aspectj.version>1.9.19</aspectj.version>
<apache.poi.version>5.2.5</apache.poi.version>
<commons-codec.version.version>1.16.0</commons-codec.version.version>
```

### 4. 🏐 GitHub Package Configuration

To use this project as a GitHub dependency:

```xml
<repository>
  <id>github</id>
  <url>https://maven.pkg.github.com/yourusername/your-framework-repo</url>
</repository>
```

Make sure to configure your GitHub credentials in your `C:/Users/<username>/.m2/settings.xml`.

---

## 📁 Folder Structure

```
src/
  ├── main/java/        # Utilities, Configs, Core
pom.xml                 # Project dependencies

```

---

## 📌 Coming Soon

* ✅ CI/CD via GitHub Actions
* ✅ Docker support
* ✅ WebDriverManager integration
* ✅ Parallel test execution

---

## 📢 Let's Connect

📧 Email: [aniketmaurya1702@gmail.com](mailto:aniketmaurya1702@gmail.com)
🔗 LinkedIn: [Aniket Maurya](https://www.linkedin.com/in/aniketmaurya1702/)

---

## ⭐ Star the repo if you find it useful!

Made with ❤️ by \[Aniket Maurya]



